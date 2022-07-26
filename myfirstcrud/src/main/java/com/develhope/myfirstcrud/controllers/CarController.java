package com.develhope.myfirstcrud.controllers;

import com.develhope.myfirstcrud.entities.Car;
import com.develhope.myfirstcrud.repository.CarRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepo;


    @PostMapping
    public Car create(@RequestBody Car car){
        Car carSaved = carRepo.saveAndFlush(car);
        return carSaved;
    }


    @GetMapping
    public List<Car> getCars(){
        List<Car> carsSaved = carRepo.findAll();
        return carsSaved;
    }


    @GetMapping(value = "/singlecar")
    public Car getSingleCars(@RequestParam long id){
        Optional<Car> carById = carRepo.findById(id);
        if (carById.isPresent()){
            Car car = carById.get();
            return car;
        }
        return new Car();
    }


    @PutMapping("/{id}")
    public Car updateType(@PathVariable long id, @RequestParam String type){
        Optional<Car> findCar = carRepo.findById(id);
        if (findCar.isPresent()){
            Car updatedCar = findCar.get();
            updatedCar.setType(type);
            carRepo.saveAndFlush(updatedCar);
            return  updatedCar;
        }
        return new Car();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable long id){
        Optional<Car> findCar = carRepo.findById(id);
        if (findCar.isPresent()){
            carRepo.deleteById(id);
            return new ResponseEntity<>("cancellazione effettuata", HttpStatus.OK);
        }
        return new ResponseEntity<>("id non trovato", HttpStatus.CONFLICT);
    }


    @DeleteMapping
    public void deleteAll(){
        carRepo.deleteAll();
    }

}
