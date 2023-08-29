package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.exception.EntitiesNotMatchException;
import com.masonwang.pnp.exception.EntityNotFoundException;
import com.masonwang.pnp.repository.TeamRepository;
import com.masonwang.pnp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private UserRepository userRepository;

    @Override
    public Team getTeam(Long id) {
        return unwrapTeam(teamRepository.findById(id), id);
    }

    @Override
    public Team getTeam(String teamName) {
        Optional<Team> team = teamRepository.findByName(teamName);
        return unwrapTeam(team, 404L);
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
        Team t = getTeam(id);
        t.setName(team.getName());

        return teamRepository.save(t);
    }

    @Override
    public Team addMember(Long teamId, Long userId) {
        Team team = getTeam(teamId);
        User user = UserServiceImpl.unwrapUser(userRepository.findById(userId), userId);
        team.getUsers().add(user);

        return teamRepository.save(team);
    }

    @Override
    public void deleteMember(Long teamId, Long userId) {
        Team team = getTeam(teamId);
        User user = UserServiceImpl.unwrapUser(userRepository.findById(userId), userId);
        if (!team.getUsers().remove(user)) throw new EntitiesNotMatchException(userId, User.class, teamId, Team.class);
        teamRepository.save(team);
    }

    @Override
    public List<Proposal> getTeamProposals(Long id) {
        Team team = getTeam(id);

        return team.getProposals();
    }

    @Override
    public Set<User> getTeamUsers(Long id) {
        Team team = getTeam(id);

        return team.getUsers();
    }

    static Team unwrapTeam(Optional<Team> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Team.class);
    }
}
