package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface TeamService {
    Team getTeam(Long id);
    Team getTeamByName(String teamName);
    Team saveTeam(Long userId, Team team);
    void deleteTeam(Long id);
    Team updateTeam(Long id, Team team);
    Team updateTeamName(Long id, String name);
    Team updateTeamContact(Long id, String contact);
    Team addMember(Long teamId, Long userId);
    void deleteMember(Long teamId, Long userId);
    List<Proposal> getTeamProposals(Long id);
    Set<User> getTeamUsers(Long id);
}
