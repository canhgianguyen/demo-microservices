package com.example.orderservicemq.service.impl;

import com.example.orderservicemq.entity.Bread;
import com.example.orderservicemq.exception.DuplicateRecordException;
import com.example.orderservicemq.locale.Translator;
import com.example.orderservicemq.model.dto.BreadDto;
import com.example.orderservicemq.repository.BreadRepository;
import com.example.orderservicemq.service.BreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BreadServiceImpl implements BreadService {
    @Autowired
    private BreadRepository breadRepository;

    @Override
    public Bread createBread(BreadDto breadDto) {
        Optional<Bread> bread = breadRepository.findByType(breadDto.getType());
        if (bread.isPresent()) {
            throw new DuplicateRecordException(Translator.toLocale("error.msg.record.duplicate_detail"));
        }
        Bread createBread = Bread.builder()
                .type(breadDto.getType())
                .price(breadDto.getPrice())
                .build();
        return breadRepository.save(createBread);
    }

    @Override
    public List<Bread> getAllBread() {
        return breadRepository.findAll();
    }
}
