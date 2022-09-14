package com.example.form.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationToken = request.getHeader("Authorization");
		System.out.println(request.getHeaders("Authorization"));
		System.out.println("authorizationToken");
		System.out.println(authorizationToken);
		if(authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
			
			try {
				
				String jwt = authorizationToken.substring(7);
				
				Algorithm algorithm = Algorithm.HMAC256("secret");
				
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				
				DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
				
				String username = decodedJWT.getSubject();
				
				String[] roles = decodedJWT.getClaim("authorities").asArray(String.class);
				
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				
				for(int i=0; i<roles.length; i++) {
					authorities.add(new SimpleGrantedAuthority(roles[i]));
				}
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(roles, username,authorities);
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
				filterChain.doFilter(request, response);
			}
			catch(Exception ex) {
				response.setHeader("Error-message", ex.getMessage());
				response.sendError(403);
			}
		}
		else {
			filterChain.doFilter(request, response);
		}
		
	}

	
}
