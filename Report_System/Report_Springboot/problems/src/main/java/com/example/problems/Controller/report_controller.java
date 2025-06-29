package com.example.problems.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.problems.Model.report_dao;
import com.example.problems.Service.report_service;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/reports")
public class report_controller {


	@Autowired
	private report_service serv;
	
	@GetMapping("/")
	public String hello() {
		return "hii";
	}

	@PostMapping("/addreport")
	@PreAuthorize("hasRole('ROLE_USER')")
	public report_dao postMethodName(@RequestParam("username") String username, @RequestParam("file")MultipartFile file, @RequestParam("description")  String description) throws IOException {
				return serv.addreport(username, file, description);
	}
	@GetMapping("/seereport/{date}")
	public List<report_dao> viewreport( @PathVariable("date") LocalDate date) {
		return serv.viewreport(date);
	}
	
}
