package com.jurik99.api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jurik99.model.LegoSet;

@RestController
@RequestMapping("/legostore/api")
public class LegoStoreController {

    private final MongoTemplate mongoTemplate;

    public LegoStoreController(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping
    public void insert(@RequestBody final LegoSet legoSet) {
        mongoTemplate.insert(legoSet);
    }
}
