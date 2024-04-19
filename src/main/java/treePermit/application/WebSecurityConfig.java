package treePermit.application;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/**").permitAll() // Erlaubt den Zugriff auf alle Pfade
                .anyRequest().authenticated()
            .and()
            .csrf().disable(); // Wenn Sie Cross Site Request Forgery nicht benötigen
    }
}
