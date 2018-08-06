package br.edu.unicesumar.aulajava2;

public class Pessoa {

	public String id;
	public String lastName; 
	public String firstName;
	public String position;
	public Integer birthYear;
	public Integer debutYear;
	
	public Pessoa(String id, String lastName, String firstName, 
				  String position, Integer birthYear, Integer debutYear) {
		
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.position = position;
		this.birthYear = birthYear;
		this.debutYear = debutYear;		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName= firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getBirthYear() {
		return birthYear;
	}
	
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}
	
	public Integer getDebutYear() {
		return debutYear;
	}
	
	public void setDebutYear(Integer debutYear) {
		this.debutYear = debutYear;
	}

	@Override
	public String toString() {
		return "Pessoa [firstName = "+ firstName + "]";
		
	}
}
