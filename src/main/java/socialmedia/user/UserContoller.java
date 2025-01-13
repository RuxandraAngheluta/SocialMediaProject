package socialmedia.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class UserContoller {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public User createUser(@RequestBody User newUser){
       return userService.createUser(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        if (loginUser.getEmail() == null || loginUser.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and Name are required");
        }

        User user = userService.findByEmailAndName(loginUser.getEmail(), loginUser.getName());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user. Please check your credentials.");
        }

        return ResponseEntity.ok(new LoginResponse(user));
    }
}
