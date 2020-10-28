package com.vinsguru.reactivemongo.repository;

import com.vinsguru.reactivemongo.entity.Freelancer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface FreelancerRepository extends ReactiveMongoRepository<Freelancer, String> {

    @Query("{ 'skills': { $all: ?0 } }")
    Flux<Freelancer> findBySkillsAll(List<String> skills);

    Flux<Freelancer> findBySkillsIn(List<String> skills);

}
