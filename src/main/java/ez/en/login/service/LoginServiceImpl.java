package ez.en.login.service;

import ez.en.login.domain.Login;
import ez.en.login.repository.LoginRepository;
import ez.en.login.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class LoginServiceImpl implements LoginService{

    private final LoginRepository loginRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Login> listAll() {

        List<Login> list = loginRepository.getAll();

        return list;
    }
    //    @Override
//    public void loginFail(String email) {
//        loginRepository.failLogin(email);
//    }
//
//    @Override
//    public int checkFailCount(String email) {
//        return loginRepository.checkCnt(email);
//
//    }
//
//    @Override
//    public void lockUser(String email) {
//        loginRepository.loginLock(email);
//    }
//
//    @Override
//    public int checkPrison(String email) {
//        return loginRepository.checkPrison(email);
//    }
//
//    @Override
//    public void resetCnt(String email) {
//        loginRepository.resetCnt(email);
//    }
    //    @Override
//    public List<Role> login(LoginDTO loginDTO) {
//        String email = loginDTO.getEmail();
//        String pw = loginDTO.getPw();
//
//        List<Role> list = roleRepository.findAllWithLogin(email, pw);
//
//        return list;
//    }

}
