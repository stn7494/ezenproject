package ez.en.login.repository;

import ez.en.login.domain.Login;
import ez.en.login.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
