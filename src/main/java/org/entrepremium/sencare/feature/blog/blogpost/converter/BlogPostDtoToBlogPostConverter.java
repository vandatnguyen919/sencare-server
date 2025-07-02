package org.entrepremium.sencare.feature.blog.blogpost.converter;

import org.entrepremium.sencare.feature.blog.blogpost.BlogPost;
import org.entrepremium.sencare.feature.blog.blogpost.dto.BlogPostDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogPostDtoToBlogPostConverter implements Converter<BlogPostDto, BlogPost> {

    @Override
    public BlogPost convert(BlogPostDto source) {
        BlogPost blogPost = new BlogPost();
        blogPost.setHeading(source.heading());
        blogPost.setContent(source.content());
        blogPost.setPageTitle(source.pageTitle());
        blogPost.setShortDescription(source.shortDescription());
        blogPost.setFeaturedImageUrl(source.featuredImageUrl());
        blogPost.setVisible(source.isVisible());
        return blogPost;
    }
}
