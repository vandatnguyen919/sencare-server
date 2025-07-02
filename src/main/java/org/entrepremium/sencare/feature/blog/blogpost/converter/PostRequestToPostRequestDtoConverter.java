package org.entrepremium.sencare.feature.blog.blogpost.converter;

import org.entrepremium.sencare.feature.blog.blogpost.BlogPost;
import org.entrepremium.sencare.feature.blog.blogpost.dto.BlogPostRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostRequestToPostRequestDtoConverter implements Converter<BlogPost, BlogPostRequestDto> {
    @Override
    public BlogPostRequestDto convert(BlogPost source) {
        return new BlogPostRequestDto(
                source.getHeading(),
                source.getContent(),
                source.getPageTitle(),
                source.getShortDescription(),
                source.getFeaturedImageUrl(),
                source.isVisible()
        );
    }
}
