package com.example.form.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.form.bean.Safir;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;

	public JwtAuthFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("AttemptAuthentication");
		//String username = request.
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken passwordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(passwordAuthenticationToken);*/
		
		//SafirSafir= null;
		String email, password;
		try {
            Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            email = requestMap.get("email");
            password = requestMap.get("password");
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email,password)
			);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successAuthentication");
		Safir safir= (Safir) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("secret");
		
		String jwtAccessToken = JWT.create()
				.withSubject(safir.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+2*60*1000))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("role", safir.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
				.sign(algorithm);
		
		String jwtRefreshToken = JWT.create()
				.withSubject(safir.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
				.withIssuer(request.getRequestURI().toString())
				.sign(algorithm);
		
		Map<String, String> idToken = new HashMap<>();
		idToken.put("access-token",jwtAccessToken);
		idToken.put("refresh-token",jwtRefreshToken);
		response.setContentType("application/json");
		response.setHeader("authorization", jwtAccessToken);
		new ObjectMapper().writeValue(response.getOutputStream(), idToken);
	}
	
	
}
