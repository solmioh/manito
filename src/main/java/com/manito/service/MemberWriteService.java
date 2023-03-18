package com.manito.service;

import com.manito.entity.Group;
import com.manito.entity.Member;
import com.manito.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;

    public HttpStatus participate(Member member) {
        var savedMember = memberRepository.save(member);

        if(savedMember == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.OK;
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

    @Transactional
    public void assignManito(Group group) {
        var members = group.getMembers();
        var shuffledMembers = shuffle(members);

        for(int i=0;i<members.size();i++) {
            var member = members.get(i);
            var manito = shuffledMembers.get(i);
            member.setManito(manito);
        }
    }

    private List<Member> shuffle(List<Member> members) {
        List<Member> shuffledMembers = new ArrayList<>();

        var size = members.size();
        var index = 0;
        while(index != size) {
            var rnd = new Random().nextInt(size);
            var member = members.get(rnd);

            if(isDuplicated(shuffledMembers, member)) {
                continue;
            }

            if(index != rnd) {
                shuffledMembers.add(member);
                index++;
            }
        }
        return shuffledMembers;
    }

    private static boolean isDuplicated(List<Member> shuffledMembers, Member member) {
        return shuffledMembers.contains(member);
    }
}
