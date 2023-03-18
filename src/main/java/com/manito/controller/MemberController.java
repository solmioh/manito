package com.manito.controller;

import com.manito.Status;
import com.manito.controller.request.MemberWriteRequest;
import com.manito.controller.response.MemberManitoGroupDto;
import com.manito.entity.Group;
import com.manito.entity.Member;
import com.manito.service.GroupQueryService;
import com.manito.service.GroupWriteService;
import com.manito.service.MemberQueryService;
import com.manito.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberWriteService memberWriteService;
    private final GroupQueryService groupQueryService;
    private final GroupWriteService groupWriteService;

    @PostMapping("")
    public HttpStatus participate(@RequestBody MemberWriteRequest request) {
        var group = groupQueryService.findGroup(request.getGroupId());

        if(!isStatusPreparing(group)) {
            return HttpStatus.BAD_REQUEST;
        }

        var member = request.toMember(group);
        var httpStatus = memberWriteService.participate(member);

        if(isAllParticipated(group)) {
            memberWriteService.assignManito(group);
            groupWriteService.updateStatus(group, Status.PROCEEDING);
        }

        return httpStatus;
    }

    @GetMapping("/{id}")
    public MemberManitoGroupDto findDto(@PathVariable("id") Long id) {
        return memberQueryService.findMemberManitoGroup(id);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        Member member = memberQueryService.findMember(id);

        if(member != null) {
            memberWriteService.delete(member);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }

    private static boolean isStatusPreparing(Group group) {
        return group.getStatus() == Status.PREPAIRING;
    }

    private static boolean isAllParticipated(Group group) {
        return group.getMembers().size() == group.getParticipants();
    }

}
