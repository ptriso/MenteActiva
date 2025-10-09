package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.ProfessionalRequestDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_TotalCitasPorProfesionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.ProfessionalResponseDTO;
import pe.edu.upc.menteactiva.services.ProfessionalService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Professionals")
public class ProfessionalController {
    @Autowired
    private ProfessionalService professionalService;

    @PostMapping("/registrar")
    public ResponseEntity<ProfessionalResponseDTO> create(@Valid @RequestBody ProfessionalRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<ProfessionalResponseDTO> listAll() {
        return professionalService.listAll();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<ProfessionalResponseDTO> update (@PathVariable Long id, @Valid @RequestBody ProfessionalRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.update(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professionalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/CitasPorProfesional")
    public ResponseEntity<List<NativeQuery_TotalCitasPorProfesionalDTO>> countAppointmentsByProfessional() {
        return ResponseEntity.ok(professionalService.countAppointmentsByProfessional());
    }
}
