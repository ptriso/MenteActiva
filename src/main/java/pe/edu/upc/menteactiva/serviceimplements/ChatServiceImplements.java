package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.ChatRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ChatResponseDTO;
import pe.edu.upc.menteactiva.entities.Appointments;
import pe.edu.upc.menteactiva.entities.Chats;
import pe.edu.upc.menteactiva.repositories.AppointmentRepository;
import pe.edu.upc.menteactiva.repositories.ChatRepository;
import pe.edu.upc.menteactiva.services.ChatService;

import java.util.List;

@Service
public class ChatServiceImplements implements ChatService {
    @Autowired
    private ChatRepository chatsRepository;

    @Autowired
    private AppointmentRepository appointmentsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChatResponseDTO create(ChatRequestDTO dto) {
        Appointments appointment = appointmentsRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        Chats chat = new Chats();
        chat.setAppointment(appointment);

        return modelMapper.map(chatsRepository.save(chat), ChatResponseDTO.class);
    }
    @Override
    public ChatResponseDTO update(Long id, ChatRequestDTO dto) {
        Chats chat = chatsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat no encontrado"));

        Appointments appointment = appointmentsRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        chat.setAppointment(appointment);

        return modelMapper.map(chatsRepository.save(chat), ChatResponseDTO.class);
    }
    @Override
    public void delete(Long id) {
        if (!chatsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat no encontrado");
        }
        chatsRepository.deleteById(id);
    }
    @Override
    public List<ChatResponseDTO> listAll() {
        return chatsRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, ChatResponseDTO.class))
                .toList();
    }
}
