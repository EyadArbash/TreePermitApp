package treePermit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user@example.com")
            .password(passwordEncoder().encode("password123"))
            .roles("USER")
            .build();
        
        UserDetails clerk = User.builder()
            .username("clerk@example.com")
            .password(passwordEncoder().encode("clerkpassword"))
            .roles("CLERK")
            .build();

        return new InMemoryUserDetailsManager(user, clerk);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/register", "/login").permitAll()
            .antMatchers("/dashboard_user", "/application_form", "/communication_interface").hasRole("USER")
            .antMatchers("/dashboard_clerks").hasRole("CLERK")
            .and().formLogin()
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                        response.sendRedirect("/dashboard_user");
                    } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLERK"))) {
                        response.sendRedirect("/dashboard_clerks");
                    } else {
                        response.sendRedirect("/");
                    }
                })
                .permitAll()
            .and().logout().permitAll()
            .and().csrf().disable();
    }
}
