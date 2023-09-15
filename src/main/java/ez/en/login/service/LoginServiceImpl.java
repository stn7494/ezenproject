package ez.en.login.service;

import ez.en.login.dto.LoginDTO;
import ez.en.login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class LoginServiceImpl implements LoginService{

    private final LoginRepository loginRepository;

    private final ModelMapper modelMapper;


    @Override
    public LoginDTO login(LoginDTO loginDTO) {



        return null;
    }
}
