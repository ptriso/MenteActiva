package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.SchedulesRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.SchedulesResponseDTO;
import pe.edu.upc.menteactiva.services.SchedulesService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Schedules")
public class SchedulesController {

    @Autowired
    private SchedulesService schedulesService;

    @PostMapping("/registrar")
    public ResponseEntity<SchedulesResponseDTO> create(@Valid @RequestBody SchedulesRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schedulesService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<SchedulesResponseDTO> listAll() {
        return schedulesService.listAll();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<SchedulesResponseDTO> update(@PathVariable Long id, @Valid @RequestBody SchedulesRequestDTO dto) {
        return ResponseEntity.ok(schedulesService.update(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        schedulesService.delete(id);
        return ResponseEntity.ok().build();
    }

}