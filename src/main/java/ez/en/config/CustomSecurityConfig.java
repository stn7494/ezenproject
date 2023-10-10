package ez.en.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationFailureHandler customFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                //접근허용 페이지
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/css/**", "/img/**", "/js/**", "/vendor/**", "/vendor/tools/**").permitAll()
                .antMatchers("/index").authenticated()

                //STOCK
                .antMatchers("/stock/**").hasRole("STOCK")

                //CHART
                .antMatchers("/chart/**").hasAnyRole("STOCK","ORDER","SUPPORT","ADMIN")

                //CONTRACT
                .antMatchers("/contract/**").hasRole("SUPPORT")

                //SUPPORTPLAN
                .antMatchers("/supportplan/**").hasRole("SUPPORT")

                //PRODUCT
                .antMatchers("/product/**").hasRole("SUPPORT")

                //ORDER
                .antMatchers("/order/**").hasRole("ORDER")

                //SUPPORT
                .antMatchers("/support/**").hasRole("SUPPORT");

        http.formLogin()
                .loginPage("/login")
                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/index");



        http.csrf().disable();
        http.logout()
                .logoutSuccessUrl("/");



    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        log.info("--------------configure-----------------");
//
////        http.formLogin().loginPage("/login");
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/contract/**").hasRole()
//
//        return http.build();
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        log.info("-------------web configure--------------");

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
