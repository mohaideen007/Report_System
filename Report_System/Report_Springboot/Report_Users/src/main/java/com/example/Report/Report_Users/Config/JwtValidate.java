package com.example.Report.Report_Users.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Report.Report_Users.Service.Jwt_service;
import com.example.Report.Report_Users.Service.MyUserdetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtValidate extends OncePerRequestFilter {
    @Autowired
    private Jwt_service jwtservice;
    
    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                 String path=request.getRequestURI();

                 if(path.startsWith("/users/login")||path.startsWith("/users/adduser")||path.startsWith("/users/sendmail/")){
            filterChain.doFilter(request, response);
            return;
        }
        String header=request.getHeader("Authorization");
        String token="";
        String username="";

       

        if(header!=null&&header.startsWith("Bearer ")){
            token=header.substring(7);
            username=jwtservice.extractusername(token);
        }
        if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userdetail=context.getBean(MyUserdetailsService.class).loadUserByUsername(username);
            if(jwtservice.validatetoken(token,userdetail)){
                String role=jwtservice.extractrole(token);
                List<GrantedAuthority>granded=List.of(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userdetail,null, granded);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request, response);

    }

}
