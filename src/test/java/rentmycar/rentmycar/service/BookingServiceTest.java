package rentmycar.rentmycar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rentmycar.rentmycar.dtos.BookingRequest;
import rentmycar.rentmycar.entity.Booking;
import rentmycar.rentmycar.entity.Car;
import rentmycar.rentmycar.entity.Customer;
import rentmycar.rentmycar.repository.BookingRepository;
import rentmycar.rentmycar.repository.CarRepository;
import rentmycar.rentmycar.repository.CustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TestingBookingServices")
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private BookingService bookingService;

    private Car carTest;
    private Booking bookingTest;
    private Customer customerTest;
    private BookingRequest bookingRequest;

    @BeforeEach()
    void setUp() {
        this.carTest = Car.builder()
                .id(1L)
                .brand("Bmw")
                .model("m3")
                .year(2002L)
                .licensePlate("12412f")
                .pricePerDay(BigDecimal.valueOf(12))
                .status("noua")
                .createdAt(LocalDateTime.now())
                .build();

        this.customerTest = Customer.builder()
                .id(1L)
                .firstName("Jora")
                .lastName("Boss")
                .email("12fafa31")
                .phone("12418714")
                .driverLicenseNumber("12423wf")
                .createdAt(LocalDateTime.now())
                .build();


        this.bookingTest = Booking.builder()
                .id(1L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(10))
                .totalDays(10)
                .totalPrice(BigDecimal.valueOf(2412))
                .pricePerDay(BigDecimal.valueOf(10))
                .status("good")
                .createdAt(LocalDateTime.now())
                .car(this.carTest)
                .customer(this.customerTest)
                .build();

        this.bookingRequest = BookingRequest.builder()
                .carId(1L)
                .customerId(1L)
                .status("New status")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(5))
                .createdAt(LocalDateTime.now())
                .build();

    }

    @Nested
    @DisplayName("Create Booking Tests")
    class CreateBookingTests {
        @Test
        @DisplayName("Should Create Booking Successfully")
        void ShouldCreateBookingSuccessfully() {
            //Given
            when(carRepository.findById(bookingRequest.getCarId()))
                    .thenReturn(Optional.of(carTest));


            when(customerRepository.findById(bookingRequest.getCustomerId()))
                    .thenReturn(Optional.of(customerTest));

            when(bookingRepository.save(any(Booking.class)))
                    .thenReturn(bookingTest);

            //When
            final Booking result = bookingService.createBooking(bookingRequest);

            //Then

            assertNotNull(result);
            assertEquals(1L, result.getId());

            verify(carRepository, times(1)).findById(bookingRequest.getCarId());
            verify(customerRepository, times(1)).findById(bookingRequest.getCustomerId());
            verify(bookingRepository, times(1)).save(any(Booking.class));

            verify(bookingRepository).save(argThat(booking ->
                    booking.getCar() != null && booking.getCar().getId().equals(1L) &&
                            booking.getCustomer() != null && booking.getCustomer().getId().equals(1L)
            ));
        }
    }


}