package ealerte.project.demo.SendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @GetMapping("/simpleemail/{email}")
    @ResponseBody
    public String home(@PathVariable String email) {
        try {
            System.out.println("hi");
            sendEmail(email);
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }

    private void sendEmail(String email) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo("hakem.imene0@gmail.com");
        helper.setText("Actor with "+email+" has authentification issue due to password forget");
        helper.setSubject("Authentification Failed");
        System.out.println("hiooooo");

        sender.send(message);
    }
}

