package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @PutMapping("/{id}/pw")
    public ResponseEntity<User> updateUserPassword(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserPassword(id, user.getPassword()), HttpStatus.OK);
    }

    @PutMapping("/{id}/contact")
    public ResponseEntity<User> updateUserContact(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserContact(id, user.getContact()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<Set<Team>> getUserTeams(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserTeams(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/proposals")
    public ResponseEntity<List<Proposal>> getUserProposals(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserProposals(id), HttpStatus.OK);
    }
}
