package com.mariyahwatson.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepo {
    private List<Run> runs= new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }
    Optional<Run> findById(Integer id){
        return runs.stream().filter(run -> run.id().equals(id)).findFirst();
    }

    //post
    void create(Run run){
        runs.add(run);
    }

    //delete
    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }

    //put
    void update(Run run, Integer id){
        Optional<Run> optionalRun = findById(id);
        if(optionalRun.isPresent()){
            runs.set(runs.indexOf(optionalRun.get()), run);
        }
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1, "Monday Run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3, Location.INDOOR));
        runs.add(new Run(2, "wednesday Run", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6, Location.OUTDOOR));
    }

}