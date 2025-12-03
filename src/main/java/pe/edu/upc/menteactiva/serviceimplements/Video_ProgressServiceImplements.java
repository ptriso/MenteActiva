package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.Video_ProgressRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.Video_ProgressResponseDTO;
import pe.edu.upc.menteactiva.entities.Clients;
import pe.edu.upc.menteactiva.entities.Video_Progress;
import pe.edu.upc.menteactiva.entities.Videos;
import pe.edu.upc.menteactiva.repositories.ClientRepository;
import pe.edu.upc.menteactiva.repositories.VideoRepository;
import pe.edu.upc.menteactiva.repositories.Video_ProgressRepository;
import pe.edu.upc.menteactiva.services.Video_ProgressService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Video_ProgressServiceImplements implements Video_ProgressService {

    @Autowired
    private Video_ProgressRepository video_progressRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Video_ProgressResponseDTO create(Video_ProgressRequestDTO dto) {

        Clients client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Videos video = videoRepository.findById(dto.getVideoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video no encontrado"));

        Video_Progress vp = video_progressRepository
                .findByClientAndVideo(client, video)
                .orElseGet(() -> {
                    Video_Progress nuevo = new Video_Progress();
                    nuevo.setClient(client);
                    nuevo.setVideo(video);
                    nuevo.setPercentage(0L);
                    nuevo.setCurrent_time(0);
                    nuevo.setCompleted(false);
                    nuevo.setViews_count(0);
                    return nuevo;
                });

        Long nuevoPorcentaje = (dto.getPercentage() != null)
                ? dto.getPercentage()
                : vp.getPercentage();

        vp.setPercentage(nuevoPorcentaje);

        if (dto.getCurrent_time() != null) {
            vp.setCurrent_time(dto.getCurrent_time());
        }

        if (dto.getCompleted() != null) {
            vp.setCompleted(dto.getCompleted());
        } else {
            vp.setCompleted(nuevoPorcentaje != null && nuevoPorcentaje >= 100);
        }

        Integer currentViews = (vp.getViews_count() != null) ? vp.getViews_count() : 0;
        vp.setViews_count(currentViews + 1);

        vp = video_progressRepository.save(vp);

        Video_ProgressResponseDTO out = modelMapper.map(vp, Video_ProgressResponseDTO.class);
        out.setClientId(client.getId());
        out.setVideoId(video.getId());

        out.setVideoTitle(video.getTitle());
        out.setDuration(video.getDuration() + " min");

        if (video.getProfesional() != null) {
            String nombreCompleto = video.getProfesional().getName() + " " +
                    video.getProfesional().getLastname();
            out.setProfessionalName(nombreCompleto);
        }

        return out;
    }
    @Override
    public Video_ProgressResponseDTO update(Long id, Video_ProgressRequestDTO dto) {
        Video_Progress vp = video_progressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progreso no encontrado"));

        Clients client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        Videos video = videoRepository.findById(dto.getVideoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video no encontrado"));

        vp.setClient(client);
        vp.setVideo(video);

        Long percentage = dto.getPercentage() != null ? dto.getPercentage() : 0L;
        vp.setPercentage(percentage);

        vp.setCurrent_time(dto.getCurrent_time() != null ? dto.getCurrent_time() : 0);

        vp.setCompleted(percentage == 100L);

        vp.setViews_count(dto.getViews_count() != null ? dto.getViews_count() : 0);

        vp = video_progressRepository.save(vp);

        return mapToResponse(vp);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        if (!video_progressRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Progreso no encontrado");
        }
        video_progressRepository.hardDeleteById(id);
    }
    @Override
    public List<Video_ProgressResponseDTO> listAll() {
        return video_progressRepository.findAll()
                .stream()
                .map(vp -> {
                    Video_ProgressResponseDTO dto = modelMapper.map(vp, Video_ProgressResponseDTO.class);

                    if (vp.getClient() != null) {
                        dto.setClientId(vp.getClient().getId());
                    }
                    if (vp.getVideo() != null) {
                        dto.setVideoId(vp.getVideo().getId());
                        dto.setVideoTitle(vp.getVideo().getTitle());
                        dto.setDuration(vp.getVideo().getDuration() + " min");

                        if (vp.getVideo().getProfesional() != null) {
                            String nombreCompleto = vp.getVideo().getProfesional().getName() + " " +
                                    vp.getVideo().getProfesional().getLastname();
                            dto.setProfessionalName(nombreCompleto);
                        }
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public List<Video_ProgressResponseDTO> listByClient(Long clientId) {
        return video_progressRepository.findByClientId(clientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    private Video_ProgressResponseDTO mapToResponse(Video_Progress vp) {
        Video_ProgressResponseDTO dto = modelMapper.map(vp, Video_ProgressResponseDTO.class);

        if (vp.getClient() != null) {
            dto.setClientId(vp.getClient().getId());
        }
        if (vp.getVideo() != null) {
            dto.setVideoId(vp.getVideo().getId());

            dto.setVideoTitle(vp.getVideo().getTitle());
            dto.setDuration(String.valueOf(vp.getVideo().getDuration()) + " min");

            if (vp.getVideo().getProfesional() != null) {
                String nombreCompleto =
                        vp.getVideo().getProfesional().getName() + " " +
                                vp.getVideo().getProfesional().getLastname();
                dto.setProfessionalName(nombreCompleto);
            }
        }

        return dto;
    }

}
