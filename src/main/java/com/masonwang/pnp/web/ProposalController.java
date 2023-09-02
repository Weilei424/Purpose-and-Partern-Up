package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.exception.ErrorResponse;
import com.masonwang.pnp.service.ProposalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/proposal")
public class ProposalController {
    private ProposalService proposalService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the proposal by id", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Proposal.class)))),
            @ApiResponse(responseCode = "404", description = "Proposal does not exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))),
    })
    @Operation(summary = "Get proposal by id", description = "Return a proposal based on an id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> getProposal(@PathVariable Long id) {
        return new ResponseEntity<>(proposalService.getProposal(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful save of the proposal"),
            @ApiResponse(responseCode = "404", description = "Team or/and User id does not exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))),
    })
    @Operation(summary = "Save a proposal", description = "Save proposal with required info")
    @PostMapping(value = "/user/{userId}/team/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> saveProposal(@PathVariable Long userId, @PathVariable Long teamId, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.saveProposal(userId, teamId, proposal), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteProposal(@PathVariable Long id) {
        proposalService.deleteProposal(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{proposalId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteUserProposal(@PathVariable Long proposalId, @PathVariable Long userId) {
        proposalService.deleteUserProposal(proposalId, userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposal(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposal(id, proposal), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposalName(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposalName(id, proposal.getName()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/description", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposalDescription(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposalDescription(id, proposal.getDescription()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/team", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposalTeam(@PathVariable Long id, @RequestBody Proposal proposal) {
        //todo unfinished
        return new ResponseEntity<>(proposalService.updateProposalTeam(id, proposal.getTeam()), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proposal>> getProposals() {
        return new ResponseEntity<>(proposalService.getProposals(), HttpStatus.OK);
    }

}
