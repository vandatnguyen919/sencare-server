package org.entrepremium.sencare.feature.blog.blogpost.converter;

import org.entrepremium.sencare.feature.blog.blogpost.BlogPost;
import org.entrepremium.sencare.feature.blog.blogpost.dto.BlogPostDto;
import org.entrepremium.sencare.feature.blog.tag.Tag;
import org.entrepremium.sencare.feature.myuser.converter.UserToUserDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BlogPostToBlogPostDtoConverter implements Converter<BlogPost, BlogPostDto> {

    private final UserToUserDtoConverter userToUserDtoConverter;

    public BlogPostToBlogPostDtoConverter(UserToUserDtoConverter userToUserDtoConverter) {
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @Override
    public BlogPostDto convert(BlogPost source) {
        return new BlogPostDto(
                source.getId(),
                source.getHeading(),
                source.getContent(),
                source.getPageTitle(),
                source.getShortDescription(),
                source.getFeaturedImageUrl(),
                source.isVisible(),
                source.getTags().stream().map(Tag::getName).collect(Collectors.toList()),
                userToUserDtoConverter.convert(source.getUser())
                );
    }
}
