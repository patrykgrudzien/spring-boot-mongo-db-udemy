package com.jurik99.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jurik99.model.LegoSet;

import java.util.Collection;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    Collection<LegoSet> findAllByThemeContains(String theme);
}
