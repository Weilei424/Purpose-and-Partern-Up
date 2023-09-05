package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;

    @Operation(summary = "Get team by id", description = "Get team by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of team by id", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Team id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> getTeam(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeam(id), HttpStatus.OK);
    }

    @Operation(summary = "Get team by team name", description = "Get team by team name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of team by team name", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Team id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> getTeamByName(@RequestBody String name) {
        return new ResponseEntity<>(teamService.getTeamByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Save a team", description = "Save user with valid info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful save of team", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "400", description = "Invalid json", content = @Content(schema = @Schema(implementation = com.masonwang.pnp.exception.ErrorResponse.class))),
    })
    @PostMapping(value = "/addBy/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> saveTeam(@PathVariable Long teamId, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.saveTeam(teamId, team), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a team", description = "Delete a team by id")
    @ApiResponse(responseCode = "204", description = "Successful deletion of team (no deletion will happen if id does not exist)")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.updateTeam(id, team), HttpStatus.OK);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Team> updateTeamName(@PathVariable Long id, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.updateTeamName(id, team.getName()), HttpStatus.OK);
    }

    @PutMapping("/{id}/contact")
    public ResponseEntity<Team> updateTeamContact(@PathVariable Long id, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.updateTeamContact(id, team.getContact()), HttpStatus.OK);
    }

    @PutMapping("/{teamId}/user/{userId}")
    public ResponseEntity<Team> addMember(@PathVariable Long teamId, @PathVariable Long userId) {
        return new ResponseEntity<>(teamService.addMember(teamId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}/user/{userId}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable Long teamId, @PathVariable Long userId) {
        teamService.deleteMember(teamId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/proposals")
    public ResponseEntity<List<Proposal>> getTeamProposals(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeamProposals(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<Set<User>> getTeamUsers(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeamUsers(id), HttpStatus.OK);
    }
}
