package socialmedia.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
@Service
public class CommentService implements Serializable {
    @Autowired
    private CommentRepositoryInterface commentRepositoryInterface;
    public List<Comment> getAllComment(){
        return commentRepositoryInterface.findAll();
    }

    public Comment cretate(Comment myComment) {
        if (myComment.getPost() == null || myComment.getPost().getId() == null) {
            throw new IllegalArgumentException("Comment must be associated with a valid post");
        }

        if (myComment.getUser() == null || myComment.getUser().getId() == null) {
            throw new IllegalArgumentException("Comment must be associated with a valid user");
        }

        return commentRepositoryInterface.save(myComment);
    }


    public Comment deleteComment(Integer id){
        Comment comment=commentRepositoryInterface.findById(id).orElse(null);
        commentRepositoryInterface.deleteById(id);
        return comment;
    }

    public Comment update(Integer id,String content){
        int rowsUpdated = commentRepositoryInterface.updateCommentById(id,content);
        if (rowsUpdated > 0) {
            return commentRepositoryInterface.findById(id).orElse(null);
        }
        throw new RuntimeException("Failed to update the comment");
    }

    public List<Comment> getAllCommentsForPost(Integer postId){
        return commentRepositoryInterface.findAllCommentsByPostId(postId);
    }
}
