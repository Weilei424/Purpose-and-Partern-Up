package com.masonwang.pnp.web;

import com.masonwang.pnp.service.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/purpose")
public class PurposeController {
    private ProposalService proposalService;
}
