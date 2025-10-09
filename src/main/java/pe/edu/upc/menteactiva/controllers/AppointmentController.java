package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.querys.TopClientesResponseDTO;
import pe.edu.upc.menteactiva.dtos.querys.TopEspecialidadResponseDTO;
import pe.edu.upc.menteactiva.dtos.querys.TopProfesionalResponseDTO;
import pe.edu.upc.menteactiva.dtos.request.AppointmentRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentResponseDTO;
import pe.edu.upc.menteactiva.services.AppointmentService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/registrar")
    public ResponseEntity<AppointmentResponseDTO> register(@Valid @RequestBody AppointmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<AppointmentResponseDTO> listAll() {
        return appointmentService.listAll();
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<AppointmentResponseDTO>update (@PathVariable Long id, @Valid @RequestBody AppointmentRequestDTO dto) {
        return ResponseEntity.ok(appointmentService.update(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/proxima/{clientId}")
    public ResponseEntity<?> proximaCitaDeCliente(@PathVariable Long clientId) {
        return appointmentService.proximaCitaDeCliente(clientId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/ranking/top-profesionales-todas")
    public List<TopProfesionalResponseDTO> topProfesionalesTodas(
            @RequestParam(defaultValue = "5") int top) {
        return appointmentService.topProfesionalesMasCitas(top);
    }
    @GetMapping("/ranking/top-especialidades")
    public List<TopEspecialidadResponseDTO> topEspecialidades(@RequestParam(defaultValue="5") int top){
        return appointmentService.topEspecialidades(top);
    }
    @GetMapping("/ranking/top-clientes-todas")
    public List<TopClientesResponseDTO> topClientesTodas(
            @RequestParam(defaultValue = "5") int top) {
        return appointmentService.topClientesMasCitasTodas(top);
    }
}
