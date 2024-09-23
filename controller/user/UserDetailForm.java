package com.au.jiro.controller.user;

import java.util.List;

import lombok.Data;

@Data
class UserDetailForm {

    private String age;

    private String email;

    private String id;

    private String name;

    private List<UserDetailRow> rows;
}
