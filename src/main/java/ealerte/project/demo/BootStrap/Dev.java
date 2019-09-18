package ealerte.project.demo.BootStrap;

import ealerte.project.demo.Model.*;
import ealerte.project.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private LocalisationURepository localisationURepository;
    @Autowired
    private LocalisationURepository localisationURepository1;
    private LocalisationURepository localisationURepository3;


    @Autowired
    private AlertSRepository alertSRepository;
    private InterventionRepository interventionRepository;
    private AgentRepository agentRepository;
    @Autowired
    private InterventionUnitRepository interventionUnitRepository;
    @Autowired
    private InterventionUnitRepository interventionUnitRepository1;
    @Autowired
    private InterventionUnitRepository interventionUnitRepository2;
    private InterventionUnitRepository interventionUnitRepository3;
    private InterventionUnitRepository interventionUnitRepository4;
    private InterventionUnitRepository interventionUnitRepository5;
    private InterventionUnitRepository interventionUnitRepository6;
    private InterventionUnitRepository interventionUnitRepository7;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Dev(AdminRepository adminRepository, SensorRepository sensorRepository, MediaRepository mediaRepository, AlertCRepository alertCRepository, CitizenRepository citizenRepository,
               AddressRepository addressRepository, LocalisationARepository localisationARepository, LocalisationURepository localisationURepository, LocalisationURepository localisationURepository1, LocalisationURepository localisationURepository3, AlertSRepository alertSRepository,
               InterventionRepository interventionRepository, AgentRepository agentRepository, LocalisationARepository localisationARepository1, InterventionUnitRepository interventionUnitRepository1, InterventionUnitRepository interventionUnitRepository2, InterventionUnitRepository interventionUnitRepository3, InterventionUnitRepository interventionUnitRepository4, InterventionUnitRepository interventionUnitRepository5, InterventionUnitRepository interventionUnitRepository6, InterventionUnitRepository interventionUnitRepository7) {

        this.adminRepository = adminRepository;
        this.sensorRepository = sensorRepository;
        this.mediaRepository = mediaRepository;
        this.alertCRepository = alertCRepository;
        this.citizenRepository = citizenRepository;
        this.addressRepository = addressRepository;
        this.localisationARepository = localisationARepository;
        this.localisationURepository = localisationURepository;
        this.localisationURepository1 = localisationURepository1;
        this.localisationURepository3 = localisationURepository3;
        this.alertSRepository = alertSRepository;
        this.interventionRepository = interventionRepository;
        this.agentRepository = agentRepository;
        this.localisationARepository1=localisationARepository1;
        this.interventionUnitRepository1 = interventionUnitRepository1;
        this.interventionUnitRepository2 = interventionUnitRepository2;
        this.interventionUnitRepository3 = interventionUnitRepository3;
        this.interventionUnitRepository4 = interventionUnitRepository4;
        this.interventionUnitRepository5 = interventionUnitRepository5;
        this.interventionUnitRepository6 = interventionUnitRepository6;
        this.interventionUnitRepository7 = interventionUnitRepository7;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initData();
    }
    public  void initData(){

        //Agents
        Agent agent= new Agent( "Nadir","Otman bey", "0487619200","nadir@scope.dz", "nadir",bCryptPasswordEncoder.encode("123"));
        Agent agent2= new Agent( "Brahim","Felouh", "0487619200","brahim@scope.dz" ,"ibrou",bCryptPasswordEncoder.encode("azer"));
        Agent agent3= new Agent( "Amine","Boubekri", "0487619200","amine@scope.dz" ,"bamine",bCryptPasswordEncoder.encode("azer"));
        Agent agent4= new Agent( "Brahim","Lamri", "0487619200","ibrahim@scope.dz", "lbrahim",bCryptPasswordEncoder.encode("azer"));
        Agent agent5= new Agent( "Bouchera","Bekkouche", "0487619200","bouchera@scope.dz", "bbouchera",bCryptPasswordEncoder.encode("azer"));

        //units
        InterventionUnit interventionUnit= new InterventionUnit(InterventionType.HOSPITALITY,35.1937168, -0.6515961,"Universitary Hospital Hassani Abdelkader, 22002 Sakya Al Hamra");
        InterventionUnit interventionUnit1= new InterventionUnit(InterventionType.HOSPITALITY,35.1946896, -0.6375403," EPSP SIDI YACINE rue 22,22002");
        InterventionUnit interventionUnit2= new InterventionUnit(InterventionType.HOSPITALITY,35.2066736,-0.6520304, "Établissement Public Hospitalier,23 22002, ZAWYA");
        InterventionUnit interventionUnit3= new InterventionUnit(InterventionType.GENDARMERIE,35.1901073,0.6399258, "La Macta, rue 53, 2200 Bab Daya");
        InterventionUnit interventionUnit4= new InterventionUnit(InterventionType.GENDARMERIE,35.1920461,-0.6465621, "Rue Halouch Cheikh, 2200 Londeu");
        InterventionUnit interventionUnit5= new InterventionUnit(InterventionType.GENDARMERIE,35.2013147,-0.6463597, "Tessala, 22000 Larbi Ben Mhidi");
        InterventionUnit interventionUnit6= new InterventionUnit(InterventionType.CIVIL_PROTECTION,35.2091319,-0.6612226, "Rue national 13, 22000 CPR");
        InterventionUnit interventionUnit7= new InterventionUnit(InterventionType.CIVIL_PROTECTION,35.1875892,-0.623087, "rue Mascara rue 23 22000 Sidi Yacine");
        InterventionUnit interventionUnit8= new InterventionUnit(InterventionType.POLICE_DIRECTION,35.217548,-0.6398527, "Commissariat 7eme, 22000 Larbi Ben Mhidi");
        InterventionUnit interventionUnit9= new InterventionUnit(InterventionType.POLICE_DIRECTION,35.2074128,-0.6275981, "Commissariat 5eme, 22000 Sidi Djilali");
        InterventionUnit interventionUnit11= new InterventionUnit(InterventionType.POLICE_DIRECTION,35.1809046,-0.6303235, "Commissariat Centrale, 22000 Makkam");




        //Citizens with Alerts
        AlertC alertC=new AlertC(LocalDate.now(), LocalTime.now(),AlertType.ACCIDENT,"There are people stuck into the cars!", 35.2175582,-0.6398529);
        Citizen citizen= new Citizen("0795813471","Chamaoui","imene");
        alertC.setAlertState(AlertState.VALIDE);
        alertC.setCitizen(citizen);
        alertC.setActeur(agent4);
        Media media=new Media("accident.jpg");
        media.setAlertC(alertC);


        AlertC alertC2=new AlertC(LocalDate.now(), LocalTime.now(),AlertType.AGGRESSION,"ne personne s'est évanoui soudainment dans le tram arrêt Adda Boudjlel", 35.2000853,-0.6609588);
        Citizen citizen1= new Citizen("0775489315","Moulay","ilyes");
        //alertC2.setAlertState(AlertState.INVALIDE);
        alertC2.setCitizen(citizen1);
        //alertC2.setActeur(agent2);
        Media media1=new Media("forest.jpg");
        media1.setAlertC(alertC2);




        Sensor sensor=new Sensor("RT301", SensorState.ACTIVE, SensorType.WATER_LEVEL,35.193658,-0.6444277);
        AlertS alertS= new AlertS(LocalDate.now(), LocalTime.now(), 21.0,35.193658,-0.6444277);
        alertS.setSensor(sensor);
        //alertS.setAlertState(AlertState.VALIDE);

        alertC.setCitizen(citizen);
        //sensor.addAlertS(alertS);
        Admin admin=new Admin("Imen", "Hakem","0487619200","imen@scope.dz","user",bCryptPasswordEncoder.encode("pass"));

        //alertS.setActeur(admin);
        adminRepository.save(admin);
        sensorRepository.save(sensor);
        citizenRepository.save(citizen); citizenRepository.save(citizen1);
        agentRepository.save(agent); agentRepository.save(agent2); agentRepository.save(agent3); agentRepository.save(agent4);agentRepository.save(agent5);
        interventionUnitRepository.save(interventionUnit);
        interventionUnitRepository.save(interventionUnit1);
       interventionUnitRepository.save(interventionUnit2);
       interventionUnitRepository.save(interventionUnit3);
       interventionUnitRepository.save(interventionUnit4);
       interventionUnitRepository.save(interventionUnit5);
       interventionUnitRepository.save(interventionUnit6);
       interventionUnitRepository.save(interventionUnit7);
        interventionUnitRepository.save(interventionUnit8);
        interventionUnitRepository.save(interventionUnit9);
        interventionUnitRepository.save(interventionUnit11);//localisationURepository.save(localisationU);

        alertSRepository.save(alertS);
        alertCRepository.save(alertC); alertCRepository.save(alertC2);
        mediaRepository.save(media);
        mediaRepository.save(media1);


      //interventionRepository.save(intervention);





    }

}
