package com.example.Report.Report_Users.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Report.Report_Users.Model.user_data;
import com.example.Report.Report_Users.Repo.user_repo;

@Service
public class user_service {
    @Autowired
    private user_repo repo;

    @Autowired
    private Jwt_service jwtserv;


    @Autowired
    private AuthenticationManager authmanag;

    @Autowired
    private JavaMailSender mailserv;

    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public user_data addusers(String username,String password,MultipartFile file) throws IOException {
        String path="uploads/";
        String name=username+".jpg";
        Path paths=Paths.get(path, name);

        Files.createDirectories(paths.getParent());
        Files.write(paths,file.getBytes());

        
        user_data userda=new user_data();
        userda.setUsername(username);
        userda.setUserphoto(paths.toString());
        if(username.equals("shifa")){
            userda.setRole("ADMIN");
        }
        else{
        userda.setRole("USER");
        }
        userda.setPassword(encoder.encode(password));
        repo.save(userda);
        return userda;
    }
    public String login(user_data userdata) {
        Authentication auth = authmanag.authenticate(
            new UsernamePasswordAuthenticationToken(userdata.getUsername(), userdata.getPassword())
        );
        if(auth.isAuthenticated()){
            user_data user=repo.findByUsername(userdata.getUsername());
            return jwtserv.generatetoken(user);
        }
        return "Bye";
    }
    public String sendemail(String mail) {

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("mohaideensheik777@gmail.com");
        message.setTo(mail);
        message.setSubject("Confimation EmailVerification");
        message.setText("This is from Report System WebApplication...Thanks For Registering Our WOnderful Application");
        mailserv.send(message);
                return "Email Sent Successfully";
    }
    
}
