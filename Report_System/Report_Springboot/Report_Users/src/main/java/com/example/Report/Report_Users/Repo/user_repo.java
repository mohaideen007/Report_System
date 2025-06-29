package com.example.Report.Report_Users.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Report.Report_Users.Model.user_data;

public interface user_repo extends JpaRepository<user_data,Long>{

    user_data findByUsername(String username);

}
