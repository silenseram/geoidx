package com.ewmw.addr.http.controllers;

import com.ewmw.addr.exceptions.RecordNotFoundException;
import com.ewmw.addr.models.User;
import com.ewmw.addr.models.reposotories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {
    @Autowired
    UsersRepository repository;

    @GetMapping("")
    public List<User> index() {
        return (List<User>) repository.findAll();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable Long id) throws RecordNotFoundException {
        return findOrThrow(id);
    }

    @PatchMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User user) throws RecordNotFoundException {
        User requestedUser = findOrThrow(id);

        requestedUser.setFirstName(user.getFirstName());
        requestedUser.setLastName(user.getLastName());
        requestedUser.setEmail(user.getEmail());

        repository.save(requestedUser);

        return requestedUser;
    }

    protected User findOrThrow(Long id) throws RecordNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, User.MODEL_NAME));
    }

}
