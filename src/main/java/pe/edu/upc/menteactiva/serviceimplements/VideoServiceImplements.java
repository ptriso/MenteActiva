package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.VideoRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.VideoResponseDTO;
import pe.edu.upc.menteactiva.entities.Videos;
import pe.edu.upc.menteactiva.repositories.ProfessionalsRepository;
import pe.edu.upc.menteactiva.repositories.VideoRepository;
import pe.edu.upc.menteactiva.services.VideoService;

import java.util.List;

@Service
public class VideoServiceImplements  implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ProfessionalsRepository professionalsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VideoResponseDTO create (VideoRequestDTO dto)
    {
        if(!professionalsRepository.existsById(dto.getProfessionalId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El professional no existe");
        }
        Videos v = modelMapper.map(dto, Videos.class);
        v.setId(null);
        return modelMapper.map(videoRepository.save(v), VideoResponseDTO.class);
    }
    @Override
    public VideoResponseDTO update (Long id, VideoRequestDTO dto)
    {
        Videos v = videoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El video no existe"));
        if(!professionalsRepository.existsById(dto.getProfessionalId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El professional no existe");
        }
        v.setTitle(dto.getTitle());
        v.setDescription(dto.getDescription());
        v.setUrl(dto.getUrl());
        v.setDuration(dto.getDuration());

        return modelMapper.map(videoRepository.save(v), VideoResponseDTO.class);
    }
    @Override
    public void delete(Long id)
    {
        if(!videoRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El video no existe");
        }
        videoRepository.deleteById(id);
    }

    @Override
    public List<VideoResponseDTO> listAll() {
        return videoRepository.findAll().stream().map( v-> modelMapper.map(v, VideoResponseDTO.class)).toList();
    }
}
