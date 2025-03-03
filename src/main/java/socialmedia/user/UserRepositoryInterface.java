package socialmedia.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<User,Integer> {
    User findByEmailAndName(String email, String name);
}
