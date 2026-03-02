package rentmycar.rentmycar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentmycar.rentmycar.dtos.BookingRequest;
import rentmycar.rentmycar.dtos.BookingResponse;
import rentmycar.rentmycar.entity.Booking;
import rentmycar.rentmycar.mapper.BookingMapper;
import rentmycar.rentmycar.service.BookingService;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
private final BookingService bookingService;

@PostMapping
public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request)
{
   Booking booking = bookingService.createBooking(request);
   return ResponseEntity.ok(BookingMapper.toDto(booking));
}

@GetMapping("/{id}")
public ResponseEntity<BookingResponse> getBooking (@PathVariable Long id){
  Booking booking = bookingService.getBooking(id);
  return ResponseEntity.ok(BookingMapper.toDto(booking));
}

@PutMapping("/{id}")
public ResponseEntity<BookingResponse>  updateBooking(
        @PathVariable Long id,
        @RequestBody BookingRequest request)
{
    Booking booking = bookingService.updateBooking(request, id);
    return ResponseEntity.ok(BookingMapper.toDto(booking));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteBooking(@PathVariable Long id)
{
    bookingService.deleteBooking(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
