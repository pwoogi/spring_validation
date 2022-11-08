package com.example.validation.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter에서 request, reponse를 찍어야할 때
 * ContentCachingRequestWrapper 클래스를 사용한다(HttpServletReq,Res로 하는 경우에는 이미 일음처리하였다고 동작해버림)
 * response를 해줄 때 copyBodyToResponse() 메서드를 사용해야 클라이언트에게 정보를 제공해 줄 수 있다는 점 주의!
 */
@Slf4j
@WebFilter(urlPatterns = "/api-v3/lombok") // 특정한 컨트롤러에게만 주입해줄 때 사용, 여러 Url 배열로 가능
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper ((HttpServletResponse)response);


//        BufferedReader br = httpServletRequest.getReader();
//
//        br.lines().forEach(line ->{
//            log.info("url : {}, line : {}", url, line);
//        });

        chain.doFilter(httpServletRequest, httpServletResponse); //TODO: 기준으로 전, 후 처리해주는 메소드 생성해야함

        String url = httpServletRequest.getRequestURI();
        //후처리

        String reqContent = new String (httpServletRequest.getContentAsByteArray());
        log.info("request status : {}, requestBody : {}", url, reqContent);

        String resContent = new String (httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);
    }
}
