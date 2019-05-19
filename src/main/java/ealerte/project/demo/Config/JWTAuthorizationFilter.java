package ealerte.project.demo.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, content-type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, xsrf-token");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,content-type, Access-Control-Allow-Credentials, Authorization, xsrf-token");

        // response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, PATCH, OPTIONS ");
        response.addHeader("Acces-Allow-Methods","GET, PUT, POST, OPTIONS, PATCH, DELETE,");

        if(request.getMethod().equals("OPTIONS")){
            System.out.println("optons");
            response.setStatus(HttpServletResponse.SC_OK);
        }else if(request.getRequestURI().equals("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        String jwtToken = request.getHeader(SecurityParams.JWT_HEADER_NAME);
        System.out.println(request.getHeader(SecurityParams.JWT_HEADER_NAME));
        System.out.println("token "+jwtToken);
        if(jwtToken==null || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX)){
            System.out.println(request);
          filterChain.doFilter(request,response);
          return ;
         }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
        String jwt=jwtToken.substring(SecurityParams.HEADER_PREFIX.length());
        System.out.println("JWT "+ jwt);
        DecodedJWT decodedJWT= verifier.verify(jwt);
        String username=decodedJWT.getSubject();
        List<String> roles=decodedJWT.getClaims().get("roles").asList(String.class);
        System.out.println("username= "+ username);
        System.out.println("roles= "+ roles);
            Collection<GrantedAuthority> authorities=new ArrayList<>();
            roles.forEach(r->{
                ((ArrayList<GrantedAuthority>) authorities).add(new SimpleGrantedAuthority(r));
                });
            UsernamePasswordAuthenticationToken user= new UsernamePasswordAuthenticationToken(username,null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);


    }

}
