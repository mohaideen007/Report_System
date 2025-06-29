package com.example.Report.Report_Users.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Report.Report_Users.Model.user_data;
import com.example.Report.Report_Users.Service.user_service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class User_Controller {
    @Autowired
    private user_service serv;

	@GetMapping("/")
	public String returnmu() {
		return "hello";
	}

    @PostMapping("/adduser")
    public user_data adduser(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("file") MultipartFile file) throws IOException {
      
        return serv.addusers(username,password,file);
    }

    @PostMapping("/login")
    public String verifyuser(@RequestBody user_data userdata) {
          return serv.login(userdata);
    }
    @PostMapping("/sendmail/{mail}")
    public String sending(@PathVariable("mail") String mail) {    
        return serv.sendemail(mail);
    }
    
    
    
}
