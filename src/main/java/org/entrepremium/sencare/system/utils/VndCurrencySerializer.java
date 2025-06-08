package org.entrepremium.sencare.system.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class VndCurrencySerializer extends JsonSerializer<BigDecimal> {
    
    private static final Locale VIETNAM_LOCALE = new Locale("vi", "VN");

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(VIETNAM_LOCALE);
            String formattedPrice = currencyFormat.format(value);
            gen.writeString(formattedPrice);
        }
    }
}