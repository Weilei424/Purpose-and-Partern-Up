package com.masonwang.pnp.repository;

import com.masonwang.pnp.entity.Purpose;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurposeRepository extends CrudRepository<Purpose, Long> {
}
