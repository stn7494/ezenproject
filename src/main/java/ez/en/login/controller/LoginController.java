package ez.en.login.controller;

import ez.en.login.domain.Role;
import ez.en.login.dto.LoginDTO;
import ez.en.login.repository.LoginRepository;
import ez.en.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/login");
        return mav;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "/index";
    }

    @PostMapping("/login")
    public ModelAndView login(HttpSession session, LoginDTO loginDTO) {

        ModelAndView mav = new ModelAndView();

        List<Role> role = service.login(loginDTO);

        if (role.size() == 0) {
            mav.setViewName("/login");
            return mav;
        } else {
        session.setAttribute("user", role);

        mav.addObject("list", role);

        mav.setViewName("/index");
        return mav;
        }
    }

}
