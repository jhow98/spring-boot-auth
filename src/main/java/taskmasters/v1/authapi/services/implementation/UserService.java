package taskmasters.v1.authapi.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taskmasters.v1.authapi.dto.UserDTO;
import taskmasters.v1.authapi.models.Users;
import taskmasters.v1.authapi.repositories.UserRepository;
import taskmasters.v1.authapi.services.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        Users userExists = userRepository.findByLogin(userDTO.login());

        if(userExists != null){
            throw new RuntimeException("Usuário já existe");
        }

        var passwordHash = passwordEncoder.encode(userDTO.password());

        Users entity = new Users(userDTO.name(), userDTO.login(), passwordHash, userDTO.role());
        Users newUser = userRepository.save(entity);
        return new UserDTO(newUser.getName(), newUser.getLogin(), newUser.getPassword(), newUser.getRole());
    }
}
