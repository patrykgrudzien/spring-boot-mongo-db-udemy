package com.jurik99.api;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
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
import com.jurik99.model.QLegoSet;
import com.jurik99.persistence.LegoSetRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

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

    @GetMapping("/all-sorted")
    public Collection<LegoSet> allSorted() {
        final Sort sortByThemeAsc = Sort.by("theme").ascending();
        return legoSetRepository.findAll(sortByThemeAsc);
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
        final Sort sortByThemeAsc = Sort.by("theme").ascending();
        return legoSetRepository.findAllByThemeContains(theme, sortByThemeAsc);
    }

    @GetMapping("/hardThatStartWithM")
    public Collection<LegoSet> hardThatStartWithM() {
        return legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, "M");
    }

    @GetMapping("/byDeliveryFeeLessThan/{price}")
    public Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable final int price) {
        return legoSetRepository.findAllByDeliveryPriceLessThan(price);
    }

    @GetMapping("/greatReviews")
    public Collection<LegoSet> byGreatReviews() {
        return legoSetRepository.findAllByGreatReviews();
    }

    @SuppressWarnings("DanglingJavadoc")
    @GetMapping("/best-buys")
    public Collection<LegoSet> bestBuys() {
        // Query DSL cannot be accessed directly from LegoSetRepository
        final QLegoSet query = new QLegoSet("query"); // parameter "name" doesn't matter here and I can put anything
        final BooleanExpression inStockFilter = query.deliveryInfo.inStock.isTrue();
        final BooleanExpression smallDeliveryFeeFilter = query.deliveryInfo.deliveryFee.lt(50);
        final BooleanExpression hasGreatReviewsFilter = query.reviews.any().rating.eq(10);

        final BooleanExpression bestBuysFilter = inStockFilter.and(smallDeliveryFeeFilter)
                                                              .and(hasGreatReviewsFilter);
        /**
         * Thanks to {@link QuerydslPredicateExecutor} that {@link LegoSetRepository} extends,
         * I can now use another overloaded method: {@link LegoSetRepository#findAll(Predicate)}
         */
        return (Collection<LegoSet>) legoSetRepository.findAll(bestBuysFilter);
    }

    @GetMapping("fullTextSearch/{text}")
    public Collection<LegoSet> fullTextSearch(@PathVariable final String text) {
        // we want to trigger full text search on 3 fields (name, theme, userName)
        // I need to wrap a text into Text Criteria Object
        final TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(text);
        return legoSetRepository.findAllBy(textCriteria);
    }
}
