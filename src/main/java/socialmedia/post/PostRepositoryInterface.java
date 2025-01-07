package socialmedia.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepositoryInterface extends JpaRepository<Post,Integer> {
    @Modifying
    @Query("UPDATE Post p SET p.title = :title, p.content = :content WHERE p.id = :id")
    int updatePostById(Integer id, String title, String content);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.status = 'PUBLISHED'")
    List<Post> findAllPublishedPostsByUserId(@Param("userId") Integer userId);

    @Modifying
    @Query("UPDATE Post p SET p.status = 'PUBLISHED' WHERE p.id = :postId")
    int approvePost(@Param("postId") Integer postId);

}
