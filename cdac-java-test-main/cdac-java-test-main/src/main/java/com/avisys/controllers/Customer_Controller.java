package com.avisys.cim.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // RESTful controller
@RequestMapping("/api/customers") // specify the base URL
@CrossOrigin(origins = "*")
public class Customer_Controller {

	@Autowired // inject the CustomerRepository instance into this controller.
	private CustomerRepository customerRepository;

	// getAllCustomers() method retrieves all customers from the database by calling
	// the findAll() method of the customerRepository.
	// Then it maps the retrieved customers to CustomerResponse objects by calling
	// the mapCustomersToResponses() method and returns the list of CustomerResponse
	// objects as a JSON array.
	@GetMapping("/")
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return mapCustomersToResponses(customers);
	}

	// searchCustomers() method allows clients to search for customers by their
	// first name, last name, or mobile number.
	@GetMapping("/search")
	public List<CustomerResponse> searchCustomers(
	    @RequestParam(value = "firstName", required = false) String firstName,
	    @RequestParam(value = "lastName", required = false) String lastName,
	    @RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
	    List<Customer> customers = customerRepository.search(firstName, lastName, mobileNumber);
	    List<CustomerResponse> responses = new ArrayList<>();
	    for (Customer customer : customers) {
	        List<String> mobileNumbers = new ArrayList<>();
	        for (MobileNumber number : customer.getMobileNumbers()) {
	            mobileNumbers.add(number.getNumber());
	        }
	        responses.add(new CustomerResponse(
	            customer.getId(),
	            customer.getFirstName(),
	            customer.getLastName(),
	            mobileNumbers
	        ));
	    }
	    return responses;
	}


	// it is a private helper method that maps a list of Customer objects to a list
	// of CustomerResponse objects.
	// It iterates through the list of Customer objects, creates a new
	// CustomerResponse object for each Customer object, and adds it to a new list.
	// and returns the new list of CustomerResponse objects.
	private List<CustomerResponse> mapCustomersToResponses(List<Customer> customers) {
		List<CustomerResponse> responses = new ArrayList<>();
		for (Customer customer : customers) {
			//for third requirement
			List<String> mobileNumbers = customer.getMobileNumbers();
			responses.add(
					new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getMobileNumber()));
		}
		return responses;
	}
	// When a client sends a request to the REST API endpoint associated with this
	// method, Spring Boot will automatically convert the returned CustomerResponse
	// objects to JSON format before sending them back to the client.

	// 2.Ability to create a new customer over REST API.
	// Third party application should be able to create a customer using REST API.
	// Customer should be only created if the mobile number is not already present
	// in DB.
	// If there is any error while creating the Customer appropriate message should
	// be returned in response.
	// For example lets say if we already have a mobile number in system and request
	// is received for same mobile number then in response we should get 500 status
	// with message "Unable to create Customer. Mobile number already present.".

	@PostMapping("/CreateCustomer")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerRequest request) {
		Customer existingCustomer = customerRepository.findByMobileNumber(request.getMobileNumber());
		if (existingCustomer != null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unable to create Customer. Mobile number already present.");
		}
		Customer newCustomer = new Customer(request.getFirstName(), request.getLastName(), request.getMobileNumber());
		customerRepository.save(newCustomer);
		return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully.");
	}

}
