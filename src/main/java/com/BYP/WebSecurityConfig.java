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

   // @Override
   //  protected void configure(HttpSecurity http) throws Exception {
   //
   //      http.
   //          authorizeRequests()
   //              .antMatchers("/").permitAll()
   //              .antMatchers("/login").permitAll()
   //              .antMatchers("/registration").permitAll()
   //              .antMatchers("/app/*").hasAnyAuthority("ADMIN", "CUSTOMER")
   //              .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()                
   //              .authenticated().and().csrf().disable().formLogin()
   //              .loginPage("/login").failureUrl("/login?error=true")
   //              .defaultSuccessUrl("/home")
   //              .usernameParameter("username")
   //              .passwordParameter("password")
   //              .and().logout()
   //              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
   //              .logoutSuccessUrl("/").and().exceptionHandling()
   //              .accessDeniedPage("/access-denied");
   //  }
  @Bean
  SecurityFilterChain configure(HttpSecurity http) throws Exception {

      http.authenticationProvider(authenticationProvider());

      // Configure authorization based on URL patterns and roles
      http.authorizeHttpRequests((authz) -> authz
              .requestMatchers("/").permitAll() // Allow login and register without authentication
              .requestMatchers("/login").permitAll() // Allow login and register without authentication
              .requestMatchers("/register").permitAll() // Allow login and register without authentication
              .requestMatchers("/userView").permitAll() // Allow login and register without authentication
              .requestMatchers("/userHomepage").authenticated() // Require authentication for userHomepage
              // .antMatchers("/admin").hasRole("ADMIN") // Restrict admin page to users with ADMIN role
              // .anyRequest().denyAll() // Deny access to all other unsecured URLs (optional, for stricter security)
      );

      // Configure form login with usernameParameter
      http.formLogin(login -> login
              .usernameParameter("email")
              .defaultSuccessUrl("/userView")
              .permitAll()
      );

      // Configure logout with logoutSuccessUrl
      http.logout(logout -> logout.logoutSuccessUrl("/").permitAll());

      return http.build();
  }
  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //     http
  //         .authorizeHttpRequests((authz) -> authz
  //             .requestMatchers("/", "/login", "/register").permitAll()
  //             .requestMatchers("/userView").authenticated()
  //             .anyRequest().permitAll()
  //         )
  //         .httpBasic(withDefaults());
  //     return http.build();
  // }
  // @Bean
  // SecurityFilterChain configure(HttpSecurity http) throws Exception {
  //      
  //     http.authenticationProvider(authenticationProvider());
  //     http.authorizeHttpRequests(auth ->
  //         auth.requestMatchers("/userHomepage").authenticated()
  //         .anyRequest().permitAll()
  //         )
  //         .formLogin(login ->
  //             login.usernameParameter("email")
  //             .defaultSuccessUrl("/userHomepage")
  //             .permitAll()
  //         )
  //         .logout(logout -> logout.logoutSuccessUrl("/").permitAll()
  //     );
  //
  //     return http.build();
  // }  
}
