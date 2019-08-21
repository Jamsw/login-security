package com.sparticles.filter;


import com.sparticles.utils.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Date 2019/8/21 11:26 PM
 * @Auther smart
 **/
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private  JwtTokenService jwtTokenService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        log.info("get authentication token " + authHeader);

        if (authHeader != null && authHeader.startsWith(tokenHead)&& authHeader.length()> 11) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String username = jwtTokenService.getUsernameFromToken(authToken);

            log.info("checking authentication " + username);
            if ((username == null || authToken == null)) {
//                response.setStatus(401);
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                    out.append("401");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
                return;
            }
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails userDetails = this.securityUserDetailsService.loadUserByUsername(username);
//
//                // 暂时不考虑过期
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
//                        request));
//                log.info("authenticated user " + username + ", setting security context");
//                //SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
        }

        chain.doFilter(request, response);
    }
}
