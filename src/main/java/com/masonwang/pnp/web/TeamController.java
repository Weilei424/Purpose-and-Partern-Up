package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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

    @PostMapping
    public ResponseEntity<Team> saveTeam(@RequestBody Team team) {
        return new ResponseEntity<>(teamService.saveTeam(team), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
