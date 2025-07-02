package org.entrepremium.sencare.feature.blog.blogpost.converter;

import org.entrepremium.sencare.feature.blog.blogpost.BlogPost;
import org.entrepremium.sencare.feature.blog.blogpost.dto.BlogPostRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostRequestDtoToPostRequestConverter implements Converter<BlogPostRequestDto, BlogPost> {

    @Override
    public BlogPost convert(BlogPostRequestDto source) {
        BlogPost post = new BlogPost();
        post.setHeading(source.heading());
        post.setContent(source.content());
        post.setPageTitle(source.pageTitle());
        post.setShortDescription(source.shortDescription());
        post.setFeaturedImageUrl(source.featuredImageUrl());
        post.setVisible(source.isVisible());
        return post;
    }
}
