package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.Session_SummariesRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.Session_SummariesResponseDTO;
import pe.edu.upc.menteactiva.services.Session_SummariesService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Session_Summaries")
public class Session_SummariesController {

    @Autowired
    private Session_SummariesService sessionSummariesService;

    @PostMapping("registrar")
    public ResponseEntity<Session_SummariesResponseDTO> create(@Valid @RequestBody Session_SummariesRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionSummariesService.create(dto));
    }
    @GetMapping("/listartodos")
    public List<Session_SummariesResponseDTO> listAll() {
        return sessionSummariesService.listAll();
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Session_SummariesResponseDTO> update(@PathVariable Long id, @Valid @RequestBody Session_SummariesRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(sessionSummariesService.update(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sessionSummariesService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<Session_SummariesResponseDTO> getByAppointment(
            @PathVariable Long appointmentId) {

        Session_SummariesResponseDTO dto =
                sessionSummariesService.findByAppointmentId(appointmentId);

        return ResponseEntity.ok(dto);
    }
    @PutMapping("/appointment/{appointmentId}")
    public ResponseEntity<Session_SummariesResponseDTO> saveOrUpdateByAppointment(
            @PathVariable Long appointmentId,
            @Valid @RequestBody Session_SummariesRequestDTO dto
    ) {
        Session_SummariesResponseDTO resp = sessionSummariesService
                .saveOrUpdateByAppointment(appointmentId, dto);
        return ResponseEntity.ok(resp);
    }
}
