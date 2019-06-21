package com.jurik99.api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping
    public void update(@RequestBody final LegoSet legoSet) {
        mongoTemplate.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final String id) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), LegoSet.class);
    }
}
