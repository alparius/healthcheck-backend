package healthcheck.application.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String username;

    private String phone;

    private String firstName;

    private String surname;
}
