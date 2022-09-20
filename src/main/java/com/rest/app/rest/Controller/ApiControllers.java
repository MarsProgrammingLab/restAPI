package com.rest.app.rest.Controller;

import com.rest.app.rest.Models.User;
import com.rest.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This annotation tells the IDE that this class is a rest controller
public class ApiControllers {

    @Autowired // handles all dependency injections
    private UserRepo userRepo;

    @GetMapping(value = "/") // "/" retrieves default endpoint when application is executed
    public String getPage(){
        return "Welcome";
    }

    @GetMapping(value = "/users") // retrieves users endpoint
    public List<User> getUsers(){ // This function will get a list of users
        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){ // This function takes in object of type user
        userRepo.save(user);
        return "Saved..";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);

        return "Updated..";

    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Deleted user with the id: " + id;

    }
}
