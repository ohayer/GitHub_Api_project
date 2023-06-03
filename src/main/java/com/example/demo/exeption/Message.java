package com.example.demo.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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
    public static Message isAccepted(HttpServletRequest request){
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains("application/xml")) {
            return new Message(406, "You have got accepted the application/xml");
        }
        return null;
    }
}
