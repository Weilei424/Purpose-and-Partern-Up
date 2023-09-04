package com.masonwang.pnp.web;

import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
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
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Operation(summary = "Get user by id", description = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user by id", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @Operation(summary = "Get user by username", description = "Get user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user by username", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Username does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@RequestBody String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Save an user", description = "Save user with valid info")
    @ApiResponse(responseCode = "201", description = "Successful save of user", content = @Content(schema = @Schema(implementation = User.class)))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an user", description = "Update an user as a whole user (id won't be changed)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of user", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @Operation(summary = "Update an user's password", description = "Update an user's password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of user's password", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}/pw", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserPassword(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserPassword(id, user.getPassword()), HttpStatus.OK);
    }

    @Operation(summary = "Update user's contact", description = "Update an user's contact'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of user's contact", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/{id}/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserContact(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserContact(id, user.getContact()), HttpStatus.OK);
    }

    @Operation(summary = "Delete an user", description = "Delete an user by id")
    @ApiResponse(responseCode = "200", description = "Successful deletion of user", content = @Content(schema = @Schema(implementation = User.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Get all users", description = "Get all users from the database")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all proposal from database", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Get all teams of user", description = "Get all teams of an user from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of all teams of user from database", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/{id}/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Team>> getUserTeams(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserTeams(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all proposals", description = "Get all proposals of an user from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of all proposals of user from database", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Proposal.class)))),
            @ApiResponse(responseCode = "404", description = "User id does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/{id}/proposals", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proposal>> getUserProposals(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserProposals(id), HttpStatus.OK);
    }
}
