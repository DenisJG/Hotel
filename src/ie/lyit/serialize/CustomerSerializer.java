package ie.lyit.serialize;

import java.util.ArrayList;
import ie.lyit.hotel.Customer;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CustomerSerializer  
{
	//create ArrayList variable
	private ArrayList<Customer> customers;
	//for knowing the highest value customer number
	private int highestValue;
	
	private final String FILENAME = "E:\\LYIT\\Year 3\\Software Implementation\\CA 1\\Files\\customer.bin";
									//"X:\\Year 3\\Software Implementation\\CA 1\\Files\\customers.bin";
	
	//default constructor
	public CustomerSerializer()
	{
		//construct customer list
		customers = new ArrayList<Customer>();
	}
	
	//read customer details from user and add to the ArrayList customers
	public void add()
	{
		//create customer object
		Customer cust = new Customer();
		//read its details
		if(cust.read() == true) //true if OK is selected
		{
			//And add it to the customers ArrayList
			customers.add(cust);
		}
		//if cancel is selected: a null customer is not added &
		//customer number must be decremented by 1 because cust incremented it
		else
		{
			Customer.setNextNumber(cust.getNumber());
		}
	}
	
	//displays the requested customer on screen and returns null if not found
	public Customer view()
	{
		Scanner keyboard = new Scanner(System.in);		

		// Read the number of the customer to be viewed from the user
		System.out.println("ENTER NUMBER OF CUSTOMER: ");
		int custToView = -1;
		try
		{
			custToView=keyboard.nextInt();
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage() + ".\nPlease type a number.\n");
			return null;
		}
		
		// for every customer object in customers
	    for(Customer tmpCust:customers)
	    {
		   // if it's number equals the number of the customerToView
		   if(tmpCust.getNumber() == custToView)
		   {
		      // display it
			  System.out.println(tmpCust);
			  return tmpCust;
		   }
		   
		}
	    // if we reach this code the customer was not found so return null
	    System.out.println("Invalid Customer Number.\n");
	    return null;		
	}
	
	//List all the customer records in the ArrayList customers
	public void list()
	{
		// for every Customer object in customers
		for(Customer tmpCust:customers)
		// display it
		System.out.println(tmpCust);
	}
	
	//edit the requested customer in the ArrayList customers	
	public void edit()
	{	
		// Call view() to find, display, & return the customer to edit
		Customer tempcustomer = view();
		// If the customer != null, i.e. it was found then...
		if(tempcustomer != null)
		{
			// get it's index
			int index=customers.indexOf(tempcustomer);
			// read in a new customer and...
			tempcustomer.read();
			// reset the object in customers
			customers.set(index, tempcustomer);
		}

	}
	
	//deletes requested customer from the ArrayList	
	public void delete()
	{	
		// Call view() to find, display, & return the customer to delete
		Customer tempcustomer = view();
		
		String warning = new String("Are you sure you want to delete Customer Number " +
									tempcustomer.getNumber() + "?" + "This cannot be undone.");
		int option = JOptionPane.showConfirmDialog(null, warning, null, JOptionPane.OK_CANCEL_OPTION);
		
		// If the customer != null, i.e. it was found then...
		if(tempcustomer != null && option == JOptionPane.OK_OPTION)
		// ...remove it from customers
		customers.remove(tempcustomer);
	}
	
	// This method will serialize the customers ArrayList when called, 
		// i.e. it will write it to a file called customers.ser
		public void writeRecordsToFile()
		{
			ObjectOutputStream os=null;
			try 
			{
				// Serialize the ArrayList...
				FileOutputStream fileStream = new FileOutputStream(FILENAME);
			
				os = new ObjectOutputStream(fileStream);
					
				os.writeObject(customers);
			}
			catch(FileNotFoundException fNFE)
			{
				System.out.println("Cannot create file to store customers.");
			}
			catch(IOException ioE)
			{
				System.out.println(ioE.getMessage());
			}
			finally 
			{
				try 
				{
					os.close();
				}
				catch(IOException ioE)
				{
					System.out.println(ioE.getMessage());
				}
			}
		}

		// This method will deserialize the customers ArrayList when called, 
		// i.e. it will restore the ArrayList from the file customers.ser
		public void readRecordsFromFile()
		{
			ObjectInputStream is=null;
			
			try 
			{
				// Deserialize the ArrayList...
				FileInputStream fileStream = new FileInputStream(FILENAME);
			
				is = new ObjectInputStream(fileStream);
					
				customers = (ArrayList<Customer>)is.readObject();
				//if the ArrayList is not empty then the next available customer number
				// will have to be changed (default is number 1)
				if(customers.isEmpty() == false)
				{
					//call setHighestValue() to change current highest value customer number
					setHighestValue();
					//call setNextNumber to change next available number to (highestValue + 1)
					Customer.setNextNumber(highestValue + 1);
				}
				
			}
			catch(FileNotFoundException fNFE)
			{
				System.out.println("Cannot create file to store customers.");
			}
			catch(IOException ioE)
			{
				System.out.println(ioE.getMessage());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			finally 
			{
				try 
				{
					is.close();
				}
				catch(IOException ioE)
				{
					System.out.println(ioE.getMessage());
				}
			}
		}
		
		//set the highestValue to the highest value customer number,
		//which should be located at the last index of the ArrayList
		public void setHighestValue()
		{
			highestValue = customers.get(customers.size() - 1).getNumber();
		}
		
		//if file doesn't exist
		public void createTheFile() 
		{
			//create file object
			File customerFile = new File(FILENAME);
			
			//if file already exists
			if(customerFile.isFile())
				System.out.println("File already exists!");
			//else create a new empty file
			else
			{
					try {
						customerFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
			}
			
		}
}
