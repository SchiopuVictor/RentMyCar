package rentmycar.rentmycar.mapper;

import rentmycar.rentmycar.dtos.BookingResponse;
import rentmycar.rentmycar.entity.Booking;

public class BookingMapper {
    public static BookingResponse toDto(Booking request) {
        return BookingResponse.builder()
                .carName(request.getCar().getBrand()+" "+request.getCar().getModel())
                .customerName(request.getCustomer().getFirstName()+" "+request.getCustomer().getLastName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .pricePerDay(request.getPricePerDay())
                .totalDays(request.getTotalDays())
                .totalPrice(request.getTotalPrice())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .build();
    }



}
