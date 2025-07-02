package org.entrepremium.sencare.feature.blog.blogpost;

import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public BlogPost findById(String postId) {
        return this.blogPostRepository.findById(postId).orElseThrow(() -> new ObjectNotFoundException("blogPost", postId));
    }

    public BlogPost save(BlogPost newPost) {
        return this.blogPostRepository.save(newPost);
    }

    public BlogPost update(String postId, BlogPost post) {

        BlogPost p = blogPostRepository.findById(postId)
                .map(oldPost -> {
                    oldPost.setContent(post.getContent());
                    oldPost.setHeading(post.getHeading());
                    oldPost.setFeaturedImageUrl(post.getFeaturedImageUrl());
                    oldPost.setPageTitle(post.getPageTitle());
                    oldPost.setShortDescription(post.getShortDescription());
                    oldPost.setVisible(post.isVisible());
                    oldPost.setUrlHandle(post.getUrlHandle());
                    return this.blogPostRepository.save(oldPost);
                })
                .orElseThrow(() -> new ObjectNotFoundException("blogPost", postId));

        return blogPostRepository.save(p);
    }

    public void delete(String postId) {
        this.blogPostRepository.findById(postId).orElseThrow(() -> new ObjectNotFoundException("blogPost", postId));
        this.blogPostRepository.deleteById(postId);
    }

    public Page<BlogPost> findAll(Pageable pageable) {
        return this.blogPostRepository.findAll(pageable);
    }
}
