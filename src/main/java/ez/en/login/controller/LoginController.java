package ez.en.login.controller;

import ez.en.login.domain.Role;
import ez.en.login.dto.LoginDTO;
import ez.en.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final LoginService service;

    @GetMapping("/login")
    public void login(String error, String logout){
        log.info("login get......................");
        log.info("logout : " + logout);

        if (logout != null) {
            log.info("user logout..........");
        }
    }
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//
//        return "/index";
//    }

//    @PostMapping("/login")
//    public ModelAndView login(HttpSession session, LoginDTO loginDTO) {
//
//        ModelAndView mav = new ModelAndView();
//
//        List<Role> role = service.login(loginDTO);
//
//        String error = "아이디 또는 비밀번호가 틀렸습니다.";
//
//        if (role.size() == 0) {
//            mav.addObject("error", error);
//            mav.setViewName("login");
//            return mav;
//        } else {
//        session.setAttribute("user", role);
//
//        mav.addObject("list", role);
//
//        mav.setViewName("/index");
//        return mav;
//        }
//    }
    @GetMapping("/fileupload")
    public void file() {

    }

}
