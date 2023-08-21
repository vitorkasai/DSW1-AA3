package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;
import br.ufscar.dc.dsw.security.LocadoraDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

@Bean
public UserDetailsService userDetailsService() {
   return new UsuarioDetailsServiceImpl();
}

@Bean
public UserDetailsService LocDetailsService() {
   return new LocadoraDetailsServiceImpl();
}


@Bean
public BCryptPasswordEncoder passwordEncoder() {
   return new BCryptPasswordEncoder();
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   authProvider.setUserDetailsService(userDetailsService());
   authProvider.setPasswordEncoder(passwordEncoder());

   return authProvider;
}

@Bean
    public DaoAuthenticationProvider locadoraAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(LocDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   auth.authenticationProvider(authenticationProvider());
   auth.authenticationProvider(locadoraAuthenticationProvider());
}

@Override
protected void configure(HttpSecurity http) throws Exception {
   http.authorizeRequests()
   		.antMatchers("/", "/index/**", "/error").permitAll()
   		.antMatchers("/login/**", "/js/**", "/css/**").permitAll()
          	.antMatchers("/image/**", "/webjars/**").permitAll()
   		.antMatchers("/admin/**").hasRole("ADMIN")
   		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/locadora/**").hasRole("LOCADORA")
   		.anyRequest().authenticated()
		   
   	.and()
   		.formLogin()
   		.loginPage("/login")
   		.permitAll()
   	.and()
   		.logout()
   		.logoutSuccessUrl("/")
   		.permitAll();
}
}