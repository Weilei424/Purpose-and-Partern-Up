package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.service.TeamService;
import com.masonwang.pnp.service.UserService;
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
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;
    private UserService userService;

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

    @Operation(summary = "Save a team", description = "Save team under an user with valid info (creator will be automatically added to the team)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful save of team", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "400", description = "Invalid json", content = @Content(schema = @Schema(implementation = com.masonwang.pnp.exception.ErrorResponse.class))),
    })
    @PostMapping(value = "/addBy/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> saveTeam(@PathVariable Long userId, @RequestBody Team team) {
        User user = userService.getUser(userId);

        return new ResponseEntity<>(teamService.saveTeam(team, user), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a team", description = "Delete a team by id")
    @ApiResponse(responseCode = "204", description = "Successful deletion of team (no deletion will happen if id does not exist)")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a team", description = "Update a team as a whole team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of team", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Invalid team id", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.updateTeam(id, team), HttpStatus.OK);
    }

    @Operation(summary = "Update a team name", description = "Update a team's name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of team name", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Invalid team id", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> updateTeamName(@PathVariable Long id, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.updateTeamName(id, team.getName()), HttpStatus.OK);
    }

    @Operation(summary = "Update a team contact", description = "Update a team's contact'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of team contact", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Invalid team id", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> updateTeamContact(@PathVariable Long id, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.updateTeamContact(id, team.getContact()), HttpStatus.OK);
    }

    @Operation(summary = "Add user to team", description = "Add an existing user to an existing team by ids")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful addition of user to team", content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "Invalid input id(s)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{teamId}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> addMember(@PathVariable Long teamId, @PathVariable Long userId) {
        return new ResponseEntity<>(teamService.addMember(teamId, userId), HttpStatus.OK);
    }

    @Operation(summary = "Delete a team member", description = "Delete a member from team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful removal of an user from a team"),
            @ApiResponse(responseCode = "404", description = "Invalid input or user/team does not match", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/{teamId}/user/{userId}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable Long teamId, @PathVariable Long userId) {
        teamService.deleteMember(teamId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all proposals of the team", description = "Get all proposals of the team by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of all proposals of the team", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Proposal.class)))),
            @ApiResponse(responseCode = "404", description = "Invalid team id", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/{id}/proposals", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proposal>> getTeamProposals(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeamProposals(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all users of the team", description = "Get all users of the team by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of all users of the team", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "404", description = "Invalid team id", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/{id}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> getTeamUsers(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeamUsers(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all teams", description = "Get all teams from database")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of teams", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Team>> getTeams() {
        return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    }
}
