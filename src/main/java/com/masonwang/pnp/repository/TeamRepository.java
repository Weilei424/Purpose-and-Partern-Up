package com.masonwang.pnp.repository;

import com.masonwang.pnp.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<Team> findByName(String teamName);
}
