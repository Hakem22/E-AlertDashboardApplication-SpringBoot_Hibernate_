package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.AdminRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController  {
    private AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @RequestMapping("/admins")
    public String getAdmins(Model model){
        model.addAttribute("admins", adminRepository.findAll());
        return "admins";
    }

    @GetMapping("/admin/{adminId}")
    public ModelAndView showAdmin(@PathVariable("adminId") long adminId){
        ModelAndView mav=new ModelAndView();
        mav.addObject(this.adminRepository.findById(adminId));
        return mav;
    }
}
