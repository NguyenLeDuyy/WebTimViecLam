package vn.hoidanit.jobhunter.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.service.error.IdInvalidException;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/users/create")
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(
            @RequestBody User postmanUser
    ) {
        User newUser = this.userService.handleCreateUser(postmanUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }



    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) throws IdInvalidException {

        if (id >= 1500){
            throw new IdInvalidException("Id không lớn hơn 1500");
        }

        this.userService.handleDeleteUser(id);
//        return ResponseEntity.status(HttpStatus.OK).body("delete user");
        return ResponseEntity.ok("delete user");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id){

        User userExisted = this.userService.fetchUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(userExisted);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> listUsers = this.userService.fetchAllUser();

        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateAUser(@RequestBody User userUpdate){
        User user = this.userService.updateAUser(userUpdate);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.ok(user);

    }
}
