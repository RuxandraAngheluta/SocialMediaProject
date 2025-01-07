package socialmedia.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepositoryInterface userRepositoryInterface;

    public User createUser(User newUser){
        return userRepositoryInterface.save(newUser);
    }
}
