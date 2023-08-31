package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.exception.EntityNotFoundException;
import com.masonwang.pnp.repository.ProposalRepository;
import com.masonwang.pnp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ProposalRepository proposalRepository;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User u = getUser(id);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setContact(user.getContact());
        return userRepository.save(u);
    }

    @Override
    public User updateUserPassword(Long id, String password) {
        User u = getUser(id);
        u.setPassword(password);
        return userRepository.save(u);
    }

    @Override
    public User updateUserContact(Long id, String contact) {
        User u = getUser(id);
        u.setContact(contact);
        return userRepository.save(u);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Set<Team> getUserTeams(Long id) {
        User user = getUser(id);
        return user.getTeams();
    }

    @Override
    public List<Proposal> getUserProposals(Long id) {

        return proposalRepository.findByUserId(id);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
}
