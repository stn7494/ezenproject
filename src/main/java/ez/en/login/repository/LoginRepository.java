package ez.en.login.repository;

import ez.en.login.domain.Login;
import ez.en.login.domain.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Login m where m.email = :email")
    Optional<Login> getWithRolesLogin(String email);

    @Query(value = "select l from Login l")
    List<Login> getAll();

//    @Modifying
//    @Transactional
//    @Query(value = "update Login l set l.failcnt = l.failcnt + 1 where l.email = :email")
//    void failLogin(String email);
//
//    @Modifying
//    @Transactional
//    @Query(value = "update Login l set l.prison = 1 where l.email = :email")
//    void loginLock(String email);
//
//    @Query(value = "select l.failcnt from Login l where l.email = :email")
//    int checkCnt(String email);
//
//    @Query(value = "select l.prison from Login l where l.email = :email")
//    int checkPrison(String email);
//
//    @Modifying
//    @Transactional
//    @Query(value = "update Login l set l.failcnt = 0 where l.email = :email")
//    void resetCnt(String email);

}
