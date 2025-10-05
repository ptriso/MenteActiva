package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AuthorityResponseDTO;
import pe.edu.upc.menteactiva.services.AuthorityService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @PostMapping("/registrar")
    public ResponseEntity<AuthorityResponseDTO> registrar(@Valid @RequestBody AuthorityRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorityService.create(dto));
    }


    @GetMapping("/listartodos")
    public List<AuthorityResponseDTO> listall(){
        return authorityService.GetAllAuthorities();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<AuthorityResponseDTO> update (@PathVariable Long id, @Valid @RequestBody AuthorityRequestDTO dto) {
        return ResponseEntity.ok(authorityService.update(id, dto));
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        authorityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
