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

    @Operation(summary = "Get proposal by id", description = "Return a proposal based on an id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the proposal by id", content = @Content(schema = @Schema(implementation = Proposal.class))),
            @ApiResponse(responseCode = "404", description = "Proposal does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> getProposal(@PathVariable Long id) {
        return new ResponseEntity<>(proposalService.getProposal(id), HttpStatus.OK);
    }

    @Operation(summary = "Save a proposal", description = "Save proposal with required info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful save of the proposal", content = @Content(schema = @Schema(implementation = Proposal.class))),
            @ApiResponse(responseCode = "404", description = "Team or/and User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping(value = "/user/{userId}/team/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> saveProposal(@PathVariable Long userId, @PathVariable Long teamId, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.saveProposal(userId, teamId, proposal), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a proposal", description = "Delete a proposal by proposal id")
    @ApiResponse(responseCode = "204", description = "Deletion done (no deletion will happen if id does not exist)")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteProposal(@PathVariable Long id) {
        proposalService.deleteProposal(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a proposal", description = "Delete a proposal by proposal id and user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful deletion of proposal"),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping(value = "/{proposalId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteUserProposal(@PathVariable Long proposalId, @PathVariable Long userId) {
        proposalService.deleteUserProposal(proposalId, userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a proposal", description = "Update a proposal as a whole proposal(id won't be changed)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated a proposal", content = @Content(schema = @Schema(implementation = Proposal.class))),
            @ApiResponse(responseCode = "404", description = "Proposal id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposal(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposal(id, proposal), HttpStatus.OK);
    }

    @Operation(summary = "Update the name of a proposal", description = "Update the name of a proposal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the name of the proposal", content = @Content(schema = @Schema(implementation = Proposal.class))),
            @ApiResponse(responseCode = "404", description = "Proposal id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposalName(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposalName(id, proposal.getName()), HttpStatus.OK);
    }

    @Operation(summary = "Update the description of a proposal", description = "Update the description of a proposal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the description of the proposal", content = @Content(schema = @Schema(implementation = Proposal.class))),
            @ApiResponse(responseCode = "404", description = "Proposal id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}/description", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposalDescription(@PathVariable Long id, @RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalService.updateProposalDescription(id, proposal.getDescription()), HttpStatus.OK);
    }

    @Operation(summary = "Update the team of a proposal", description = "Update the team of a proposal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the team of the proposal", content = @Content(schema = @Schema(implementation = Proposal.class))),
            @ApiResponse(responseCode = "404", description = "Proposal id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}/team", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> updateProposalTeam(@PathVariable Long id, @RequestBody Proposal proposal) {
        //todo unfinished
        return new ResponseEntity<>(proposalService.updateProposalTeam(id, proposal.getTeam()), HttpStatus.OK);
    }

    @Operation(summary = "Get all proposals", description = "Get all proposals from the database")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all proposal from database", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Proposal.class))))
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proposal>> getProposals() {
        return new ResponseEntity<>(proposalService.getProposals(), HttpStatus.OK);
    }

}
