package com.avisys.entities;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerResponse {

	//@JsonProperty annotation to specify the JSON property name for a field
    @JsonProperty("firstName")
    //@NotBlank specify that a field must not be blank
    @NotBlank(message = "First name is required")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last name is required")
    private String lastName;

  
    @JsonProperty("mobileNumbers")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MobileNumberResponse> mobileNumbers = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private Long id;

    public CustomerResponse() {}

    public CustomerResponse(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
       
    }

    public CustomerResponse(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
       
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

    public List<MobileNumberResponse> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<MobileNumberResponse> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
    }
}
