package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Branch{
    private String name;
    private Commit lastCommit;
}
