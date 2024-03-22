package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.User;
import com.basic.niroj.backend_social_media.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ustomUserDetailsService  implements UserDetailsService {



    @Autowired
    private userRepository userrepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user=userrepository.findByEmail(username);

               if(user==null){
                   throw new UsernameNotFoundException("User not found");
               }
           List<GrantedAuthority> authorities= AuthorityUtils.createAuthorityList("ROLE_USER");


                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);


    }
}
