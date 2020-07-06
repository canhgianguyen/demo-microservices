package com.example.orderservicemq.controller;

import com.example.orderservicemq.model.dto.BreadDto;
import com.example.orderservicemq.model.response.ResponseFactory;
import com.example.orderservicemq.service.BreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bread")
public class BreadController {
    @Autowired
    private BreadService breadService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping
    public ResponseEntity createBread(@RequestBody BreadDto breadDto) {
        return responseFactory.success(breadService.createBread(breadDto));
    }
}
