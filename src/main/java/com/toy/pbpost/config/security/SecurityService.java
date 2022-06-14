package com.toy.pbpost.config.security;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SecurityService {

    public static String getUid() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String uid = request.getHeader("uid");

        try {
            if (uid != null) {
                return uid;
            }else {
                throw new AuthenticationException("회원이 아닙니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
