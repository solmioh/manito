package com.manito.repository;

import com.manito.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom  {
    Optional<Member> findByGroupIdAndNickname(String groupId, String nickname);
}
