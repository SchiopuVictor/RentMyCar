package rentmycar.rentmycar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentmycar.rentmycar.dtos.CarRequest;
import rentmycar.rentmycar.dtos.CarResponse;
import rentmycar.rentmycar.mapper.CarMapper;
import rentmycar.rentmycar.service.CarService;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest request){
        return ResponseEntity.ok(CarMapper.toDto(carService.createCar(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(
            @Valid @RequestBody CarRequest request,
            @PathVariable Long id){
        return ResponseEntity.ok(CarMapper.toDto(carService.updateCar(request,id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id){
        return ResponseEntity.ok(CarMapper.toDto(carService.getCar(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
