package ealerte.project.demo;

import ealerte.project.demo.fileStorage.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootApplication
@EnableCaching
public class DemoApplication implements CommandLineRunner {

    @Resource
    FileService storageService;

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder getBCPE(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }

}