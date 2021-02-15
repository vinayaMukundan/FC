package com.example.demo.airport;

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
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.repo.AirportRepository;

@ExtendWith(SpringExtension.class)
public class AirportServiceTest {
	
	@Mock
	AirportRepository airportRepo;
	
	@InjectMocks
	AirportService airportService;

	public AirportServiceTest()
	{
		airportRepo=mock(AirportRepository.class);
		airportService=new AirportServiceImp(airportRepo);
	}
	
	@Test
	 public void testSaveNewAirport() {
	     Airport airport = new Airport("TVM","TVM-AIRPORT","INDIA");
	     Mockito.when(airportRepo.save(airport)).thenReturn(airport);
	     String message = airportService.insertAirport(airport);
	     assertEquals("Airport details Added Successfully ...",
	    		 "Airport details Added Successfully ...", message);
	  }
	
	@Test
    public void deleteAirportById()
    {
		Airport airport = new Airport("TVM","TVMAIRPORT","INDIA");
		String message = airportService.deleteAirport(airport.getIataCode());
		verify(airportRepo,times(1)).deleteById(airport.getIataCode());
		assertEquals("Airport Deleted Successfully...!!", 
				"Airport Deleted Successfully...!!", message);
    }
	
	@Test
	 public void testUpdatedAirport() {
	     Airport airport = new Airport("TVM","TVMAIRPORT","INDIA");
	     Mockito.when(airportRepo.save(airport)).thenReturn(airport);
	     String message = airportService.updateAirport(airport);
	     assertEquals("Airport Updated Successfully...!!", "Airport Updated Successfully...!!", message);
	  }
	
	@Test
    public void getAirportById()throws AirportNotFoundException
    {
	   final Airport emp = new Airport("TVM","TVMAIRPORT","INDIA");
	   Optional<Airport> optional = Optional.of(emp);
	   Mockito.when(airportRepo.findById("TVM")).thenReturn(optional);
	   assertThat(airportService.getAirportById("TVM")).isNotNull();
   }
	
	 @Test
	 public void findallAirport() {
		List<Airport> airportList = new ArrayList<Airport>();
		airportList.add(new Airport("KOC","TVMAIRPORT","INDIA"));		
		airportList.add(new Airport("KOC","TVMAIRPORT","INDIA"));
		airportList.add(new Airport("TCR","TVMAIRPORT","INDIA"));
	    Mockito.when(airportRepo.findAll()).thenReturn(airportList);
	    List<Airport> list = airportService.getAllAirports();
	    assertEquals(3, list.size());
	}
}
