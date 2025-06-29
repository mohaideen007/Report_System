package com.example.problems.Model;

import java.time.LocalDate;
import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name="reports")
public class report_dao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long problem_id;

    private String username;
    private String photo;
    private String Description;
    private LocalDate date;
    private String evidence;


}
