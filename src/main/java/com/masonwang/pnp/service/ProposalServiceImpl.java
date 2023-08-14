package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.exception.EntityNotFoundException;
import com.masonwang.pnp.repository.ProposalRepository;
import com.masonwang.pnp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProposalServiceImpl implements ProposalService {

    private ProposalRepository proposalRepository;
    private UserRepository userRepository;

    @Override
    public Proposal getProposal(Long id) {
        Optional<Proposal> proposal = proposalRepository.findById(id);
        return unwrapProposal(proposal, id);
    }

    @Override
    public Proposal saveProposal(Long userId, Proposal proposal) {
        User user = UserServiceImpl.unwrapUser(userRepository.findById(userId), userId);
        user.getProposals().add(proposal);
        return proposalRepository.save(proposal);
    }

    @Override
    public void deleteProposal(Long id) {
        //todo: security check admin only
        proposalRepository.deleteById(id);
    }

    @Override
    public Proposal updateProposal(Long id, Proposal proposal) {
        Proposal p = unwrapProposal(proposalRepository.findById(id), id);
        p.setDecription(proposal.getDecription());
        p.setName(proposal.getName());
        p.setUser(proposal.getUser());
        return proposalRepository.save(p);
    }

    @Override
    public List<Proposal> getProposals() {
        return (List<Proposal>) proposalRepository.findAll();
    }

    @Override
    public Set<Proposal> getTeamProposals(Long teamId) {
        //todo
        return null;
    }

    @Override
    public Set<Proposal> getUserProposals(Long userId) {
        //todo
        return null;
    }

    static Proposal unwrapProposal(Optional<Proposal> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Proposal.class);
    }

}