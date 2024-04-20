package treePermit;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/**").permitAll() // Alle Anfragen erlauben
            .and()
            .csrf().disable() // CSRF-Schutz deaktivieren
            .formLogin().disable() // Formularbasierte Anmeldung deaktivieren
            .httpBasic().disable() // HTTP Basic deaktivieren
            .logout().disable(); // Logout deaktivieren
    }
}
