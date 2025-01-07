package socialmedia.comment;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import socialmedia.post.Post;
import socialmedia.user.User;

import java.util.Date;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name="post_id",nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = true)
    private User user;

    private String content;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createdOn;
}
