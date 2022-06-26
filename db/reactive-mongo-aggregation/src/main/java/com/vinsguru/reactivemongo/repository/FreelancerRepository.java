package com.vinsguru.reactivemongo.repository;

import com.vinsguru.reactivemongo.entity.Freelancer;
import com.vinsguru.reactivemongo.projection.SkilledPeople;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FreelancerRepository extends ReactiveMongoRepository<Freelancer, String> {

    @Aggregation({
            "{ $unwind : { path : $skills } }",
            "{ $group : { _id : $skills, names : { $push : $name } } }",
            "{ $project : { _id : 0, skill : $_id, names : 1 } }"
    })
    Flux<SkilledPeople> getSkilledPeople();

}
