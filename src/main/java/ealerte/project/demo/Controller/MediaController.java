package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.MediaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MediaController {

    private MediaRepository mediaRepository;

    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @RequestMapping("/medias")
    public String getMedias(Model model){
        model.addAttribute("medias", mediaRepository.findAll());
        return "medias";
    }

}
