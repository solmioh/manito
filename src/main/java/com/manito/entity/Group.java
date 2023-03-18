package com.manito.entity;

import com.manito.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Builder
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group extends BaseEntity {

    @Id
    @Column(name = "group_id")
    private String id;
    @Column(name = "admin_in")
    private String adminId;
    private int participants;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "group")
    private final List<Member> members = new ArrayList<>();

    public void changeStatus(Status status) {
        this.status = status;
    }
}
