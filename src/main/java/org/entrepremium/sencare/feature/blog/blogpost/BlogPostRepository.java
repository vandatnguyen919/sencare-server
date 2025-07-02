package org.entrepremium.sencare.feature.blog.blogpost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, String> {
}
