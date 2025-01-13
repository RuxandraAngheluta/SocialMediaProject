package socialmedia.post;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepositoryInterface extends JpaRepository<Post,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.title = :title, p.content = :content WHERE p.id = :id")
    int updatePostContentAndTitleById(@Param("id") Integer id, @Param("title") String title, @Param("content") String content);


    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.status = 'PUBLISHED'")
    List<Post> findAllPublishedPostsByUserId(@Param("userId") Integer userId);

    @Modifying
    @Query("UPDATE Post p SET p.status = 'PUBLISHED' WHERE p.id = :postId")
    int approvePost(@Param("postId") Integer postId);

    @Modifying
    @Query("SELECT p FROM Post p WHERE p.status = 'PENDING' AND p.user.id = :userId")
    List<Post> findAllPendingPostsByUserId(@Param("userId") Integer userId);


}
