package taskmasters.v1.authapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmasters.v1.authapi.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}