package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.AddressRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {
    private AddressRepository addressRepository;

    @RequestMapping("/addresses")
    public String getAddresses(Model model){
        model.addAttribute("addresses", addressRepository.findAll());
        return "addresses";
    }

    @GetMapping("/address/{addressId}")
    public ModelAndView showAddress(@PathVariable("addressId") long addressId){
        ModelAndView mav=new ModelAndView();
        mav.addObject(this.addressRepository.findById(addressId));
        return mav;
    }
}
