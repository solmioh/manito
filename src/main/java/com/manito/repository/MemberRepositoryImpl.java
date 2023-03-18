package com.manito.repository;

import com.manito.controller.response.MemberManitoGroupDto;
import com.manito.controller.response.QMemberManitoGroupDto;
import com.manito.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.manito.entity.QGroup.group;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public MemberManitoGroupDto search(Long id) {
        var member = new QMember("member");
        var manito = new QMember("manito");

        return queryFactory
                .select(new QMemberManitoGroupDto(
                        member.name,
                        member.nickname,
                        manito.name.as("manitoName"),
                        manito.nickname.as("manitoNickname"),
                        group.id.as("groupId")))
                .from(member)
                .leftJoin(member.manito, manito)
                .leftJoin(member.group, group)
                .where(
                        memberIdEq(member, id)
                )
                .fetchOne();
    }

    @Override
    public List<MemberManitoGroupDto> searchAll(String groupId) {
        var member = new QMember("member");
        var manito = new QMember("manito");

        return queryFactory
                .select(new QMemberManitoGroupDto(
                        member.name,
                        member.nickname,
                        manito.name.as("manitoName"),
                        manito.nickname.as("manitoNickname"),
                        group.id.as("groupId")))
                .from(member)
                .leftJoin(member.manito, manito)
                .leftJoin(member.group, group)
                .where(
                        groupIdEq(groupId)
                )
                .fetch();
    }

    private BooleanExpression memberIdEq(QMember member, Long id) {
        return id != null ? member.id.eq(id) : null;
    }

    private BooleanExpression groupIdEq(String groupId) {
        return hasText(groupId) ? group.id.eq(groupId) : null;
    }
}
