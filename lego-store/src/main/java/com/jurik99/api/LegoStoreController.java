package com.jurik99.api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jurik99.model.LegoSet;
import com.jurik99.model.LegoSetDifficulty;
import com.jurik99.persistence.LegoSetRepository;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/legostore/api")
public class LegoStoreController {

    @SuppressWarnings("FieldCanBeLocal")
    private final MongoTemplate mongoTemplate;

    private final LegoSetRepository legoSetRepository;

    public LegoStoreController(final MongoTemplate mongoTemplate,
                               final LegoSetRepository legoSetRepository) {
        this.mongoTemplate = mongoTemplate;
        this.legoSetRepository = legoSetRepository;
    }

    @GetMapping("/all")
    public Collection<LegoSet> all() {
//        return mongoTemplate.findAll(LegoSet.class);
        return legoSetRepository.findAll();
    }

    @PostMapping
    public void insert(@RequestBody final LegoSet legoSet) {
//        mongoTemplate.insert(legoSet);
        legoSetRepository.insert(legoSet);
    }

    @PutMapping
    public void update(@RequestBody final LegoSet legoSet) {
//        mongoTemplate.save(legoSet);
        legoSetRepository.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final String id) {
//        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), LegoSet.class);
        legoSetRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<LegoSet> findById(@PathVariable("id") final String id) {
        return legoSetRepository.findById(id);
    }

    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable final String theme) {
        return legoSetRepository.findAllByThemeContains(theme);
    }

    @GetMapping("/hardThatStartWithM")
    public Collection<LegoSet> hardThatStartWithM() {
        return legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, "M");
    }

    @GetMapping("/byDeliveryFeeLessThan/{price}")
    Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable final int price) {
        return legoSetRepository.findAllByDeliveryPriceLessThan(price);
    }
}
