package com.BYP;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.Customizer.withDefaults;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
public class WebSecurityConfig {
     
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }

  @Bean
  SecurityFilterChain configure(HttpSecurity http) throws Exception {
      //FIXME user not properly logged

      http.authenticationProvider(authenticationProvider());

      // Configure authorization based on URL patterns and roles
      http.securityMatcher("/login", "/register").authorizeHttpRequests((authz) -> authz// /, /userView, /userHomepage, /admin hasRole("ADMIN")
              .anyRequest().denyAll() // Deny access to all other unsecured URLs (optional, for stricter security)
      );

      // Configure form login with usernameParameter
      http.formLogin(login -> login
              .usernameParameter("email")
              .defaultSuccessUrl("/userView")//FIXME must be adjusted userView.html
              .permitAll()
      );

      // Configure logout with logoutSuccessUrl
      http.logout(logout -> logout.logoutSuccessUrl("/").permitAll());

      return http.build();
  }
}
