package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.repositories.WashTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WashTypeService {

    private final WashTypeRepository washTypeRepository;
    public WashType findById(Long id){
        return washTypeRepository.findById(id).orElseThrow(()->new ApplicationException("Type not found for id:"+id));
    }

    public WashType createWashType(String name, Long duration) {
        WashType washType= new WashType(name,duration);
        return washTypeRepository.save(washType);
    }
}
