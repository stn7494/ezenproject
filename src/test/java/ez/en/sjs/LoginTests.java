package ez.en.sjs;

import ez.en.login.domain.Member;
import ez.en.login.domain.MemberRole;
import ez.en.login.domain.Role;
import ez.en.login.repository.MemberRepository;
import ez.en.login.repository.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testLogin() {
        String email = "user01@naver.com";
        String pw = "user01";

        List<Role> login = repository.findAllWithLogin(email, pw);

        log.info(login.get(0).getLogin().getEmail());

    }
    @Test
    public void insertMembers() {

        IntStream.rangeClosed(10,10).forEach(i -> {
            Member member = Member.builder()
                    .email("admin")
                    .pw(passwordEncoder.encode("admin"))
                    .name("관리자")
                    .build();

//            member.addRole(MemberRole.ORDER);
            member.addRole(MemberRole.ADMIN);
            member.addRole(MemberRole.SUPPORT);
            member.addRole(MemberRole.ORDER);
            member.addRole(MemberRole.STOCK);

            memberRepository.save(member);
        });
    }
    @Test
    public void testRead() {

        Optional<Member> result = memberRepository.getWithRoles("user01@naver.com");

        Member member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
    }


}
