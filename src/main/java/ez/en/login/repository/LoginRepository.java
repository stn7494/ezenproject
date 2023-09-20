package ez.en.login.repository;

import ez.en.login.domain.Login;
import ez.en.login.domain.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Login m where m.email = :email")
    Optional<Login> getWithRolesLogin(String email);


}
