package com.cg.trg.boot;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public UserDetailsManager generateUserDetails() {
		
		UserDetails admin = User.builder()
								.username("admin")
								.password("admin")
								.roles("ADMIN","ABCD","MANAGER")
								.build();
		
				
		UserDetails user = User.builder()
							   .username("user")
							   .password("user")
							   .roles("USER")
							   .build();
		
		
		return new InMemoryUserDetailsManager(admin,user);
		
	}
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
			.antMatchers("/welcome").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user").authenticated()
			.and()  //its giving you previous dataTypes
			.formLogin();
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder getEnCoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
