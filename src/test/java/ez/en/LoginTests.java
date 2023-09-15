package ez.en;

import ez.en.login.domain.Login;
import ez.en.login.domain.Role;
import ez.en.login.repository.LoginRepository;
import ez.en.login.repository.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class LoginTests {

    @Autowired
    private RoleRepository repository;

    @Test
    public void testLogin() {
        String email = "user01@naver.com";
        String pw = "user01";

        List<Role> login = repository.findAllWithLogin(email, pw);

        log.info(login.get(0).getLogin().getEmail());

    }

}
