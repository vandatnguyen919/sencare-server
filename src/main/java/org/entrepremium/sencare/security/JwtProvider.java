package org.entrepremium.sencare.security;

import org.entrepremium.sencare.feature.myuser.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final JwtEncoder jwtEncoder;

    public JwtProvider(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String createToken(Authentication authentication) {
        Instant now = Instant.now();
        long expireIn = 2; // 2 hours

        MyUserPrincipal userDetails = (MyUserPrincipal) authentication.getPrincipal();
        String userId = userDetails.getUserId(); // Assuming you have a method to get user ID

        // Prepare a claim called authorities.
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" ")); // MUST BE space-delimited;

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .id(userId)
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(expireIn, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("authorities", authorities)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
