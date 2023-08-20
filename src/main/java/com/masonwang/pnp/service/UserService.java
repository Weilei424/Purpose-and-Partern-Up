package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getUser(Long id);
    User saveUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> getUsers();
    Set<Team> getUserTeams(Long id);
    List<Proposal> getUserProposals(Long id);
}
