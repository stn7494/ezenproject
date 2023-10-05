package ez.en.login.controller;

import ez.en.login.domain.Login;
import ez.en.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final LoginService service;

    @GetMapping("/")
    public String landging(){
        return "langding";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login";

    }

    @GetMapping("/index")
    public void index(){

    }

    @GetMapping("/admin/list")
    public String list(Model model) {

        List<Login> list = service.listAll();

        model.addAttribute("list", list);
        return "/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("email")String email,Model model) {
        Optional<Login> list = service.detail(email);

        model.addAttribute("list", list);
        return "/detail";
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

}
