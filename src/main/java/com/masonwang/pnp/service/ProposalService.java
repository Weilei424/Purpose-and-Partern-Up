package com.masonwang.pnp.service;

import com.masonwang.pnp.entity.Proposal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProposalService {
    Proposal getProposal(Long id);
    Proposal saveProposal(Long userId, Proposal proposal);
    void deleteProposal(Long id);
    Proposal updateProposal(Long id, Proposal proposal);
    List<Proposal> getProposals();
    Set<Proposal> getTeamProposals(Long teamId);
    Set<Proposal> getUserProposals(Long userId);
}
