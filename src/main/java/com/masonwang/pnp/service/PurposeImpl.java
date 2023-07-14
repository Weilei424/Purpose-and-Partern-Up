package com.masonwang.pnp.service;

import com.masonwang.pnp.repository.PurposeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PurposeImpl implements PurposeService {
    private PurposeRepository purposeRepository;
}
