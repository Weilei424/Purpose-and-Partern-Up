package com.masonwang.pnp.web;

import com.masonwang.pnp.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;
}
