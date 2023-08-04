package com.fc5.adminback.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc5.adminback.common.APIDataResponse;
import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ObjectMapper objectMapper = new ObjectMapper();
        if (
                httpServletRequest.getRequestURI().equals("/api/login")
                        || httpServletRequest.getRequestURI().equals("/api/logout")
        ) {
            chain.doFilter(request, response);
            return;
        }

        try {
            HttpSession session = httpServletRequest.getSession(false);

            if (session == null || session.getAttribute("adminId") == null) {
                httpServletResponse.setStatus(401);
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                APIDataResponse.empty(HttpStatus.UNAUTHORIZED, AnnualErrorCode.UNAUTHORIZED.getMessage());

                APIDataResponse<Object> body = new APIDataResponse<>(HttpStatus.UNAUTHORIZED.value(), AnnualErrorCode.UNAUTHORIZED.getMessage(), null);

                String jsonBody = objectMapper.writeValueAsString(body);
                httpServletResponse.getWriter().write(jsonBody);
                return;
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }
}
