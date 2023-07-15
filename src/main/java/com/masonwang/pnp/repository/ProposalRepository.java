package com.masonwang.pnp.repository;

import com.masonwang.pnp.entity.Proposal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {
}
