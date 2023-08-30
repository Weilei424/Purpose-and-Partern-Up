package com.masonwang.pnp.repository;

import com.masonwang.pnp.entity.Proposal;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    List<Proposal> findByUserId(Long userId);
    List<Proposal> findByTeamId(Long teamId);
    @Transactional
    void deleteByIdAndUserId(Long proposalId, Long userId);
}
