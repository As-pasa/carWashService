package com.asPasa.testTask.repositories;

import com.asPasa.testTask.models.WashType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WashTypeRepository extends CrudRepository<WashType,Long> {

    List<WashType> findByNameIgnoreCase(String name);
}
