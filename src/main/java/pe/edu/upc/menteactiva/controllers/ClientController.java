package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.ClientRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ClientResponseDTO;
import pe.edu.upc.menteactiva.services.ClientService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/registrar")
    public ResponseEntity<ClientResponseDTO> create (@Valid @RequestBody ClientRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(dto));
    }

    @GetMapping("listartodos")
    public List<ClientResponseDTO> listAll(){ return clientService.listAll();}

    @PutMapping("/modificar/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO dto)
    {
        return ResponseEntity.ok(clientService.update(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
