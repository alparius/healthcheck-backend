package healthcheck.application.service;

import healthcheck.application.dto.UserUpdateRequestDto;
import healthcheck.application.model.User;
import healthcheck.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;


    public void addUser(User user) {
        final String password = UUID.randomUUID().toString().replace("-","");
        user.setEmail(user.getEmail());
        user.setPassword(password);
        user.setIsAdmin(false);
        user.setCity(user.getHospital().getCity());
        userRepository.save(user);
        mailService.sendEmail(user.getEmail(), password);
    }

    @Transactional
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = this.userRepository.findById(id).get();
        user.setUsername(userUpdateRequestDto.getUsername());
        user.setPhone(userUpdateRequestDto.getPhone());
        user.setFirstName(userUpdateRequestDto.getFirstName());
        user.setSurname(userUpdateRequestDto.getSurname());
    }

    public User getUserById(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser.get();
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }

    @Transactional
    public void updateUserPassword(long userId, String newPassword) {
        User user = this.userRepository.findById(userId).get();
        user.setPassword(newPassword);
    }

    public List<User> getAllVolunteersFromCity(String city) {
        return userRepository.getAllVolunteersFromCity(city);
    }
}
