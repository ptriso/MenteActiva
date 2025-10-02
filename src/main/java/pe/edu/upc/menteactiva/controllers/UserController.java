package pe.edu.upc.menteactiva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.services.UserService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/User")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/listartodos")
    public ResponseEntity<List<User>> GetAllUsers(){
        List<User> users = userService.GetAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
