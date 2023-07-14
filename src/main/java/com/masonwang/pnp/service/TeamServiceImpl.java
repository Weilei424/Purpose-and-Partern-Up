package com.masonwang.pnp.service;

import com.masonwang.pnp.repository.TeamRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
}
