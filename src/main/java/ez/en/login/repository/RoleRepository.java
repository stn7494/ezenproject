package ez.en.login.repository;

import ez.en.login.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "select r from Role r join fetch r.login where r.login.email = :email and r.login.pw = :pw")
    List<Role> findAllWithLogin(@Param("email")String email, @Param("pw")String pw);
}
