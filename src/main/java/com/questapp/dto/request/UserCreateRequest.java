package com.questapp.dto.request;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String username;
    private String password;
}
