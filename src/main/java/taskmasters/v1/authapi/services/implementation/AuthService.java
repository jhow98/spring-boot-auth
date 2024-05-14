package taskmasters.v1.authapi.services.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import taskmasters.v1.authapi.dto.AuthDTO;
import taskmasters.v1.authapi.models.Users;
import taskmasters.v1.authapi.repositories.UserRepository;
import taskmasters.v1.authapi.services.AuthServiceInterface;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthService implements AuthServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }

    @Override
    public String getToken(AuthDTO authDTO) {
        Users user = userRepository.findByLogin(authDTO.login());
        return generateJWTToken(user);
    }

    public String generateJWTToken(Users user){

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret-key011");

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch(JWTCreationException exception) {
                throw new RuntimeException("Erro ao gerar o token" + exception.getMessage());
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret-key011");

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception){
            return "";
        }
    }

}
