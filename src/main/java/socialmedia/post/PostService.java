package socialmedia.post;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialmedia.comment.CommentRepositoryInterface;
import socialmedia.user.User;
import socialmedia.user.UserRepositoryInterface;

import java.io.Serializable;
import java.util.List;

@Service
public class PostService implements Serializable {
    @Autowired
    private PostRepositoryInterface postRepositoryInterface;
    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    @Autowired
    private CommentRepositoryInterface commentRepositoryInterface;
    public Post create(Post myPost){
        if (myPost.getUser() == null || myPost.getUser().getId() == null) {
            throw new IllegalArgumentException("Post must be associated with a valid user");
        }
        User user = userRepositoryInterface.findById(myPost.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + myPost.getUser().getId() + " does not exist"));
        Hibernate.initialize(user);

        myPost.setUser(user);
        myPost.setStatus(Status.PENDING);

        return postRepositoryInterface.save(myPost);
    }

    public List<Post> getAllPost(Integer userId){
        return postRepositoryInterface.findAllPendingPostsByUserId(userId);
    }

   public List<Post> getAllPublishedPostsForUser(Integer userId){
        return postRepositoryInterface.findAllPublishedPostsByUserId(userId);
   }
    @Transactional
    public Post update(Integer id, String title, String content) {
        if (id == null || title == null || content == null) {
            throw new IllegalArgumentException("Id, title, and content cannot be null");
        }

        if (!postRepositoryInterface.existsById(id)) {
            throw new IllegalArgumentException("Post with ID " + id + " does not exist");
        }

        int rowsUpdated = postRepositoryInterface.updatePostContentAndTitleById(id, title, content);

        if (rowsUpdated > 0) {
            return postRepositoryInterface.findById(id).orElseThrow(() ->
                    new RuntimeException("Post with ID " + id + " could not be fetched after update"));
        }

        throw new RuntimeException("Failed to update the post");
    }

    @Transactional
    public Post delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        Post postToDelete = postRepositoryInterface.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + id + " does not exist"));

        commentRepositoryInterface.deleteByPostId(id);
        postRepositoryInterface.deleteById(id);

        return postToDelete;
    }


    @Transactional
    public Post approvePost(Integer postId){
        if(postId==null){
            throw new IllegalArgumentException("Id not can be null");
        }
        Post post=postRepositoryInterface.findById(postId).orElseThrow(()->new IllegalArgumentException("Post with ID " + postId +" does not exist"));
        if(post.getStatus()==Status.PUBLISHED){
            throw new IllegalArgumentException("Post is already published");
        }
        postRepositoryInterface.approvePost(postId);
        return postRepositoryInterface.findById(postId).orElse(null);
    }
}
