package org.entrepremium.sencare.feature.blog.tag;

import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findById(String id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("tag", id));
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(String id, Tag tag) {
        return tagRepository.findById(id)
                .map(oldTag -> {
                    oldTag.setName(tag.getName());
                    return tagRepository.save(oldTag);
                })
                .orElseThrow(() -> new ObjectNotFoundException("tag", id));
    }

    public void delete(String id) {
        tagRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("tag", id));
        tagRepository.deleteById(id);
    }

    public Tag findByNameIgnoreCase(String name) {
        return tagRepository.findByNameIgnoreCase(name);
    }
}