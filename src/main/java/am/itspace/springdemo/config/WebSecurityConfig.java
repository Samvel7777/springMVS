package am.itspace.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/editBook").hasAnyRole("ADMIN", "USER")
                .antMatchers("/deleteBook").hasAnyRole("ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("poxos")
                .password(encoder().encode("poxos"))
                .roles("USER")
                .and()
                .withUser("petros")
                .password(encoder().encode("petros"))
                .roles("ADMIN");
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
