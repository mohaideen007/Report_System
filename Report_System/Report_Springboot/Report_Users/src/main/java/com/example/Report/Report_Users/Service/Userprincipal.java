package com.example.Report.Report_Users.Service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Report.Report_Users.Model.user_data;

public class Userprincipal implements UserDetails{
    private user_data userdata;

    public Userprincipal(user_data userdata) {
       this.userdata=userdata;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singleton(new SimpleGrantedAuthority(userdata.getRole()));
    }

    @Override
    public String getPassword() {
        return userdata.getPassword();
    }

    @Override
    public String getUsername() {
        return userdata.getUsername();
    }

}
