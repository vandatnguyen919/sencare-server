package org.entrepremium.sencare.feature.blog.tag.dto;

import jakarta.validation.constraints.NotEmpty;

public record TagDto(
        String id,
        @NotEmpty(message = "Tag name is required")
        String name,
        Integer blogPostCount
) {}