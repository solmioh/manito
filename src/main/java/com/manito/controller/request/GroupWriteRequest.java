package com.manito.controller.request;

import com.manito.Status;
import com.manito.entity.Group;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Random;

@Getter
public class GroupWriteRequest {
    private int participants;
    private LocalDate endDate;

    public Group toGroup() {
        return Group.builder()
                .id(generateId())
                .adminId(generateId())
                .participants(participants)
                .endDate(endDate)
                .status(Status.PREPAIRING)
                .build();
    }

    private static String generateId() {
        var range = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        var len = 5;

        var rnd = new Random();
        var sb = new StringBuilder(len);
        for(int i = 0; i < len; i++) {
            sb.append(range.charAt(rnd.nextInt(range.length())));
        }

        return sb.toString();
    }
}
