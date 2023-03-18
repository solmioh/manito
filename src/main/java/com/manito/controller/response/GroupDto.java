package com.manito.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class GroupDto {
    private String groupId;
    private String adminId;
    private int participants;
    private LocalDate endDate;
}
