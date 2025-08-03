package com.example.problems.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.problems.Model.report_dao;

public interface report_repo extends JpaRepository<report_dao,Long> {

    List<report_dao> findByDate(LocalDate date);

}
