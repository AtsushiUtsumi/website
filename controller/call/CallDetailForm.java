package com.au.jiro.controller.call;

import java.util.List;

import lombok.Data;

@Data
class CallDetailForm {

    private String abura;

    private String karame;

    private String ninniku;

    private String yasai;

    private List<CallDetailRow> rows;
}
