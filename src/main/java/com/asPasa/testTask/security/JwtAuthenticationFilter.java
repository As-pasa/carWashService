package com.asPasa.testTask.security;

import com.asPasa.testTask.services.JwtService;
import com.asPasa.testTask.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private static final String HEADER_NAME="Authorization";
    private static final String HEADER_PREFIX="Bearer ";
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if(request.getRequestURL().toString().contains("api") ||request.getRequestURL().toString().contains("swagger") ){
            filterChain.doFilter(request,response);
            return;
        }

        String header= request.getHeader(HEADER_NAME);
        if(StringUtils.isEmpty(header) || !StringUtils.startsWithIgnoreCase(header, HEADER_PREFIX)){
            filterChain.doFilter(request,response);
            return;
        }
        String jwt=header.substring(HEADER_PREFIX.length());
        String username = jwtService.extractName(jwt);
        if(!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userService.getUserDetailsService().loadUserByUsername(username);
            if(jwtService.isTokenValid(jwt,userDetails)){
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
