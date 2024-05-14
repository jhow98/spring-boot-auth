package taskmasters.v1.authapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import taskmasters.v1.authapi.dto.AuthDTO;
import taskmasters.v1.authapi.models.Users;

public interface AuthServiceInterface extends UserDetailsService {
    public String getToken(AuthDTO authDTO);

    public String validateToken(String token);


}
