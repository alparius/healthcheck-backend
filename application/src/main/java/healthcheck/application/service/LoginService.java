package healthcheck.application.service;

import healthcheck.application.dto.LoginRequestDto;
import healthcheck.application.model.User;
import healthcheck.application.session.Session;
import healthcheck.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    private Map<String, Session> connectedUsers = new HashMap<String, Session>();

    private String generateNewUserToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public boolean isUserLoggedIn(String userToken) {
        if (connectedUsers.containsKey(userToken)) {
            return true;
        }
        return false;
    }

    public Session getUserSession(String userToken) throws Exception {
        if (connectedUsers.containsKey(userToken)) {
            return connectedUsers.get(userToken);
        }
        else {
            throw new Exception("Invalid user token!");
        }
    }

    public Session loginUser(LoginRequestDto loginRequestDto) throws Exception {
        Optional<User> optionalUser =
                userRepository.getUserByEmail(loginRequestDto.getEmail());

        if (!optionalUser.isPresent() ||
                !optionalUser.get().getPassword().equals(loginRequestDto.getPassword())) {
            throw new Exception("Invalid combination sent for login");
        }


        String userToken = this.generateNewUserToken();
        User user = optionalUser.get();

        Session newSession = Session.builder()
                .userId(user.getId())
                .userToken(userToken)
                .username(user.getUsername())
                .email(user.getEmail())
                .isAdmin(user.getIsAdmin())
                .sessionCreationTime(Instant.now())
                .build();

        //add user to connected
        this.connectedUsers.put(userToken, newSession);
        return newSession;
    }

    public void logoutUser(String userToken) throws Exception {
        if (!this.isUserLoggedIn(userToken)) {
            throw new Exception("User was not logged in previously");
        }

        connectedUsers.remove(userToken);
    }

}
