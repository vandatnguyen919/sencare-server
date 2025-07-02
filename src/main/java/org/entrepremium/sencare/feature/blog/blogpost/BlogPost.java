package org.entrepremium.sencare.feature.blog.blogpost;

import org.entrepremium.sencare.feature.blog.tag.Tag;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(length = 1024)
    private String heading;

    @Column(length = 1024)
    private String pageTitle;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 1024)
    private String shortDescription;

    @Column(length = 1024)
    private String featuredImageUrl;

    private String urlHandle;

    private boolean isVisible;

    @CreationTimestamp
    private LocalDateTime publishedDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @ManyToOne
    private MyUser user;

    @ManyToMany
    @JoinTable(
            name = "blog_post_tag",
            joinColumns = @JoinColumn(name = "blog_post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
}
