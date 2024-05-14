package taskmasters.v1.authapi.dto;

import taskmasters.v1.authapi.enums.RoleEnum;

public record UserDTO(String name, String login, String password, RoleEnum role) {
}
