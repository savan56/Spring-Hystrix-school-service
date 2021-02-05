package com.savan.SpringHystrixschoolservice.controller;

import com.savan.SpringHystrixschoolservice.service.Schoolservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolServiceController {

    @Autowired
    private Schoolservice schoolservice;

    @GetMapping("/getSchoolDetails/{schoolname}")
    public String getStudents(@PathVariable String schoolname) {
        System.out.println("Going to call student service to get data!");
        return schoolservice.getStudentData(schoolname);
    }
}
