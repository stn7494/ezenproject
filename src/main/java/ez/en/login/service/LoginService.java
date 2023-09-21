package ez.en.login.service;


import ez.en.login.domain.Login;

import java.util.List;
import java.util.Optional;

public interface LoginService {


    //    List<Role> login(LoginDTO loginDTO);

    void loginFail(String email);

    int checkFailCount(String email);

    void lockUser(String email);

}
