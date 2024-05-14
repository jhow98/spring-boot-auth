package taskmasters.v1.authapi.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }
}
