package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Branch{
    private String name;
    private Commit lastCommit;
}
