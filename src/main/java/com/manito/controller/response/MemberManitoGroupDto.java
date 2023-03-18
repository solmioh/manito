package com.manito.controller.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberManitoGroupDto {
    private String name;
    private String nickname;
    private String manitoName;
    private String manitoNickname;
    private String groupId;

    @QueryProjection
    public MemberManitoGroupDto(String name, String nickname, String manitoName, String manitoNickname, String groupId) {
        this.name = name;
        this.nickname = nickname;
        this.manitoName = manitoName;
        this.manitoNickname = manitoNickname;
        this.groupId = groupId;
    }
}
