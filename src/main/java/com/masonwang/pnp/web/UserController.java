package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Operation(summary = "Get user by id", description = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user by id", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @Operation(summary = "Get user by username", description = "Get user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user by username", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Username does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestBody String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Save an user", description = "Save user with valid info")
    @ApiResponse(responseCode = "201", description = "Successful save of user", content = @Content(schema = @Schema(implementation = User.class)))
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
