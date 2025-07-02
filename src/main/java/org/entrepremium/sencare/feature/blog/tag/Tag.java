package org.entrepremium.sencare.feature.blog.tag;

import org.entrepremium.sencare.feature.blog.blogpost.BlogPost;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<BlogPost> blogPosts = new ArrayList<>();
}