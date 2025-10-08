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
    public Video_ProgressResponseDTO create(Video_ProgressRequestDTO dto) {
        Clients client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        Videos video = videoRepository.findById(dto.getVideoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video no encontrado"));

        var existing = video_progressRepository.findByClientAndVideo(client, video);
        if (existing.isPresent()) {
            var out = modelMapper.map(existing.get(), Video_ProgressResponseDTO.class);
            out.setClientId(client.getId());
            out.setVideoId(video.getId());
            return out;
        }

        Video_Progress vp = new Video_Progress();
        vp.setId(null);
        vp.setClient(client);
        vp.setVideo(video);
        vp.setPercentage(dto.getPercentage() != null ? dto.getPercentage() : 0L);
        vp.setCurrent_time(dto.getCurrent_time() != null ? dto.getCurrent_time() : 0);
        vp.setCompleted(dto.getCompleted() != null ? dto.getCompleted() : false);
        vp.setViews_count(dto.getViews_count() != null ? dto.getViews_count() : 0);

        vp = video_progressRepository.save(vp);

        var out = modelMapper.map(vp, Video_ProgressResponseDTO.class);
        out.setClientId(client.getId());
        out.setVideoId(video.getId());
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

        vp.setPercentage(dto.getPercentage());
        vp.setCurrent_time(dto.getCurrent_time());
        vp.setCompleted(dto.getCompleted());
        vp.setViews_count(dto.getViews_count());

        vp = video_progressRepository.save(vp);

        Video_ProgressResponseDTO out = modelMapper.map(vp, Video_ProgressResponseDTO.class);
        out.setClientId(client.getId());
        out.setVideoId(video.getId());
        return out;
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
                    dto.setClientId(vp.getClient() != null ? vp.getClient().getId() : null);
                    dto.setVideoId(vp.getVideo()  != null ? vp.getVideo().getId()  : null);
                    return dto;
                })
                .toList();
    }
}
