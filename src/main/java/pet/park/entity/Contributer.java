package pet.park.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;

public class Contributer {

	//add annotations to make JPA work
	//first one will tell where the identity column/ primary key column is
	@Id
	//generated value tells JPA how the primary key is managed. 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	//add fields from entity relationship diagram (our instance variables)
	private Long contributerId;
	private String contributerName;
	
	//we want a unique key on the email column so use @column and set unique to true so then when JPA creates the tables it will create an index on contributer email so theres no duplicates
	@Column(unique = true)
	private String contributerEmail;
	
	// then add a list(Set) of petParks JPA recognizes one to many relationship with petParks by adding a set of pet parks, but it also needs to know which Java instance variable is mapping that relationship. Also need to specify a cascade strategy for deletion
	// need @Equals... b/c or else when jackson converts to json it will be repetitive and take up a lot of memory and @ toString so it doesn't ever try to print the object recursion
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "contributer", cascade = CascadeType.ALL)
	private Set<PetPark> petParks = new HashSet<>();

}
