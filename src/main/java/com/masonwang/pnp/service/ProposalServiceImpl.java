package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.repository.ProposalRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private ProposalRepository proposalRepository;

    @Override
    public Proposal getProposal(Long id) {
        return proposalRepository.findById(id).get();
    }

    @Override
    public Proposal saveProposal(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    @Override
    public void deleteProposal(Long id) {
        proposalRepository.deleteById(id);
    }

    @Override
    public Proposal updateProposal(Long id, Proposal proposal) {
        //todo
        return null;
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

    static Proposal unwrapProposal(Optional<Proposal> entity, Long studentId, Long courseId) {
        //todo
        if (entity.isPresent()) return entity.get();
        else throw new RuntimeException();
    }

}