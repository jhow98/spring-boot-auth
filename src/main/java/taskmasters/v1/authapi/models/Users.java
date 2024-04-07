package taskmasters.v1.authapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taskmasters.v1.authapi.enums.RoleEnum;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String login;
    private String password;
    private RoleEnum role;

    public Users(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

}
