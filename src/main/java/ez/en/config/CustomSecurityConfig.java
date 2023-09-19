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

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                //접근허용 페이지
                .antMatchers("/").authenticated()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/css/**", "/img/**", "/js/**", "/vendor/**", "/vendor/tools/**").permitAll()

                //STOCK
                .antMatchers("/stock/ioList").hasRole("STOCK")
                .antMatchers("/stock/orderList").hasRole("STOCK")
                .antMatchers("/stock/stockList").hasRole("STOCK")

                //ORDER
                .antMatchers("/order/detail").hasRole("ORDER")
                .antMatchers("/order/list").hasRole("ORDER")
                .antMatchers("/order/modify").hasRole("ORDER")
                .antMatchers("/order/register").hasRole("ORDER")

                //SUPPORT
                .antMatchers("/support/index").hasRole("SUPPORT")
                .antMatchers("/support/productList").hasRole("SUPPORT")
                .antMatchers("/contract/list").hasRole("SUPPORT")
                .antMatchers("/contract/detail").hasRole("SUPPORT")
                .antMatchers("/contract/modify").hasRole("SUPPORT");

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/");


        http.csrf().disable();

        http.logout()
                .logoutSuccessUrl("/login");

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
