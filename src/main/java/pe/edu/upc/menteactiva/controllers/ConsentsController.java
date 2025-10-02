package pe.edu.upc.menteactiva.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.ConsentsRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ConsentsResponseDTO;
import pe.edu.upc.menteactiva.services.ConsentsService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Consents")
public class ConsentsController {

    @Autowired
    private ConsentsService consentsService;

    @PostMapping("/registrar")
    public ResponseEntity<ConsentsResponseDTO> create(@Valid @RequestBody ConsentsRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(consentsService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<ConsentsResponseDTO> listAll()
    {
        return consentsService.listAll();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<ConsentsResponseDTO>  update(@PathVariable Long id, @Valid @RequestBody ConsentsRequestDTO dto) {
        return ResponseEntity.ok(consentsService.update(id, dto));
    }
    @DeleteMapping("/eliminar/{id}")
    public  ResponseEntity<ConsentsResponseDTO>  delete(@PathVariable Long id)
    {
        consentsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
