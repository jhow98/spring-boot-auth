package taskmasters.v1.authapi.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmasters.v1.authapi.dto.UserDTO;
import taskmasters.v1.authapi.models.Users;
import taskmasters.v1.authapi.repositories.UserRepository;
import taskmasters.v1.authapi.services.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        Users userExists = userRepository.findByLogin(userDTO.login());

        if(userExists != null){
            throw new RuntimeException("Usuário já existe");
        }
        Users entity = new Users(userDTO.name(), userDTO.login(), userDTO.password());
        Users newUser = userRepository.save(entity);
        return new UserDTO(newUser.getName(), newUser.getLogin(), newUser.getPassword());
    }
}
