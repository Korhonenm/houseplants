package palvelinohjelmointi.houseplants.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Houseplant {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Size(min=2, max=30)
	private String name;
	
	@Size(min=2, max=30)
	private String scientificName;
	
	@Size(min=4, max=4)
	private String year;
	
	@NotNull
	private int size;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "typeid")
	private Type type;
	
	public Houseplant() {}
	
	public Houseplant(String name, String scientificName, String year, int size, Type type) {
		super();
		this.name = name;
		this.scientificName = scientificName;
		this.year = year;
		this.size = size;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		
		//if (this.type !=null)
			//return "Houseplant [id=" + id + ", name=" + name + ", scientificName=" + scientificName + ", year=" + year
			//+ ", size=" + size + "type =" + this.getType() + "]";
		//else
			return "Houseplant [id=" + id + ", name=" + name + ", scientificName=" + scientificName + ", year=" + year
				+ ", size=" + size + "]";
	}
 
}
