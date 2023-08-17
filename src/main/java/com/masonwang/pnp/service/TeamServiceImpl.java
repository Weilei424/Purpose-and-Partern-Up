package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.exception.EntityNotFoundException;
import com.masonwang.pnp.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Override
    public Team getTeam(Long id) {
        return unwrapTeam(teamRepository.findById(id), id);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Team updateTeam(Long id, Team team) {
        return null;
    }

    @Override
    public Team addMember(Long teamId, Long userId) {
        return null;
    }

    @Override
    public void deleteMember(Long teamId, Long userId) {

    }

    @Override
    public List<Proposal> getTeamProposals(Long id) {
        return null;
    }

    @Override
    public Set<User> getTeamUsers(Long id) {
        return null;
    }

    static Team unwrapTeam(Optional<Team> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Team.class);
    }
}
