package com.ahmed.music.musica.rest;

import com.ahmed.music.musica.entity.User;
import com.ahmed.music.musica.exception.ResourceNotFoundException;
import com.ahmed.music.musica.exception.ServerException;
import com.ahmed.music.musica.response.SuccessResponse;
import com.ahmed.music.musica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ghonim
 */
@RestController
@RequestMapping("/api/v1/users/")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("get/{id}")
    public SuccessResponse<User> getUser(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, String.valueOf(id)));
        user.setPassword(null);
        return new SuccessResponse<>(user);
    }

    @PutMapping("delete/{id}")
    public SuccessResponse<Long> deleteUser(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, String.valueOf(id)));
        userService.deleteUser(id);
        return new SuccessResponse<>(user.getId());
    }

    @PutMapping("update")
    public SuccessResponse<User> updateUser(@RequestBody User user) {
        userService.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(User.class, String.valueOf(user.getId())));
        User savedUser = userService.updateUser(user);
        return new SuccessResponse<>(savedUser);
    }

    @PostMapping("create")
    public SuccessResponse<User> saveUser(@Valid @RequestBody User newUser) {
       User validUser= userService.findByEmail(newUser.getEmail());
       if (validUser !=null){
           throw new ServerException("email already exist");
       }
        User user = userService.save(newUser);
        user.setPassword(null);
        return new SuccessResponse<>(user);
    }
}