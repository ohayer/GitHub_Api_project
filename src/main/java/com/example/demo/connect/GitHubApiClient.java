package com.example.demo.connect;

import com.example.demo.user.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.*;

@Component
public class GitHubApiClient {
    private final OkHttpClient httpClient;
    private final String baseUrl;

    public GitHubApiClient() {
        httpClient = new OkHttpClient();
        baseUrl = "https://api.github.com";
    }

    public List<Repository> getUserRepositories(String username) throws IOException {
        String url = baseUrl + "/users/" + username + "/repos";

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer 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")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Repository>>() {
            }.getType();
            List<Repository> repositories = gson.fromJson(responseBody, listType);

            for (Repository repository : repositories) {
                Owner owner = new Owner();
                owner.setUsername(username);
                repository.setOwner(owner);

                List<Branch> branches = getRepositoryBranches(username, repository.getName());
                repository.setBranches(branches);
            }
            return repositories;
        }
    }

    private List<Branch> getRepositoryBranches(String username, String repositoryName) throws IOException {
        String url = baseUrl + "/repos/" + username + "/" + repositoryName + "/branches";

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer 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")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Branch>>() {
            }.getType();
            List<Branch> branches = gson.fromJson(responseBody, listType);

            for (Branch branch : branches) {
                Commit lastCommit = getBranchLastCommit(username, repositoryName, branch.getName());
                branch.setLastCommit(lastCommit);
            }

            return branches;
        }
    }

    private Commit getBranchLastCommit(String username, String repositoryName, String branchName) throws IOException {
        String url = baseUrl + "/repos/" + username + "/" + repositoryName + "/branches/" + branchName;

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer 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")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            Gson gson = new Gson();
            return gson.fromJson(responseBody, Commit.class);
        }
    }

    public void close() {
        httpClient.dispatcher().executorService().shutdown();
    }
}
