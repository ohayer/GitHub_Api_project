package com.example.demo.controller;

import com.example.demo.connect.GitHubApiClient;
import com.example.demo.exeption.Message;
import com.example.demo.exeption.UserNotFoundException;
import com.example.demo.user.Repository;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users/{username}")
    @ResponseBody
    public List getUser(@PathVariable String username, HttpServletRequest request) {
        GitHubApiClient gitHubApiClient = new GitHubApiClient();
        if (Message.isAccepted(request)) {
            return Collections.singletonList("forward:/error");
        }
        try {
            List<Repository> list = gitHubApiClient.getUserRepositories(username);
            gitHubApiClient.close();
            return list;
        } catch (JsonSyntaxException e) {
            throw new UserNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/error")
    public String getUser() {
        Message message = new Message(406, "You have got accepted the application/xml");
        return message.toString();
    }
}
