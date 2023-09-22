package ez.en.sjs;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.login.domain.Login;
import ez.en.login.domain.MemberRole;
import ez.en.login.domain.Role;
import ez.en.login.repository.LoginRepository;
import ez.en.login.repository.RoleRepository;
import ez.en.stock.domain.Stock;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.StockRepository;
import ez.en.stock.service.StockService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class LoginTests {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockService stockService;

    @Test
    public void testLogin() {
        String email = "user01@naver.com";
        String pw = "user01";

        List<Role> login = repository.findAllWithLogin(email, pw);

        log.info(login.get(0).getLogin().getEmail());

    }
    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1,1).forEach(i -> {
            Login member = Login.builder()
                    .name("테스트")
                    .pw(passwordEncoder.encode("test"))
                    .email("test")
                    .build();

            member.addRole(MemberRole.ADMIN);
            member.addRole(MemberRole.SUPPORT);
            member.addRole(MemberRole.ORDER);
            member.addRole(MemberRole.STOCK);

            loginRepository.save(member);
        });
    }
    @Test
    public void testRead() {

        Optional<Login> result = loginRepository.getWithRolesLogin("admin");

        Login member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
    }
    @Test
    public void testSelectLogin() {
        String email = "admin";

        Optional<Login> result = loginRepository.findById(email);

        Login login = result.orElseThrow();

        log.info(login);
    }
    @Test
    public void testAll() {

        List<Login> result = loginRepository.getAll();

        for (int i = 0; i < result.size(); i++) {
            log.info(result.get(i).getEmail());
            log.info(result.get(i).getName());
        }
    }
//    @Test
//    public void testfail() {
//
//        String email = "user01@naver.com";
//
//        loginRepository.failLogin(email);
//
//    }
//    @Test
//    public void testLock() {
//
//        String email = "test";
//
//        loginRepository.loginLock(email);
//    }
//    @Test
//    public void testcntLock() {
//        String email = "user01@naver.com";
//
//        int list = loginRepository.checkCnt(email);
//
//        log.info(list);
//    }
    @Test
    public void testDelete() {
        String email = "test";

        loginRepository.deleteById(email);

    }
//    @Test
//    public void testCheckPrison() {
//        String email = "test";
//
//        int check = loginRepository.checkPrison(email);
//
//        log.info(check+"============================");
//    }
    @Test
    public void testListALL() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<StockDTO> responseDTO = stockService.list(pageRequestDTO);

        List<StockDTO> list = responseDTO.getDtoList();

        for (StockDTO stockDTO:list){
            log.info(stockDTO);
        }

    }

}
