package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.ChatRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ChatResponseDTO;
import pe.edu.upc.menteactiva.entities.Chats;
import pe.edu.upc.menteactiva.services.ChatService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("registrar")
    public ResponseEntity<ChatResponseDTO> create(@Valid @RequestBody ChatRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.create(dto));
    }
    @GetMapping("/listartodos")
    public List<ChatResponseDTO> listAll(){
        return chatService.listAll();
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<ChatResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ChatRequestDTO dto){
        return ResponseEntity.ok(chatService.update(id, dto));
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        chatService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/cliente/{clientId}")
    public List<Chats> historialPorCliente(@PathVariable Long clientId) {
        return chatService.historialPorCliente(clientId);
    }
    @GetMapping("/historial/cliente/{clientId}")
    public ResponseEntity<List<Chats>> getHistorialPorCliente(@PathVariable Long clientId) {
        List<Chats> historial = chatService.historialPorCliente(clientId);
        return ResponseEntity.ok(historial);
    }
}
