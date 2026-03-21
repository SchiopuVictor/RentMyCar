package rentmycar.rentmycar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentmycar.rentmycar.dtos.CarRequest;
import rentmycar.rentmycar.entity.Car;
import rentmycar.rentmycar.exception.CarNotFoundException;
import rentmycar.rentmycar.mapper.CarMapper;
import rentmycar.rentmycar.repository.CarRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    @Transactional
    public Car createCar(CarRequest request) {
        Car car = CarMapper.toEntity(request);
        car.setCreatedAt(LocalDateTime.now());
        return carRepository.save(car);

    }

    @Transactional
    public Car updateCar(CarRequest request, Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                CarNotFoundException::new);
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setLicensePlate(request.getLicensePlate());
        car.setStatus(request.getStatus());
        car.setPricePerDay(request.getPricePerDay());
        return carRepository.save(car);
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).orElseThrow(
                CarNotFoundException::new);
    }

    @Transactional
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                CarNotFoundException::new);
        carRepository.delete(car);
    }

}
