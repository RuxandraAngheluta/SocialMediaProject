package socialmedia.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialmedia.post.Post;
import socialmedia.post.PostRepositoryInterface;
import socialmedia.user.User;
import socialmedia.user.UserRepositoryInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:8081")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostRepositoryInterface postRepositoryInterface;

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    @PostMapping("/CommentPost/{postId}/{userId}")
    public Comment createComment(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, @RequestBody Comment myComment) {
        Optional<Post> post = postRepositoryInterface.findById(postId);
        if (post.isEmpty()) {
            throw new IllegalArgumentException("Post not found for the provided ID");
        }
        myComment.setPost(post.get());
        Optional<User> user = userRepositoryInterface.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist");
        }
        myComment.setUser(user.get());
        return commentService.cretate(myComment);
    }

    @GetMapping("/all")
    public List<Comment> getAllCommenta(){
        return commentService.getAllComment();
    }
    @DeleteMapping("/delete/{id}")
    public Comment deleteComment(@PathVariable Integer id){
        return commentService.deleteComment(id);
    }
    @PutMapping("/update/{id}")
    public Comment updateComment(@PathVariable Integer id,@RequestParam String content){
       return commentService.update(id,content);
    }
    @GetMapping("/commentForPost/{postId}")
    public List<Comment> getAllCommentsForPost(@PathVariable Integer postId){
        return commentService.getAllCommentsForPost(postId);
    }
}
