package com.example.validation.interceptor;

import com.example.validation.annotation.Auth;
import com.example.validation.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();


        log.info("request url: {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        //TODO : 만약에 내 서비스의 서버는 모두 PUBLIC으로 동작하는데 Auth 권한을 가진 요청에 대해서만 PRIVATE으로 해주고 싶다면?
        if (hasAnnotation) {
            // 권한체크
            String query = uri.getQuery();
            if (query.equals("name=steve")) {
                return true;
            }
            throw new AuthException(); //권한 없다면 예외 터트리기(401로 터트려보자)
        }
        return true;
    }

    public boolean checkAnnotation(Object handler, Class clazz) {

        // resource
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            return true;
        }
        return false;
    }
}
