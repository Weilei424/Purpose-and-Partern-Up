package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface TeamService {
    Team getTeam(Long id);
    Team saveTeam(Team team);
    void deleteTeam(Long id);
    Team updateTeam(Long id, Team team);
    Team addMember(Long teamId, Long userId);
    void deleteMember(Long teamId, Long userId);
    List<Proposal> getTeamProposals();
    Set<User> getTeamUsers();
}
