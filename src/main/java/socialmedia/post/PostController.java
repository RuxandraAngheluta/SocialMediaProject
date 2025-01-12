package socialmedia.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/post")
    public Post create(@RequestBody Post myPost){
        return postService.create(myPost);
    }
    @GetMapping("/findAll")
    public List<Post> get(){return postService.getAllPost();}
    @PutMapping("/{id}")
    public Post update(@PathVariable Integer id, @RequestParam String title,@RequestParam String content){return  postService.update(id,title,content);}
    @DeleteMapping("delete/{id}")
    public Post delete(@PathVariable Integer id) {
        return postService.delete(id);
    }
    @GetMapping("/findPostForUser/{userId}")
    public List<Post> getAllPublishedPostsForUser(@PathVariable Integer userId){
        return postService.getAllPublishedPostsForUser(userId);
    }
    @PutMapping("/posts/approve/{id}")
    public Post createApprovePost(@PathVariable Integer postId){
        return postService.approvePost(postId);
    }

}
