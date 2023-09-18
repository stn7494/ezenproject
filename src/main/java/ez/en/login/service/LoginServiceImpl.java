package ez.en.login.service;

import ez.en.login.domain.Role;
import ez.en.login.dto.LoginDTO;
import ez.en.login.repository.LoginRepository;
import ez.en.login.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class LoginServiceImpl implements LoginService{

    private final LoginRepository loginRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<Role> login(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String pw = loginDTO.getPw();

        List<Role> list = roleRepository.findAllWithLogin(email, pw);

        return list;
    }

}
