package com.jurik99.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jurik99.model.LegoSet;
import com.jurik99.model.LegoSetDifficulty;

import java.util.Collection;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    Collection<LegoSet> findAllByThemeContains(String theme);

    Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);

    /*
     * Raw query from Mongo:
     * db.getCollection('legosets').find({"delivery.deliveryFee : {$lt : 50}"})
     */
    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")
    Collection<LegoSet> findAllByDeliveryPriceLessThan(int price);
}
