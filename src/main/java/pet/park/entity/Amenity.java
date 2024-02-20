package pet.park.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Amenity {
	//again, add fields/ instance variables from entity diagram
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long amenityId;
		
		private String amenity;
		
		//like with petParks having a set of amenities amenity needs to have a set of petParks so JPA can manage the relationship using mappedBy (java field name)
		@EqualsAndHashCode.Exclude
		@ToString.Exclude
		@ManyToMany(mappedBy = "amenities")
		private Set<PetPark> petParks = new HashSet<>();


}
