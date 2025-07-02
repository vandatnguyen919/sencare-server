package org.entrepremium.sencare.feature.blog.blogpost.dto;

import org.entrepremium.sencare.feature.myuser.dto.UserDto;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record BlogPostDto(String id,
                          @NotEmpty(message = "heading is required.")
                          String heading,
                          @NotEmpty(message = "content is required.")
                          String content,
                          String pageTitle,
                          String shortDescription,
                          String featuredImageUrl,
                          boolean isVisible,
                          List<String> nameTags,
                          UserDto userDto) {
}
