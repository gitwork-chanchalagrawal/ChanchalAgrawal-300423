package com.avisys.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	//for third requirement
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<MobileNumber> mobileNumbers; //for fourth requirement

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<MobileNumber> getMobileNumbers() {
		return mobileNumbers;
	}

	//for fourth requirement
	public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	
	//for fourth requirement
	public void addMobileNumber(MobileNumber mobileNumber) {
		if (this.mobileNumbers == null) {
			this.mobileNumbers = new ArrayList<>();
		}
		this.mobileNumbers.add(mobileNumber);
		mobileNumber.setCustomer(this);
	}
	}

}
