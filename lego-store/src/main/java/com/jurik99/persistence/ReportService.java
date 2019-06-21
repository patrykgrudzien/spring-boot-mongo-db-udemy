package com.jurik99.persistence;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import com.jurik99.model.AvgRatingModel;
import com.jurik99.model.LegoSet;

import java.util.List;

@Service
public class ReportService {

    private final MongoTemplate mongoTemplate;

    public ReportService(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<AvgRatingModel> getAvgRatingReport() {
        // building projection operation
        final ProjectionOperation projectToMatchModel =
                Aggregation.project()
                           .andExpression("$name").as("productName")
                           .andExpression("{$avg : '$reviews.rating'}").as("avgRating");
        // aggregation
//        final Aggregation avgRatingAggregation = Aggregation.newAggregation(LegoSet.class, projectToMatchModel);
        final TypedAggregation<LegoSet> avgRatingAggregation = Aggregation.newAggregation(LegoSet.class, projectToMatchModel);

        return mongoTemplate.aggregate(avgRatingAggregation, LegoSet.class, AvgRatingModel.class)
                            .getMappedResults();
    }
}
