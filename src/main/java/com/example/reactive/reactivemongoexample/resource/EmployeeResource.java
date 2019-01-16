package com.example.reactive.reactivemongoexample.resource;


import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactive.reactivemongoexample.model.Employee;
import com.example.reactive.reactivemongoexample.model.EmployeeEvent;
import com.example.reactive.reactivemongoexample.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

    private EmployeeRepository employeeRepository;

    public EmployeeResource(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/all")
    public Flux<Employee> getAll(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable String id){
        return employeeRepository.findById(id);
    }

    @GetMapping(value = "/{empId}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable String empId){
        return employeeRepository.findById(empId)
                .flatMapMany(employee -> {
                    Flux<Long> interval  = Flux.interval(Duration.ofSeconds(2));
                    Flux<EmployeeEvent> employeeEventFlux =
                            Flux.fromStream(
                                    Stream.generate(() -> new EmployeeEvent(employee,new Date()))
                            );
                    return Flux.zip(interval,employeeEventFlux).map(Tuple2::getT2);
                });
    }
}
