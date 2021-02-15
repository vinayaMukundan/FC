package com.example.demo.passenger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.repo.PassengerRepository;

@ExtendWith(SpringExtension.class)
public class PassengerServiceTest {
	
	@Mock
	PassengerRepository passengerRepository;
	
	@InjectMocks
	PassengerService passengerService;

	public PassengerServiceTest() {
		passengerRepository = mock(PassengerRepository.class);
		passengerService = new PassengerServiceImp(passengerRepository);
	}
	
	@Test
    public void deletePassengerById()
    {
	    Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya@gmail.com");
		String message =passengerService.deletePassenger(passenger.getId());
		verify(passengerRepository,times(1)).deleteById(passenger.getId());
		assertEquals("Passenger Deleted Successfully...!!",
				"Passenger Deleted Successfully...!!", message);
    }
	
	 @Test
	 public void getPassengerByEmail()
	 {
	     Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya@gmail.com");
	     Mockito.when(passengerRepository.findByEmail("vinaya@gmail.com")).thenReturn(passenger);
	     assertThat(passengerService.findByEmail("vinaya@gmail.com")).isNotNull();
	 }
	
	 @Test
	 public void testFindallPassenger() {
		 List<Passenger> passengerList = new ArrayList<Passenger>();
		 passengerList.add(new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya@gmail.com"));
		 passengerList.add(new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya@gmail.com"));

		 Mockito.when(passengerRepository.findAll()).thenReturn(passengerList);
		 List<Passenger> passengerListExpected = passengerService.getAllPassengers();
		 assertEquals(passengerListExpected, passengerList);
		 assertEquals(2, passengerListExpected.size());
	 }
	
	 @Test
	 public void getFlightById()
	 {
	     final Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya@gmail.com");
		 Optional<Passenger> optional = Optional.of(passenger);
	     Mockito.when(passengerRepository.findById("PS-1")).thenReturn(optional);
	     assertThat(passengerService.getPassengerById("PS-1")).isNotNull();
	 }
	 @Test
	 public void testUpdateNewPassenger() {
	     Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya@gmail.com");
	     Mockito.when(passengerRepository.save(passenger)).thenReturn(passenger);
	     String message = passengerService.updatePassenger(passenger);
	     assertEquals("PS-1", passenger.getId());
	     assertEquals("Vinaya", passenger.getFirstName());
	     assertEquals("Mukundan", passenger.getLastName());
	     assertEquals("vinaya@gmail.com", passenger.getEmail());
	     assertEquals("Passenger Details updated Successfully ...!",message,message);
	  }
	 @Test
	 public void testSaveNewPassenger() {
	     Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya@gmail.com");
	     Mockito.when(passengerRepository.save(passenger)).thenReturn(passenger);
	     String message = passengerService.insertPassenger(passenger);
	     assertEquals("PS-1", passenger.getId());
	     assertEquals("Vinaya", passenger.getFirstName());
	     assertEquals("Mukundan", passenger.getLastName());
	     assertEquals("vinaya@gmail.com", passenger.getEmail());
	     assertEquals("Passenger details Added Successfully ...",message,message);
	  }
}
