package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    Team getTeam(Long id);
    Team saveTeam(Team team);
    void deleteTeam(Long id);
    Team updateTeam(Long id, Team team);
    Team addMember(Long teamId, Long userId);

}
