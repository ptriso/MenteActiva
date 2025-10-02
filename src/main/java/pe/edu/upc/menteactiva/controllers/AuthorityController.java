package pe.edu.upc.menteactiva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.menteactiva.entities.Authority;
import pe.edu.upc.menteactiva.services.AuthorityService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @GetMapping("/listartodos")
    public ResponseEntity<List<Authority>> GetAllAuthorities(){
        List<Authority> authorities = authorityService.GetAllAuthorities();
        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }
}
