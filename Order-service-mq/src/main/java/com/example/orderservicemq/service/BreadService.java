package com.example.orderservicemq.service;

import com.example.orderservicemq.entity.Bread;
import com.example.orderservicemq.model.dto.BreadDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BreadService {
    Bread createBread(BreadDto breadDto);
    List<Bread> getAllBread();
}
