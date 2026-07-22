package dev.rabbit.fila.user.controller;

import dev.rabbit.fila.user.domain.UserModel;
import dev.rabbit.fila.user.dto.UserDto;
import dev.rabbit.fila.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Users", description = "Operations related to users")
@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user", description = "Creates a user and publishes the event for sending email.")
    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserDto userDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveAndPublish(userModel));
    }

    @Operation(summary = "List all users", description = "Returns a list of all registered users.")
    @GetMapping("list/users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "List user from Id", description = "Retorn user registered.")
    @GetMapping
    public ResponseEntity<UserModel> getUserById(@RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Deletion user from Id", description = "Deleted user resgistered.")
    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(@RequestParam("userId") UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update user from Id", description = "Update user Registered")
    @PutMapping
    public ResponseEntity<UserDto> updateUserById(@RequestParam("userId") UUID userId, @RequestBody UserDto userDto) {
        UserDto updated = userService.updateUserById(userId, userDto);
        return ResponseEntity.ok(updated);
    }
}
