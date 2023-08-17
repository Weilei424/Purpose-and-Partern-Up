package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

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


}
