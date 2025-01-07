package socialmedia.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContoller {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public User createUser(@RequestBody User newUser){
       return userService.createUser(newUser);
    }
}
