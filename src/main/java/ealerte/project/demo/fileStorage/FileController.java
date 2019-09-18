package ealerte.project.demo.fileStorage;

import ealerte.project.demo.Model.AlertC;
import ealerte.project.demo.Model.AlertType;
import ealerte.project.demo.Model.Citizen;
import ealerte.project.demo.Model.Media;
import ealerte.project.demo.Repository.AlertCRepository;
import ealerte.project.demo.Repository.CitizenRepository;
import ealerte.project.demo.Repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class FileController {

    private final FileService fileService;
    private  MediaRepository mediaRepository;
    private AlertCRepository alertCRepository;
    private CitizenRepository citizenRepository;

    @Autowired
    public FileController(FileService fileService, MediaRepository mediaRepository, AlertCRepository alertCRepository, CitizenRepository citizenRepository) {
        this.fileService = fileService;
        this.mediaRepository = mediaRepository;
        this.alertCRepository = alertCRepository;
        this.citizenRepository = citizenRepository;
    }

    @PostMapping(value = "/api/files/{description},{alertType},{altitude},{longitude},{phoneNumber},{firstName},{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public void handleFileUpload(@RequestBody MultipartFile file ,@PathVariable String description,@PathVariable  String  alertType,@PathVariable String altitude,
                                 @PathVariable String longitude,@PathVariable String phoneNumber, @PathVariable String firstName,@PathVariable  String lastName) throws IOException {


        fileService.storeFile(file);
        Optional<Citizen> citizen=citizenRepository.findCitizenByPhoneNumber(phoneNumber);
        Citizen citizen1;
        if(!citizen.isPresent()) {
            System.out.println("hhhhhhhhhhh");
            citizen1 = new Citizen(phoneNumber, firstName, lastName);
            citizenRepository.save(citizen1);
        }
        else {
            System.out.println("rrrrrr");
            citizen1=citizen.get();
        }

        AlertType alertType1;
        if(alertType.equals("FIRE")) alertType1=AlertType.FIRE;
        else if(alertType.equals("FLOOD")) alertType1=AlertType.FLOOD;
        else if(alertType.equals("ACCIDENT")) alertType1=AlertType.ACCIDENT;
        else if(alertType.equals("AGGRESSION")) alertType1=AlertType.AGGRESSION;
        else alertType1=AlertType.EARTHEQUAKE;

        AlertC alertC=new AlertC(LocalDate.now(), LocalTime.now(),alertType1,description,Double.valueOf(altitude),Double.valueOf(longitude));
        alertC.setCitizen(citizen1);
        System.out.println(citizen);
        System.out.println(alertC);
        alertCRepository.save(alertC);
        System.out.println(file.getOriginalFilename());
        Media media=new Media(file.getOriginalFilename());
        media.setAlertC(alertC);
        mediaRepository.save(media);

    }

    @GetMapping(value="/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        System.out.println(filename);
        Resource file = fileService.loadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }


    @GetMapping(value="/getimage/{filename}")
    @ResponseBody
    public ResponseEntity<String> getImage(@PathVariable String filename)  {
        String image=null;
        Resource file = fileService.loadFile(filename);
        System.out.println(file);
        String encodeBase64 = null;
        String extension= "jpg";
        try {
            FileInputStream fileInputStream = new FileInputStream((File) file);
            byte[] bytes=new byte[(int) ((File) file).length()];
            fileInputStream.read(bytes);
            encodeBase64 = Base64.getEncoder().encodeToString(bytes);
            image="data:image/"+extension+";base64,"+encodeBase64;
        }catch(Exception e){
            System.out.println("errrrrror");
        }
        System.out.println(image);
        return new ResponseEntity<String>(image,HttpStatus.OK);
    }
}
