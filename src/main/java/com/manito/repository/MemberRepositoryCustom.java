package com.manito.repository;

import com.manito.controller.response.MemberManitoGroupDto;

import java.util.List;

public interface MemberRepositoryCustom {
    MemberManitoGroupDto search(Long id);
    List<MemberManitoGroupDto> searchAll(String groupId);
}
