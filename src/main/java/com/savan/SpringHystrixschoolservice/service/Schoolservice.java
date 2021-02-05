package com.savan.SpringHystrixschoolservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class Schoolservice {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getStudentData_Fallback")
    public String getStudentData(String schoolname){
        System.out.println("Getting School details for " + schoolname);

        String response = restTemplate
                .exchange("http://localhost:8089/getStudent/{schoolname}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, schoolname).getBody();

        System.out.println("Response Received as " + response + " -  " + new Date());

        return "NORMAL FLOW !!! - School Name -  " + schoolname + " :::  " +
                " Student Details " + response + " -  " + new Date();
    }

    private String getStudentData_Fallback(String schoolname){
        System.out.println("Student Service is down!!! fallback route enabled...");

        return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
                " Service will be back shortly - " + new Date();
    }

}
