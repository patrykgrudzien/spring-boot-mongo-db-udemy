package com.jurik99.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jurik99.model.LegoSet;
import com.jurik99.model.LegoSetDifficulty;

import java.util.Collection;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
    /**
     * Those methods with naming convention provided by Spring are allowed
     * to use {@link Sort} parameter because under the hood, the Proxy is generated
     * and it knows how to take {@link Sort} parameter into consideration.
     */
    Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);

    Collection<LegoSet> findAllByThemeIsNot(String theme);

    /*
     * Raw query from Mongo:
     * db.getCollection('legosets').find({"delivery.deliveryFee : {$lt : 50}"})
     */
    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);
    /**
     * Those custom methods with usage of {@link Query}
     * are NOT allowed to use {@link Sort} parameter.
     * We have to specify sorting on MongoDB level manually.
     */
    /*
     * Raw query from Mongo:
     * db.getCollection('legosets').find({"reviews.rating : {$eq : 10}"})
     */
    @Query("{'reviews.rating' : {$eq : 10}}")
    Collection<LegoSet> findAllByGreatReviews();

    @Query("{'delivery.inStock' : true}")
    Collection<LegoSet> findAllInStock();
}
