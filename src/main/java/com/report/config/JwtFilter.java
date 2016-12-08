package com.report.config;

import com.report.service.ReportClientService;
import com.report.util.CacheUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {


    @Autowired
    ReportClientService reportClientService;

    @Value("${report.service.url}")
    private String url;

    @Value("${report.test.user.email}")
    private String userEmail;

    @Value("${report.test.user.password}")
    private String userPassword;



    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();

        final String authHeader = request.getHeader("Authorization");



        String userCachedToken = CacheUtil.cachedAuthToken.getIfPresent(userEmail);
        if (StringUtils.isBlank(userCachedToken)){

           /* String userNewToken = reportClientService.login(email, password);
            CacheUtil.cachedAuthToken.put(userEmail,userNewToken);
            response.addCookie(new Cookie("Authorization",userNewToken));*/
        }



        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new ServletException("Missing or invalid Authorization header.");


        final String token = authHeader.substring(7); // The part after "Bearer "

        try {
            final Claims claims = Jwts.parser().setSigningKey("secretkey")
                .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        } catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }

        chain.doFilter(req, res);
    }

}
