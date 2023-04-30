package com.avisys.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    //for fourth requirement
    private List<@NotBlank(message = "Mobile number is required") 
    			@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    			String> mobileNumbers;

    public CustomerRequest() {}

    public CustomerRequest(String firstName, String lastName, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
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

    //for fourth requirements
    public List<String> getMobileNumbers() {
        return mobileNumbers;
    }

    //for fourth requirements
    public void setMobileNumbers(List<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    
    }
}
