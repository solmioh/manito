package com.manito.service;

import com.manito.Status;
import com.manito.entity.Group;
import com.manito.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupQueryService {

    private final GroupRepository groupRepository;

    public Group findGroup(String groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public List<Group> findByStatus(Status status) {
        List<Group> byStatus = groupRepository.findByStatus(status);
        System.out.println("groupRepository.findByStatus(status) = " + byStatus);
        return byStatus;
    }
}
