package healthcheck.application.dto;

import lombok.Data;

@Data
public class AddUserDto {
    String email;

    Long hospitalId;
}
