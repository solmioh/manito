package com.manito.controller.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.manito.controller.response.QMemberManitoGroupDto is a Querydsl Projection type for MemberManitoGroupDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberManitoGroupDto extends ConstructorExpression<MemberManitoGroupDto> {

    private static final long serialVersionUID = -2042081770L;

    public QMemberManitoGroupDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> manitoName, com.querydsl.core.types.Expression<String> manitoNickname, com.querydsl.core.types.Expression<String> groupId) {
        super(MemberManitoGroupDto.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class}, name, nickname, manitoName, manitoNickname, groupId);
    }

}

