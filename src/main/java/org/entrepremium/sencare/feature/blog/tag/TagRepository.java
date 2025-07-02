package org.entrepremium.sencare.feature.blog.tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByNameIgnoreCase(String name);
}
