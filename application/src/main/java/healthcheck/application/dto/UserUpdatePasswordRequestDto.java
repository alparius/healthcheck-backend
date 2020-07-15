package healthcheck.application.dto;

import lombok.Data;

@Data
public class UserUpdatePasswordRequestDto {
    private String newPassword;
}
