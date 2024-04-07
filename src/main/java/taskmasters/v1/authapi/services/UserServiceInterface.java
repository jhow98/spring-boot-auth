package taskmasters.v1.authapi.services;

import taskmasters.v1.authapi.dto.UserDTO;

public interface UserServiceInterface {
    public UserDTO createUser(UserDTO userDTO);
}
