package com.example.problems.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.problems.Model.report_dao;
import com.example.problems.Repo.report_repo;

@Service
public class report_service {

    @Autowired
    private report_repo repo;

    public report_dao addreport(String username,MultipartFile file, String description) throws IOException {
        String uploads="reports/";
        String fileName = file.getOriginalFilename();
        Path path=Paths.get(uploads+fileName);

        Files.createDirectories(path.getParent());
        Files.write(path,file.getBytes());

       Path imagePath = Paths.get("uploads",username+".jpg");
        
        report_dao report= new report_dao();
        report.setUsername(username);
        report.setPhoto(imagePath.toString());
        report.setDescription(description);
        report.setDate(LocalDate.now());
        report.setEvidence(path.toString());
        repo.save(report);
        return report;
    }

    public List<report_dao> viewreport(LocalDate date) {
       return repo.findByDate(date);
    }

}
