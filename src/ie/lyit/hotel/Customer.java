package ie.lyit.hotel;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.io.*;

//import java.io.Serializable;

public class Customer extends Person implements Serializable
{	// INHERITANCE - Customer IS-A Person
	// Customer has name, address, & phoneNumber from Person
	private String emailAddress;    // AND emailAddress
	private int number;			    // AND number

	private static int nextNumber=1;// static for unique number - starts off at 1
	
	// Default Constructor
	// Called when object is created like this ==> 
	//   Customer cObj = new Customer();	
	public Customer(){
		super();			// NOTE:Don't need to call super() explicitly.
		emailAddress=null;
		// Set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}
	
	// Initialization Constructor
	// Called when object is created like this ==>
	// Customer cObj = new Customer("Mr","Joe","Doe","12 Hi Rd,Letterkenny",
	//                              "0871234567","joe@hotmail.com");
	public Customer(String t, String fN, String sn, String address, 
			        String pNo, String email){
		// Call super class constructor - Passing parameters required by Person ONLY!
		super(t, fN, sn, address, pNo);
		// And then initialise Customers own instance variables
		emailAddress=email;
		// And finally set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}

	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
	public String toString(){
		return "[" + number + "," + super.toString() + "," + emailAddress + "]";
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(c1.equals(c2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Customer cObject;
		if (obj instanceof Customer)
		   cObject = (Customer)obj;
		else
		   return false;
		   
	    return(this.number==cObject.number);
	}

	// set() and get() methods
	public void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}
	public String getEmailAddress(){
		return this.emailAddress;
	}	
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}	
	
	//////////////////////////////////
	//		EXTRA METHODS FOR CA	//
	//////////////////////////////////
	
	//read() - reads customer from user 
	//returns true if OK is pressed, false if cancel
	public boolean read()
	{
		//set text field in txtCustNo to the customer number
		JTextField txtCustNo = new JTextField();
		txtCustNo.setText(String.valueOf(this.getNumber()));
		//User should not be able to edit Customer Number as it is unique
		txtCustNo.setEditable(false);
		
		//set all other text fields and combo box
		String[] titles = {"Mr", "Mrs", "Ms", "Miss"};
		//combo box in case incorrect titles are used
		JComboBox<String> comboTitle = new JComboBox<String>(titles);
		
		JTextField txtFirstName = new JTextField();
		txtFirstName.requestFocus(); //request input focus to start with FirstName
		JTextField txtSurname = new JTextField();
		JTextField txtAddress = new JTextField();
		JTextField txtPhoneNumber = new JTextField();
		JTextField txtEmail = new JTextField();
		
		//create message object to pass into the JOptionPane
		Object[] message = 
			{
				"Customer Number: ", txtCustNo,
				"Title: ", comboTitle,
				"First Name: ", txtFirstName,
				"Surname: ", txtSurname,
				"Address: ", txtAddress,
				"Phone Number: ", txtPhoneNumber,
				"Email: ", txtEmail,
			};
		
		//JOptionPane(parentObject, message, title, optionType)
		int option = JOptionPane.showConfirmDialog(null, message, "Enter Customer Details", JOptionPane.OK_CANCEL_OPTION);
		
		//If the user presses the OK option
		if(option == JOptionPane.OK_OPTION)
		{
			
			
			//Apply all the customer details they typed in to this customer object
			Name custName = new Name((String)comboTitle.getSelectedItem(), txtFirstName.getText(), txtSurname.getText());
			this.setName(custName);
			this.setAddress(txtAddress.getText());
			this.setPhoneNumber(txtPhoneNumber.getText());
			this.emailAddress = txtEmail.getText();
			
			return true;
		}
		//if user presses cancel button
		else
		{
			System.out.print("Operation cancelled\n");
			return false;
		}
	}
	
	//sets the next number to (highest value customer number + 1)
	// so when the program is closed it doesn't say 1 when deserialized
	public static void setNextNumber(int nextNumberIn)
	{
		nextNumber = nextNumberIn;
	}
	
}
