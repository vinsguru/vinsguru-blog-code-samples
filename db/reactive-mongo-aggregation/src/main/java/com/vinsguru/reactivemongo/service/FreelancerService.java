package com.vinsguru.reactivemongo.service;

import com.vinsguru.reactivemongo.entity.Freelancer;
import com.vinsguru.reactivemongo.projection.SkilledPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FreelancerService {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public Flux<SkilledPeople> getSkilledPeople(){

        // unwind
        UnwindOperation unwindOperation = Aggregation.unwind("skills");
        // group
        GroupOperation groupOperation = Aggregation.group("skills").push("name").as("names");
        // project
        ProjectionOperation projectionOperation = Aggregation.project("names").and("_id").as("skill");

        // add all the stages
        TypedAggregation<Freelancer> skillTypedAggregation = Aggregation.newAggregation(
                Freelancer.class,
                unwindOperation,
                groupOperation,
                projectionOperation
        );

        // execute
        return this.reactiveMongoTemplate
                .aggregate(
                        skillTypedAggregation,
                        SkilledPeople.class
                );


    }

}
