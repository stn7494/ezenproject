package ez.en.sjs;

import ez.en.login.domain.Role;
import ez.en.login.dto.LoginDTO;
import ez.en.login.repository.RoleRepository;
import ez.en.login.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class LoginTests {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private LoginService service;

    @Test
    public void testLogin() {
        LoginDTO loginDTO = new LoginDTO();

        List<Role> login = service.login(loginDTO);

        log.info(login);

    }


}
