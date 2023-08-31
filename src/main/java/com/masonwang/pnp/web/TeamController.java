package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeam(id), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<Team> getTeam(@RequestBody String name) {
        return new ResponseEntity<>(teamService.getTeamByName(name), HttpStatus.OK);
    }

    @PostMapping("/addBy/{userId}")
    public ResponseEntity<Team> saveTeam(@PathVariable Long userId, @RequestBody Team team) {
        return new ResponseEntity<>(teamService.saveTeam(userId, team), HttpStatus.CREATED);
    }

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
