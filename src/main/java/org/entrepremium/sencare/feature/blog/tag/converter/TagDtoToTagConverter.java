package org.entrepremium.sencare.feature.blog.tag.converter;

import org.entrepremium.sencare.feature.blog.tag.Tag;
import org.entrepremium.sencare.feature.blog.tag.dto.TagDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TagDtoToTagConverter implements Converter<TagDto, Tag> {
    @Override
    public Tag convert(TagDto source) {
        Tag tag = new Tag();
        tag.setName(source.name());
        return tag;
    }
}