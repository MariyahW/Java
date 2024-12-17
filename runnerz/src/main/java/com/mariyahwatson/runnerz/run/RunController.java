package com.mariyahwatson.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepo runRepo;

    public RunController(RunRepo runRepo) {
        this.runRepo=runRepo;
    }
    @GetMapping("")
    public List<Run> findAll(){
        return runRepo.findAll();
    }
    @GetMapping("/{id}")
    public Run findById(@PathVariable Integer id){
        Optional<Run> run = runRepo.findById(id);
        if(run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        return run.get();

    }
    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Run run){
        runRepo.create(run);
    }

    //put
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepo.update(run, id);
    }
    //delete
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepo.delete(id);
    }

}