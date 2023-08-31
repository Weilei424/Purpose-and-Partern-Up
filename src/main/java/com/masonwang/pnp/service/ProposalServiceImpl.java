package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.exception.EntityNotFoundException;
import com.masonwang.pnp.repository.ProposalRepository;
import com.masonwang.pnp.repository.TeamRepository;
import com.masonwang.pnp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProposalServiceImpl implements ProposalService {

    private ProposalRepository proposalRepository;
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @Override
    public Proposal getProposal(Long id) {
        Optional<Proposal> proposal = proposalRepository.findById(id);
        return unwrapProposal(proposal, id);
    }

    @Override
    public Proposal saveProposal(Long userId, Long teamId, Proposal proposal) {
        User user = UserServiceImpl.unwrapUser(userRepository.findById(userId), userId);
        Team team = TeamServiceImpl.unwrapTeam(teamRepository.findById(teamId), teamId);
        user.getProposals().add(proposal);
        team.getProposals().add(proposal);
        proposal.setUser(user);
        proposal.setTeam(team);
        return proposalRepository.save(proposal);
    }

    @Override
    public void deleteProposal(Long id) {
        //todo: security check admin only
        proposalRepository.deleteById(id);
    }

    @Override
    public void deleteUserProposal(Long proposalId, Long userId) {
        proposalRepository.deleteByIdAndUserId(proposalId, userId);
    }

    @Override
    public Proposal updateProposal(Long id, Proposal proposal) {
        Proposal p = unwrapProposal(proposalRepository.findById(id), id);
        p.setDescription(proposal.getDescription());
        p.setName(proposal.getName());
        p.setTeam(proposal.getTeam());
        return proposalRepository.save(p);
    }

    @Override
    public Proposal updateProposalName(Long id, String name) {
        Proposal p = unwrapProposal(proposalRepository.findById(id), id);
        p.setName(name);
        return proposalRepository.save(p);
    }

    @Override
    public Proposal updateProposalDescription(Long id, String description) {
        Proposal p = unwrapProposal(proposalRepository.findById(id), id);
        p.setDescription(description);
        return proposalRepository.save(p);
    }

    @Override
    public Proposal updateProposalTeam(Long id, Team team) {
        Proposal p = unwrapProposal(proposalRepository.findById(id), id);
        p.setTeam(team);
        return proposalRepository.save(p);
    }

    @Override
    public List<Proposal> getProposals() {
        return (List<Proposal>) proposalRepository.findAll();
    }

    static Proposal unwrapProposal(Optional<Proposal> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Proposal.class);
    }

}