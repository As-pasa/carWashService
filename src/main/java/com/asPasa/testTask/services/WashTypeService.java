package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.repositories.WashTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WashTypeService {
    private final WashTypeRepository washTypeRepository;

    public WashType findById(Long id) {
        return washTypeRepository.findById(id).orElseThrow(() -> new ApplicationException("Wash type not found for id" + id));
    }

    public WashType createWashType(WashType washType) {
        if(washTypeRepository.existsByName(washType.getName())){
            throw new ApplicationException("Wash Type already exists with such name: "+washType.getName());
        }
        return washTypeRepository.save(washType);

    }

    public WashType persistWashType(WashType washType) {
        return washTypeRepository.save(washType);
    }

    public void deleteById(Long id) {
        washTypeRepository.deleteById(id);
    }
    public WashType findByName(String name){
        return washTypeRepository.findByName(name).orElseThrow(()->new ApplicationException("Wash type with name not found. name: "+name));
    }
    public WashType updateWashType(WashType n_type, Long id){
        WashType washType = findById(id);
        washType.setName(n_type.getName());
        washType.setRequiredMinutes(n_type.getRequiredMinutes());
        return washTypeRepository.save(washType);
    }

    public List<WashType> findAll(){
        return (List<WashType>) washTypeRepository.findAll();
    }
}
