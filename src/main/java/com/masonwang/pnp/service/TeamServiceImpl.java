package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.repository.TeamRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Override
    public Team getTeam(Long id) {
        return null;
    }

    @Override
    public Team saveTeam(Team team) {
        return null;
    }

    @Override
    public void deleteTeam(Long id) {

    }

    @Override
    public Team updateTeam(Long id, Team team) {
        return null;
    }

    @Override
    public Team addMember(Long teamId, Long userId) {
        return null;
    }
}
