package vn.hoidanit.jobhunter.controller;


import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/user/create")
    @PostMapping("/user")
    public User createNewUser(
            @RequestBody User postmanUser
    ) {
        User newUser = this.userService.handleCreateUser(postmanUser);
        return newUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        this.userService.handleDeleteUser(id);
        return "delete user";
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(name = "id") Long id){

        User userExisted = this.userService.fetchUserById(id);

        return userExisted;
    }

    @GetMapping("/user")
    public List<User> getAllUser(){

        List<User> listUsers = this.userService.fetchAllUser();

        return listUsers;
    }

    @PutMapping("/user")
    public User updateAUser(@RequestBody User userUpdate){
        User user = this.userService.updateAUser(userUpdate);

        return user;
    }

}
