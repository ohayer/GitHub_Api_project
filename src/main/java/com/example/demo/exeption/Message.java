package com.example.demo.exeption;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@AllArgsConstructor
@NoArgsConstructor
@ControllerAdvice
@Setter
@Getter
public class Message {
    private int status;
    private String message;

    @Override
    public String toString() {
        return "{" + "\n" +
                "status:" + status + "\n" +
                "Message:'" + message + '\'' +
                "\n" +
                '}';
    }

    public static boolean isAccepted(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        return acceptHeader != null && acceptHeader.contains("application/xml");
    }
}
