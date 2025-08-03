package com.example.Report.Report_Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Report.Report_Users.Model.user_data;
import com.example.Report.Report_Users.Repo.user_repo;

@Service
public class MyUserdetailsService implements UserDetailsService{

    @Autowired
    private user_repo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       user_data userdata=repo.findByUsername(username);
       if(userdata==null){
        throw new UsernameNotFoundException(username);
       }
       else{
        return new Userprincipal(userdata);
       }
    }

}
