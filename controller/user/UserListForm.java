package com.au.jiro.controller.user;

import java.util.List;

import lombok.Data;

@Data
class UserListForm {

    // 検索条件
    private String age;

    private String email;

    private String id;

    private String name;

    private List<UserListRow> rows;
}
