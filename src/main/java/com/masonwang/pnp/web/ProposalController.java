package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.service.ProposalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/proposal")
public class ProposalController {
    private ProposalService proposalService;

    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the proposal by id", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Proposal.class)))),
            @ApiResponse(responseCode = "404", description = "Proposal does not exist"),
    })
    @Operation(summary = "Get proposal by id", description = "Return a proposal based on an id")
    @GetMapping("/{id}")
    public ResponseEntity<Proposal> getProposal(@PathVariable Long id) {
        return new ResponseEntity<>(proposalService.getProposal(id), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/team/{teamId}")
    public ResponseEntity<Proposal> saveProposal(@PathVariable Long userId, @PathVariable Long teamId, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.saveProposal(userId, teamId, proposal), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProposal(@PathVariable Long id) {
        proposalService.deleteProposal(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{proposalId}/{userId}")
    public ResponseEntity<HttpStatus> deleteUserProposal(@PathVariable Long proposalId, @PathVariable Long userId) {
        proposalService.deleteUserProposal(proposalId, userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proposal> updateProposal(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposal(id, proposal), HttpStatus.OK);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Proposal> updateProposalName(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposalName(id, proposal.getName()), HttpStatus.OK);
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<Proposal> updateProposalDescription(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposalDescription(id, proposal.getDescription()), HttpStatus.OK);
    }

    @PutMapping("/{id}/team")
    public ResponseEntity<Proposal> updateProposalTeam(@PathVariable Long id, @RequestBody Proposal proposal) {
        //todo unfinished
        return new ResponseEntity<>(proposalService.updateProposalTeam(id, proposal.getTeam()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Proposal>> getProposals() {
        return new ResponseEntity<>(proposalService.getProposals(), HttpStatus.OK);
    }

}
