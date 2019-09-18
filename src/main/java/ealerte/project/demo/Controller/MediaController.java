package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.Media;
import ealerte.project.demo.Repository.MediaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MediaController {

    private MediaRepository mediaRepository;

    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @GetMapping("/medias")
    public List<Media> getMedias() {
        return (List<Media>) mediaRepository.findAll();
    }
    @PostMapping("/media")
    public void addMedia(@RequestBody Media media){
        mediaRepository.save(media);
    }
    @GetMapping("/media/{id}")
    public Media retrieveAdmin(@PathVariable long id) {
        Optional<Media> media = mediaRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
        return media.get();
    }
    @DeleteMapping("/media/{id}")
    public void deleteMedia(@PathVariable long id) {
        mediaRepository.deleteById(id);
    }

    @PutMapping("/media/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestBody Media media, @PathVariable long id) {

        Optional<Media> mediaOptional = mediaRepository.findById(id);

        if (!mediaOptional.isPresent())  return ResponseEntity.notFound().build();

        media.setId(id);
        mediaRepository.save(media);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/uri/{id}")
    public String getURI(@PathVariable Long id) {
        Optional<Media> media=mediaRepository.findByAlertCId(id);
        return media.get().getURI();
    }

}
