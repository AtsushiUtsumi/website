package com.au.jiro.controller.call;

import java.util.List;

import com.au.jiro.controller.cache.UserCacheParam;

import lombok.Data;

@Data
class CallListForm {

    // 検索条件
    @UserCacheParam
    private String abura;

    @UserLogParam
    private String karame;

    @UserCacheParam
    private String ninniku;

    @UserLogParam
    private String yasai;

    private List<CallListRow> rows;
}
