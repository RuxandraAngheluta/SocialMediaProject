package socialmedia.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepositoryInterface extends JpaRepository<Comment,Integer> {
@Modifying
@Query("UPDATE Comment c SET c.content=:content WHERE c.id=:id")
    int updateCommentById(Integer id,String content);
    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId")
    List<Comment> findAllCommentsByPostId(@Param("postId") Integer postId);

}
