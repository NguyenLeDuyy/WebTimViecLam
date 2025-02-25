package vn.hoidanit.jobhunter.controller;


import com.turkraft.springfilter.boot.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/users/create")
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User postmanUser) {
        String hashPassword = this.passwordEncoder.encode(postmanUser.getPassword());
        postmanUser.setPassword(hashPassword);
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
    @ApiMessage("fetch all users")
    public ResponseEntity<ResultPaginationDTO> getAllUser(
        @Filter Specification<User> spec,
        Pageable pageable){

        ResultPaginationDTO rs = this.userService.fetchAllUser(spec, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(rs);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateAUser(@RequestBody User userUpdate){
        User user = this.userService.updateAUser(userUpdate);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.ok(user);
    }
}
