package rentmycar.rentmycar.mapper;

import rentmycar.rentmycar.dtos.CarRequest;
import rentmycar.rentmycar.dtos.CarResponse;
import rentmycar.rentmycar.entity.Car;

public class CarMapper {
    public static CarResponse toDto(Car request){
        return CarResponse.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .year(request.getYear())
                .licensePlate(request.getLicensePlate())
                .pricePerDay(request.getPricePerDay())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .build();
    }

    public static Car toEntity(CarRequest request){
        return Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .year(request.getYear())
                .licensePlate(request.getLicensePlate())
                .pricePerDay(request.getPricePerDay())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .build();
    }

}
