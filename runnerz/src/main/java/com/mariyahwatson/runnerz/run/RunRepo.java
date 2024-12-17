package com.mariyahwatson.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepo {
    private final JdbcClient jdbcClient;


    public RunRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from Run").query(Run.class).list();
    }
    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select id,title,started_on,completed_on,miles,location from Run where id=:id").param("id", id).query(Run.class).optional();
    }
    public void create(Run run) {
        var updated=jdbcClient.sql("insert into Run(id, title, started_on, completed_on, miles, location) values (?,?,?,?,?,?)").params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString())).update();
        Assert.state(updated == 1, "Run not created."+ run.title());
    }
    public void update(Run run, Integer id) {
        var updated=jdbcClient.sql("update Run set id=?, title=?, started_on=?, completed_on=?, miles=?, location=? where id=:run.id").params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString())).update();
        Assert.state(updated == 1, "Run not updated."+ run.title());
    }
    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from run where id=:id").param("id", id).update();
        Assert.state(updated == 1, "Run not deleted.");
//    private List<Run> runs= new ArrayList<>();
//    List<Run> findAll(){
//        return runs;
//    }
//    Optional<Run> findById(Integer id){
//        return runs.stream().filter(run -> run.id().equals(id)).findFirst();
//    }
//
//    //post
//    void create(Run run){
//        runs.add(run);
//    }
//
//    //delete
//    void delete(Integer id){
//        runs.removeIf(run -> run.id().equals(id));
//    }
//
//    //put
//    void update(Run run, Integer id){
//        Optional<Run> optionalRun = findById(id);
//        if(optionalRun.isPresent()){
//            runs.set(runs.indexOf(optionalRun.get()), run);
//        }
    }

//    @PostConstruct
//    private void init(){
//        runs.add(new Run(1, "Monday Run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3, Location.INDOOR));
//        runs.add(new Run(2, "wednesday Run", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6, Location.OUTDOOR));
//    }

}