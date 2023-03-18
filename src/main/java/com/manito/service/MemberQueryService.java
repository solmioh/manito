package com.manito.service;

import com.manito.controller.response.MemberManitoGroupDto;
import com.manito.entity.Member;
import com.manito.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public MemberManitoGroupDto findMemberManitoGroup(Long id) {
        return memberRepository.search(id);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
