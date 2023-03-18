package com.manito.controller;

import com.manito.controller.request.GroupWriteRequest;
import com.manito.controller.response.GroupDto;
import com.manito.service.GroupWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupWriteService groupWriteService;

    @PostMapping("/groups")
    public GroupDto createGroup(@RequestBody GroupWriteRequest request) {
        return groupWriteService.create(request.toGroup());
    }

    @GetMapping("/hc")
    public String healthCheck() {
        return "OK";
    }

}
