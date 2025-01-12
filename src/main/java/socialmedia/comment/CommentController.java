package socialmedia.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:8081")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping ("/CommentPost")
    public Comment createComment(@RequestBody Comment myComment){
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
    public Comment updateComment(@PathVariable Integer id,@PathVariable String content){
       return commentService.update(id,content);
    }
    @GetMapping("/commentForPost/{postId}")
    public List<Comment> getAllCommentsForPost(@PathVariable Integer postId){
        return commentService.getAllCommentsForPost(postId);
    }
}
