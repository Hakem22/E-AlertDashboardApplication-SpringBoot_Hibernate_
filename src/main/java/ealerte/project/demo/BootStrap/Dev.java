package ealerte.project.demo.BootStrap;

import ealerte.project.demo.Model.*;
import ealerte.project.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class Dev implements ApplicationListener<ContextRefreshedEvent> {


    private AdminRepository adminRepository;
    private SensorRepository sensorRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private AlertCRepository alertCRepository;
    private CitizenRepository citizenRepository;
    private AddressRepository addressRepository;
    private LocalisationARepository localisationARepository;
    private LocalisationARepository localisationARepository1;
    private LocalisationURepository localisationURepository;
    @Autowired
    private AlertSRepository alertSRepository;
    private InterventionRepository interventionRepository;
    private AgentRepository agentRepository;
    @Autowired
    private InterventionUnitRepository interventionUnitRepository;

    public Dev(AdminRepository adminRepository, SensorRepository sensorRepository, MediaRepository mediaRepository, AlertCRepository alertCRepository, CitizenRepository citizenRepository,
               AddressRepository addressRepository, LocalisationARepository localisationARepository, LocalisationURepository localisationURepository, AlertSRepository alertSRepository,
               InterventionRepository interventionRepository, AgentRepository agentRepository, LocalisationARepository localisationARepository1) {

        this.adminRepository = adminRepository;
        this.sensorRepository = sensorRepository;
        this.mediaRepository = mediaRepository;
        this.alertCRepository = alertCRepository;
        this.citizenRepository = citizenRepository;
        this.addressRepository = addressRepository;
        this.localisationARepository = localisationARepository;
        this.localisationURepository = localisationURepository;
        this.alertSRepository = alertSRepository;
        this.interventionRepository = interventionRepository;
        this.agentRepository = agentRepository;
        this.localisationARepository1=localisationARepository1;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initData();
    }
    public  void initData(){

        Agent agent= new Agent( "Nadir","Otman bey", "0487619200", "Nadir31","1123");
        Address address=new Address("Aissat idir", "23","22002","Sidi Yacine");
        LocalisationU localisationU=new LocalisationU(45.98, 67.976, 7.99);
        InterventionUnit interventionUnit= new InterventionUnit(InterventionType.GENDARMERIE,localisationU, address);
        LocalisationA localisationA=new LocalisationA( 23.999,2.98,3.09);
        LocalisationA localisationA1= new LocalisationA( 50.999,0.98,9.09);
      //  Media media=new Media("lien");


        //Intervention intervention=new Intervention(new InterventionKey((long)1,(long)1), "22-04-2009","12:00", new Alert());
        AlertC alertC=new AlertC(LocalDate.now(), LocalTime.now(),AlertType.FIRE,"hhhhh", localisationA1);
        Citizen citizen= new Citizen("0795813471");
        Sensor sensor=new Sensor("H123", SensorState.ACTIVE, SensorType.WATER_LEVEL,3.4,4.7);
        AlertS alertS= new AlertS(LocalDate.now(), LocalTime.now(), 2.1,localisationA);
        alertS.setSensor(sensor);
        alertC.setCitizen(citizen);
        //sensor.addAlertS(alertS);
        Admin admin=new Admin("Imen", "Hakem","0487619200","Imen22","Imen1995@");

        alertS.setActeur(admin);
        adminRepository.save(admin);
        sensorRepository.save(sensor);
        citizenRepository.save(citizen);
        agentRepository.save(agent);
        interventionUnitRepository.save(interventionUnit);
        addressRepository.save(address);
        localisationURepository.save(localisationU);
        alertSRepository.save(alertS);
        alertCRepository.save(alertC);
        localisationARepository.save(localisationA);
        localisationARepository1.save(localisationA1);
       // mediaRepository.save(media);


      //interventionRepository.save(intervention);





    }

}
