package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.User_AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.User_AuthorityResponseDTO;
import pe.edu.upc.menteactiva.entities.User_Authority;
import pe.edu.upc.menteactiva.services.User_AuthorityService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/User_Authority")
public class User_AuthorityController {

    @Autowired
    User_AuthorityService user_AuthorityService;


    @PostMapping("/registrar")
    public ResponseEntity<User_AuthorityResponseDTO> create(@Valid @RequestBody User_AuthorityRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user_AuthorityService.create(dto));
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<User_AuthorityResponseDTO> update(@PathVariable Long id, @Valid @RequestBody User_AuthorityRequestDTO dto) {
        return ResponseEntity.ok(user_AuthorityService.update(id, dto));
    }

    @GetMapping("/listartodos")
    public List<User_AuthorityResponseDTO> listAll() {
        return user_AuthorityService.listAll();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        user_AuthorityService.remove(id);
        return ResponseEntity.ok().build();
    }
}
