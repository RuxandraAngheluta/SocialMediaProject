package socialmedia.post;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import socialmedia.user.User;

import java.util.Date;

@Entity
@Data

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String title;
    private String content;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createdOn;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
