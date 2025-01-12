package socialmedia.user;

import lombok.Data;

@Data

public class LoginResponse {
    private User user;
    public LoginResponse(User user) {
        this.user = user;
    }
}
