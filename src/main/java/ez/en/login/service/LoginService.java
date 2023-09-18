package ez.en.login.service;

import ez.en.login.domain.Role;
import ez.en.login.dto.LoginDTO;

import java.util.List;

public interface LoginService {


    List<Role> login(LoginDTO loginDTO);


}
