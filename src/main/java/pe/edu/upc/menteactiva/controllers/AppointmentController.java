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
import pe.edu.upc.menteactiva.dtos.responses.AppointmentClientResponseDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentProfessionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentResponseDTO;
import pe.edu.upc.menteactiva.enums.StatusAp;
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
    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<List<AppointmentClientResponseDTO>> listByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(appointmentService.listByClientId(clientId));
    }
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        appointmentService.cancel(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/profesional/{idProfessional}/citas")
    public List<AppointmentProfessionalDTO> listByProfessional(@PathVariable Long idProfessional) {
        return appointmentService.listByProfessional(idProfessional);
    }

    @GetMapping("/profesional/{idProfessional}/proximas")
    public List<AppointmentProfessionalDTO> listUpcoming(@PathVariable Long idProfessional) {
        return appointmentService.listUpcomingByProfessional(idProfessional);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestParam("status") StatusAp statusAp
    ) {
        appointmentService.updateStatus(id, statusAp);
        return ResponseEntity.ok().build();
    }
}
