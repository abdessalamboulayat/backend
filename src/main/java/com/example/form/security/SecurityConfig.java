package com.example.form.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.form.filter.JwtAuthFilter;
import com.example.form.filter.JwtAuthorizationFilter;
import com.example.form.bean.Safir;
import com.example.form.service.SafirService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SafirService safirService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				Safir safir =  safirService.findByEmailSafir(email);
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				safir.getAuthorities().forEach(r->{
					authorities.add(new SimpleGrantedAuthority(r.getAuthority()));
				});
				
				return new User(safir.getUsername(), safir.getPassword(), authorities);
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.formLogin();
		http.authorizeRequests().antMatchers("/api/auth/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/evenement/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/formation/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/Consultation/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/formulaire/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/contact/**").permitAll();
		http.authorizeRequests().antMatchers("/api/activiteSafir/**").permitAll();
		http.authorizeRequests().antMatchers("/auth/v1/AllUsers").hasAuthority("Admin");
		http.authorizeRequests().antMatchers("/auth/v1/AllRoles").hasAuthority("Admin");
		http.authorizeRequests().antMatchers("/api/Admin/**").hasAuthority("Admin");
		http.authorizeRequests().antMatchers("/api/safir/**").hasAuthority("Safir");
		//http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JwtAuthFilter(authenticationManagerBean()));
		http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	/*@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    final CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3006"));
	    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setExposedHeaders(Arrays.asList("X-Auth-Token", "Authorization", "Access-Control-Allow-Origin",
	            "Access-Control-Allow-Credentials"));
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}*/
	
}
