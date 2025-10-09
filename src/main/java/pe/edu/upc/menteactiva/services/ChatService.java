package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.ChatRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ChatResponseDTO;
import pe.edu.upc.menteactiva.entities.Chats;

import java.util.List;

public interface ChatService {
    ChatResponseDTO create(ChatRequestDTO dto);
    ChatResponseDTO update(Long id, ChatRequestDTO dto);
    void delete(Long id);
    List<ChatResponseDTO> listAll();
    List<Chats> historialPorCliente(Long clientId);
}
