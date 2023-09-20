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
                    .email("admin")
                    .pw(passwordEncoder.encode("admin"))
                    .name("관리자")
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
    public void testSelect() {
        int sno = 1;

        Optional<Stock> result = stockRepository.findById(sno);

        Stock stock = result.orElseThrow();

        log.info(stock);
    }
    @Test
    public void testPaging() {

        Pageable pageable = PageRequest.of(0, 3, Sort.by("sno").descending());

        Page<Stock> result = stockRepository.findAll(pageable);

        log.info(result.getTotalElements());
    }
    @Test
    public void testSearchAll() {
        String[] types = {"t"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 3, Sort.by("sno").descending());

        Page<Stock> result = stockRepository.searchAll(types, keyword, pageable);

        //total pages
        log.info(result.getTotalElements());
        //pag size
        log.info(result.getSize());
        //pageNumber
        log.info(result.getNumber());
        //prev next
        log.info(result.hasPrevious() + " : "  + result.hasNext());

        result.getContent().forEach(stock -> log.info(stock));
    }
    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<StockDTO> responseDTO = stockService.list(pageRequestDTO);

        log.info(responseDTO);
    }

}
