package com.manito.service;

import com.manito.Status;
import com.manito.controller.response.GroupDto;
import com.manito.entity.Group;
import com.manito.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupWriteService {

    private final GroupQueryService groupQueryService;
    private final GroupRepository groupRepository;

    public GroupDto create(Group group) {
        var savedGroup = groupRepository.save(group);

        return GroupDto.builder()
                .groupId(savedGroup.getId())
                .adminId(group.getAdminId())
                .endDate(group.getEndDate())
                .participants(group.getParticipants())
                .build();
    }

    @Transactional
    public void updateStatus(Group group, Status status) {
        group.changeStatus(status);
    }

    @Transactional
    public void checkEndDate(List<Group> groups) {
        for(int i=0;i<groups.size();i++) {
            updateStatus(groups.get(i), Status.FINISHED);
        }
    }
}
