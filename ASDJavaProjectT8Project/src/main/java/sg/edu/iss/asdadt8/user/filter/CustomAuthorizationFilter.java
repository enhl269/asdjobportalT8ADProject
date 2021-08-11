package sg.edu.iss.asdadt8.user.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;



public class CustomAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// check the request wants to login or request for refresh token
        if(request.getServletPath().equals("/api/login")||request.getServletPath().equals("/api/refreshtoken" )){
            filterChain.doFilter(request,response);
        } else{
        	// get token from http header.authorization
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            //the header should have prefix with "Bearer "
            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());

                    //the same algorithm with authentication filter
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                    //verify the jwt algorithm
                    JWTVerifier verifier = JWT.require(algorithm).build();

                    //decode jwt and get username and roles
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

                    //get the authority
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    for(String role:roles){
                    	authorities.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username,null,authorities);

                    //set the authority into the security context and do the filtering
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                } catch (Exception exception){
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    //response.sendError(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }
            }else {
                filterChain.doFilter(request,response);
            }   
        }
	}
}
