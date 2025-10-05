package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.StatusRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.StatusResponseDTO;
import pe.edu.upc.menteactiva.services.StatusService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @PostMapping("/registrar")
    public ResponseEntity<StatusResponseDTO> create(@Valid @RequestBody StatusRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(statusService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<StatusResponseDTO> listAll() {
        return statusService.listAll();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<StatusResponseDTO> update(@PathVariable Long id, @Valid @RequestBody StatusRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(statusService.update(id, dto));
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        statusService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
