package ealerte.project.demo.Config;

import ealerte.project.demo.Model.Acteur;
import ealerte.project.demo.Model.Admin;
import ealerte.project.demo.Repository.ActeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ActeurRepository acteurRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Acteur acteur= acteurRepository.findActeurByUsername(username);
        if(acteur==null) throw new UsernameNotFoundException("username not found zini");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(acteur instanceof Admin)
        ((ArrayList<GrantedAuthority>) authorities).add(new SimpleGrantedAuthority("ADMIN"));
        else         ((ArrayList<GrantedAuthority>) authorities).add(new SimpleGrantedAuthority("USER"));

        return new User(acteur.getUsername(), acteur.getPassword(), authorities);
    }
}
