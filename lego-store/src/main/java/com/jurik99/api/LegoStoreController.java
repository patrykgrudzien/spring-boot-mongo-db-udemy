package com.jurik99.api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jurik99.model.LegoSet;

import java.util.Collection;

@RestController
@RequestMapping("/legostore/api")
public class LegoStoreController {

    private final MongoTemplate mongoTemplate;

    public LegoStoreController(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/all")
    public Collection<LegoSet> all() {
        return mongoTemplate.findAll(LegoSet.class);
    }

    @PostMapping
    public void insert(@RequestBody final LegoSet legoSet) {
        mongoTemplate.insert(legoSet);
    }
}
