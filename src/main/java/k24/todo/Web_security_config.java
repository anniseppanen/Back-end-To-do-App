package k24.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import k24.todo.web.User_Detail_Service_Impl;

@Configuration
public class Web_security_config {
    @Autowired
    private User_Detail_Service_Impl user_detail_service;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // kaikille sivuille tarvitaan autentikointi
        http.authorizeHttpRequests(authorize -> 
            authorize
                .anyRequest().authenticated()
        )
        // login-sivulta pääsee onnistuneen sisäänkirjautumisen jälkeen todos-sivulle
        .formLogin(formlogin ->
            formlogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/todos", true)
                    .permitAll()
        )
        .logout(logout ->
            logout
                .permitAll()
        );

		return http.build();
    }

    
    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Haetaan userdetailservice-luokasta käyttäjätiedot
		auth.userDetailsService(user_detail_service).passwordEncoder(new BCryptPasswordEncoder());
	}
    
    // Salasanan kryptaus
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
}
