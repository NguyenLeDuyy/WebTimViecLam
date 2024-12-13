package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void handleDeleteUser(Long id){
        this.userRepository.deleteById(id);
    }

    public User fetchUserById(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isPresent()){
            return userOptional.get();
        }
        return null;

    }

    public List<User> fetchAllUser(){
        return this.userRepository.findAll();
    }

    public User updateAUser(User reqUser){
        User userExisted = fetchUserById(reqUser.getId());
        if (userExisted != null){
            userExisted.setEmail(reqUser.getEmail());
            userExisted.setName(reqUser.getName());
            userExisted.setPassword(reqUser.getPassword());
            //update
            return this.userRepository.save(reqUser);
        }
        return null;
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }
}
