package rentmycar.rentmycar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentmycar.rentmycar.dtos.BookingRequest;
import rentmycar.rentmycar.entity.Booking;
import rentmycar.rentmycar.entity.Car;
import rentmycar.rentmycar.entity.Customer;
import rentmycar.rentmycar.exception.BookingNotFoundException;
import rentmycar.rentmycar.exception.CarNotFoundException;
import rentmycar.rentmycar.exception.CustomerNotFoundException;
import rentmycar.rentmycar.repository.BookingRepository;
import rentmycar.rentmycar.repository.CarRepository;
import rentmycar.rentmycar.repository.CustomerRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Booking createBooking(BookingRequest request) {
       Car car = carRepository.findById(request.getCarId()).orElseThrow(
               CarNotFoundException::new);
       Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(
               CustomerNotFoundException::new);

        Booking booking = new Booking();

        booking.setCar(car);
        booking.setCustomer(customer);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setStatus(request.getStatus());
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public Booking getBooking(Long id){
        return bookingRepository.findById(id).orElseThrow(
                BookingNotFoundException::new);
    }

    @Transactional
    public Booking updateBooking(BookingRequest request, Long id){
        Car car = carRepository.findById(request.getCarId()).orElseThrow(
                CarNotFoundException::new);
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(
                CustomerNotFoundException::new);

        Booking booking = bookingRepository.findById(id).orElseThrow(
                BookingNotFoundException::new);
        booking.recalculate();
        booking.setCar(car);
        booking.setCustomer(customer);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setStatus(request.getStatus());
        return bookingRepository.save(booking);

    }

    @Transactional
    public void deleteBooking(Long id){
        Booking booking = bookingRepository.findById(id).orElseThrow(
                BookingNotFoundException::new);
        bookingRepository.delete(booking);
    }
}
