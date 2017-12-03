package ie.lyit.testers;

import ie.lyit.serialize.CustomerSerializer;
import ie.lyit.serialize.Menu;

public class CustomerSerializerTester 
{
	public static void main(String[] args) 
	{
	
		// Create an object of customerSerializer
		CustomerSerializer customerSerializer = new CustomerSerializer();
		
		//try to read the  records from the customers file
		try
		{
			customerSerializer.readRecordsFromFile();
		}
		//catch any exceptions
		//e.g. File has not been created yet
		catch(Exception e)
		{
			System.out.print(e.getMessage());
			//create the customers file
			System.out.println(", creating file");
			customerSerializer.createTheFile();
		}
		
	    // Create a Menu Object
		Menu menuObj = new Menu();
		
		do
		{
			// Call it's display() method
			menuObj.display();
			// Call it's readOption() method
			menuObj.readOption();
			// switch on the option and call the appropriate method
			switch(menuObj.getOption())
			{
			   case 1:customerSerializer.add();break;
			   case 2:customerSerializer.list();break;
			   case 3:customerSerializer.view();break;
			   case 4:customerSerializer.edit();break;
			   case 5:customerSerializer.delete();break;
			   case 6:break;				
			   default:System.out.println("INVALID OPTION...");
			}
		}while(menuObj.getOption() != 6);	
		
		
		// Serialize the ArrayList to the File
		// i.e. write the customers ArrayList back into the the file		
		customerSerializer.writeRecordsToFile();

	}
}
