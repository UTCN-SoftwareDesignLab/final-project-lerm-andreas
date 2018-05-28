package carRentalApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_name, password, true from users where user_name = ?")
                .authoritiesByUsernameQuery("select user_name, role from users where user_name = ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
                //.passwordEncoder(new BCryptPasswordEncoder());
    }

   @Override
   public void configure(HttpSecurity httpSecurity) throws Exception
   {
       httpSecurity
               .csrf()
               .disable()
               .authorizeRequests()
               .antMatchers("/login").permitAll()
               //.antMatchers("/user").hasRole("user")
               //.antMatchers("/user").hasRole("administrator")
               .antMatchers("/admin").hasRole("administrator")
               .antMatchers("/employee").hasRole("employee")
               .antMatchers("/carsView").hasRole("customer")
               .anyRequest().authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .successHandler(getAuthenticationSuccessHandler())
              // .failureHandler(getAuthenticationFailureHandler())
               .permitAll()
               .and()
               .logout()
               .permitAll();
   }
    @Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
                .ignoring()
                .antMatchers("/resources/**");
    }
    private AuthenticationSuccessHandler getAuthenticationSuccessHandler()
    {
        return new AuthSuccessHandler();
    }
    //private AuthenticationFailureHandler getAuthenticationFailureHandler(){ return new AuthFailureHandler();}

}
