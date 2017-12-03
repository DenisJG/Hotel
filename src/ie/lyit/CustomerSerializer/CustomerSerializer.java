//Denis Gillespie
//L00126047
//CA 1

package ie.lyit.CustomerSerializer;

import java.util.ArrayList;
import ie.lyit.hotel.Customer;
public class CustomerSerializer
{
	// Constant FILENAME for the file to be created
	final String FILENAME = "customers.bin";
	
	// Declare ArrayList called customers - for storing a list of customers
	private ArrayList<Customer> customers;
	
	//default constructor
	public CustomerSerializer() 
	{
		//construct customer ArrayList
		customers = new ArrayList<Customer>();
	}
	
	//////////////////////////////////////////////////////
	// Method Name : add()								//
	// Return Type : void								//
	// Parameters : None								//
	// Purpose : Reads one Customer record from the user//
	//           and adds it to the ArrayList customers //
	//////////////////////////////////////////////////////	
	public void add()
	{
		// Create a Book object
		Customer customer = new Customer();
		// Read its details
		customer.read();	
		// And add it to the books ArrayList
		customers.add(customer);
	}
	
}
