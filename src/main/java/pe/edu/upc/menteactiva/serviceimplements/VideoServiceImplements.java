package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.VideoRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.NativeQuery_MostViewedVideosDTO;
import pe.edu.upc.menteactiva.dtos.responses.VideoResponseDTO;
import pe.edu.upc.menteactiva.entities.Profesionals;
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
    @Transactional
    public VideoResponseDTO create(VideoRequestDTO dto) {
        Profesionals prof = professionalsRepository
                .findById(dto.getProfessionalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"El profesional no existe"));

        Videos v = modelMapper.map(dto, Videos.class);
        v.setId(null);
        v.setProfesional(prof);
        v.setDuration(dto.getDuration());

        v = videoRepository.save(v);
        VideoResponseDTO out = modelMapper.map(v, VideoResponseDTO.class);
        out.setProfessionalId(v.getProfesional().getId()); // <- 1
        return out;
    }
    @Override
    public VideoResponseDTO update (Long id, VideoRequestDTO dto)
    {
        Videos v = videoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El video no existe"));
        if (dto.getProfessionalId() != null) {
            Profesionals prof = professionalsRepository.findById(dto.getProfessionalId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El profesional no existe"));
            v.setProfesional(prof);
        }
        v.setTitle(dto.getTitle());
        v.setDescription(dto.getDescription());
        v.setUrl(dto.getUrl());
        v.setDuration(dto.getDuration());

        Videos saved = videoRepository.save(v);

        VideoResponseDTO out = modelMapper.map(saved, VideoResponseDTO.class);
        out.setProfessionalId(saved.getProfesional().getId());
        return out;
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
        List<Videos> list = videoRepository.findAll();

        return list.stream().map(v -> {
            VideoResponseDTO dto = modelMapper.map(v, VideoResponseDTO.class);
            dto.setProfessionalId(
                    (v.getProfesional() != null) ? v.getProfesional().getId() : null
            );
            return dto;
        }).toList();
    }

    @Override
    public List<NativeQuery_MostViewedVideosDTO> getMostViewedVideos() {
        return videoRepository.getMostViewedVideos()
                .stream()
                .map(obj -> new NativeQuery_MostViewedVideosDTO(
                        (String) obj[0],                // titulo
                        (String) obj[1],                // autor
                        ((Number) obj[2]).longValue()   // total_vistas
                ))
                .toList();
    }


    @Override
    public List<Videos> top5MasLargosPorProfesional(Long professionalId) {
        return videoRepository.findTop5ByProfesional_IdOrderByDurationDesc(professionalId);
    }

    @Override
    public long contarPorProfesional(Long professionalId) {
        return videoRepository.countByProfesional_Id(professionalId);
    }
    @Override
    public List<Videos> buscarPorTitulo(String q) {
        return videoRepository.findByTitleContainingIgnoreCase(q);
    }
}
