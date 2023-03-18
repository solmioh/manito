package com.manito.controller.request;

import com.manito.entity.Group;
import com.manito.entity.Member;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MemberWriteRequest {
    private String name;
    private String nickname;
    private String groupId;

    public Member toMember(Group group) {
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .group(group)
                .build();
    }
}
