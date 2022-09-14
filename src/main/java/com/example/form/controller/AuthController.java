package com.example.form.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.form.bean.Authority;
import com.example.form.bean.Safir;
import com.example.form.repository.AuthorityRepo;
import com.example.form.repository.SafirRepo;
import com.example.form.requests.LoginRequest;
import com.example.form.requests.RoleToUserRequest;
import com.example.form.service.AuthorityService;
import com.example.form.service.PasswordService;
import com.example.form.service.SafirService;

@RestController
@CrossOrigin(origins = {"http://localhost:3006", "http://localhost:3000"})
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private SafirRepo safirRepo;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private SafirService safirService;
	@Autowired
	private AuthorityRepo authorityRepo;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) throws Exception{
		System.out.println("login1 "+ request.getEmail());
		
		try {
			System.out.println("login2");
            Authentication authenticate =  (Authentication) authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                    )
                );
            System.out.println("login3");
            UserDetails user =  (UserDetails) ((org.springframework.security.core.Authentication) authenticate).getPrincipal();
            Algorithm algorithm = Algorithm.HMAC256("secret");
            
            String jwtAccessToken = JWT.create()
    				.withSubject(user.getUsername())
    				.withExpiresAt(new Date(System.currentTimeMillis()+120*60*1000))
    				.withClaim("authorities", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
    				.sign(algorithm);

           return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtAccessToken).body(jwtAccessToken);
            
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        	
        }
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<String> validateToken(HttpServletRequest request){
		System.out.println("token validation");
		String jwt = request.getHeader("jwt");
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier jwtVerifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
			System.out.println(decodedJWT);
			return new ResponseEntity<>("valide",HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Not valide",HttpStatus.FORBIDDEN);
		}
	}
	@PostMapping("/addNewSafir")
	public Safir addNewSafir(@RequestBody Safir safir) {
		safir.setPassword(passwordService.passwordEncoder().encode(safir.getPassword()));
		return safirRepo.save(safir);
	}

	@PostMapping("/addNewRole")
	public Authority addNewRole(@RequestBody Authority authority) {
		return authorityService.addNewRole(authority);
	}
	@PostMapping("/roleToUser")
	public Safir addRoleToUser(@RequestBody RoleToUserRequest request) {
		return safirService.addRoleToUser(request.getUsername(), request.getAuthority());
	}
	@GetMapping("/AllUsers")
	public List<Safir> getAllSafir(){
		return safirRepo.findAll();
	}
	@GetMapping("/AllRoles")
	public List<Authority> getAllRole(){
		return authorityRepo.findAll(); 
	}
	
}
