package pet.park.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class PetPark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petParkId;
	
	
	private String parkName;
	private String directions;
	private String stateOrProvince;
	
	// for geoLocation where we added @Embeddable in its class we add @Embedded here
	@Embedded
	private GeoLocation geoLocation;
	
	//on the petPark side there's a many to one relationship with contributer. We also need to specify the join column
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contributer_id", nullable = false) //name = table name (snake_case).
	private Contributer contributer;
	
	//use cascadeType persist b/c if i delete a park I want to delete the join table rows but I don't want to delete the amenity rows
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	// then we want to create the join table... names = table names (snake_case)
	@JoinTable(name = "pet_park_amenity", 
		joinColumns = @JoinColumn(name = "pet_park_id"),
		inverseJoinColumns = @JoinColumn(name = "amenity_id"))
	private Set<Amenity> amenities = new HashSet<>();
}
