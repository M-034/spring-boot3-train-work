package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * loginuserエンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    private String userName;
    private String password;
    private String authority;
}