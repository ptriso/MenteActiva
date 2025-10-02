package pe.edu.upc.menteactiva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.menteactiva.entities.User_Authority;
import pe.edu.upc.menteactiva.services.User_AuthorityService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/User_Authority")
public class User_AuthorityController {

    @Autowired
    User_AuthorityService user_AuthorityService;

    @GetMapping("/listartodos")
    public ResponseEntity<List<User_Authority>> GetAllUser_Authorities(){
        List<User_Authority> user_authorities = user_AuthorityService.GetAllUser_Authorities();
        return new ResponseEntity<>(user_authorities, HttpStatus.OK);
    }
}
