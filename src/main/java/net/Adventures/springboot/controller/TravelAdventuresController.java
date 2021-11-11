package net.Adventures.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.Adventures.springboot.entities.Adventure;
import net.Adventures.springboot.repository.AdventureRepository;

@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {
 private final AdventureRepository adventureRepository;

public TravelAdventuresController(AdventureRepository adventureRepository) {
    this.adventureRepository = adventureRepository;
  }
  // Add controller methods below:
  @GetMapping("")
  public Iterable<Adventure> getAllAdventures() {
    return this.adventureRepository.findAll();
  }
  
  
  @GetMapping("/{id}")
  public Optional<Adventure> getAdventureId(@PathVariable("id") Integer id) {
    return this.adventureRepository.findById(id);
  }
	

@PostMapping("/add")
  public Adventure createNewAdventure(@RequestBody Adventure adventure) {
    Adventure newAdventure = this.adventureRepository.save(adventure);
    return newAdventure;
  }

 @PutMapping("/{id}")
 public Adventure updateAdventure(@PathVariable("id") Integer id, @RequestBody Adventure a) {
   Optional<Adventure> adventureToUpdateOptional = this.adventureRepository.findById(id);
   if (!adventureToUpdateOptional.isPresent()) {
	  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
   }
   Adventure adventureToUpdate = adventureToUpdateOptional.get();
   
   if (a.getDate() != null) {
	adventureToUpdate.setDate(a.getDate());
   }
   if (a.getCountry() != null) {
	   adventureToUpdate.setCountry(a.getCountry());
   }
   if (a.getCity() != null) {
	   adventureToUpdate.setCity(a.getCity());
   }
   
   if (a.getState() != null) {
	   adventureToUpdate.setState(a.getState());
   }
   
   if (a.getNumPhotos() != null) {
	   adventureToUpdate.setNumPhotos(a.getNumPhotos());
   }
   
   if (a.getBlogCompleted() != null) {
	   adventureToUpdate.setBlogCompleted(a.getBlogCompleted());
   }
   
   Adventure updatedAdventure = this.adventureRepository.save(adventureToUpdate);
   return updatedAdventure;
 }
 

@DeleteMapping("/{id}")
public Adventure deleteAdventure(@PathVariable("id") Integer id) {
Optional<Adventure> adventureToDeleteOptional = this.adventureRepository.findById(id);
  if (!adventureToDeleteOptional.isPresent()) {
    // return null;
 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
    }
  Adventure adventureToDelete = adventureToDeleteOptional.get();
this.adventureRepository.delete(adventureToDelete);
return adventureToDelete;
}

@GetMapping("/bystate")
public List<Adventure> searchAdventure(
  @RequestParam(name = "state", required = false) String state
) {
  if (state != null) {
return this.adventureRepository.findByState(state);
  } 
  else {
    return new ArrayList<>();
  }
}

@GetMapping(path = "/bycountry/{country}")
public List<Adventure> countryAdventure( 
  @PathVariable(name = "country") String country)
  {
  if (country != null) {
return this.adventureRepository.findByCountry(country);
  } 
  else {
     return new ArrayList<>();
  
  }
}
/*
@GetMapping(path = "/bycity/{city}")
public List<Adventure> cityAdventure( 
  @PathVariable(name = "city") String city)
  {
  if (city != null) {
return this.adventureRepository.findByCity(city);
  } 
  else {
     return new ArrayList<>();
  
  }
}
*/


}
