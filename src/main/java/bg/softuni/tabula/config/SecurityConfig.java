package bg.softuni.tabula.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // for the ant pattern matcher syntax, please check:
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html

    http
        .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/login/authenticate")
          .failureUrl("/login?param.error=bad_credentials")
          .successForwardUrl("/home")
        .and()
          .logout()
          .logoutUrl("/logout")
          .deleteCookies("JSESSIONID")
        .and()
          .authorizeRequests()
          .antMatchers("/login/**", "/favicon.ico").permitAll()
          .antMatchers("/**")
        .authenticated();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(
        new BCryptPasswordEncoder());
  }
}
