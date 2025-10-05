package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.Video_ProgressRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.Video_ProgressResponseDTO;
import pe.edu.upc.menteactiva.services.Video_ProgressService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/Video_Progress")
public class Video_ProgressController {

    @Autowired
    private Video_ProgressService video_progressService;

    @PostMapping("/registrar")
    public ResponseEntity<Video_ProgressResponseDTO> create(@Valid @RequestBody Video_ProgressRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(video_progressService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<Video_ProgressResponseDTO> listAll(){ return video_progressService.listAll(); }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Video_ProgressResponseDTO> update(@PathVariable Long id, @Valid @RequestBody Video_ProgressRequestDTO dto){
        return ResponseEntity.ok(video_progressService.update(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        video_progressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
