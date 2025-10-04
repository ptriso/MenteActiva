package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.VideoRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.VideoResponseDTO;
import pe.edu.upc.menteactiva.services.VideoService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/registrar")
    public ResponseEntity<VideoResponseDTO> create(@Valid @RequestBody VideoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<VideoResponseDTO> listAll() {
        return videoService.listAll();
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<VideoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody VideoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(videoService.update(id, dto));
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<VideoResponseDTO> delete(@PathVariable Long id) {
        videoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
