package com.masonwang.pnp.repository;

import com.masonwang.pnp.entity.Proposal;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    Optional<Proposal> findByUserIdAndTeamId(Long userId, Long teamId);
    List<Proposal> findByUserId(Long userId);
    List<Proposal> findByTeamId(Long teamId);
    @Transactional
    void deleteByUserIdAndTeamId(Long userId, Long teamId);
}
