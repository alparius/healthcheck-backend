package healthcheck.application.mapper;

import healthcheck.application.dto.AddUserDto;
import healthcheck.application.model.User;
import healthcheck.application.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserDtoMapper extends  AbstractMapper<User, AddUserDto> {

    @Autowired
    HospitalService hospitalService;

    @Override
    public User convertDtoToModel(AddUserDto addUserDto) {
        User user = new User();
        user.setEmail(addUserDto.getEmail());
        user.setUsername(addUserDto.getEmail().substring(0, addUserDto.getEmail().indexOf("@")));
        return user;
    }

    @Override
    public AddUserDto convertModelToDto(User user) {
        return null;
    }
}
