//////////////////////////////////////////////////////
//class Menu                     //
//� This class contains the Menu for the system - //
//////////////////////////////////////////////////////
package ie.lyit.serialize;

import java.util.Scanner;

public class Menu 
{
	private int option;
	
	public void display()
	{
		System.out.println("\t1. Add");
		System.out.println("\t2. List");
		System.out.println("\t3. View");
		System.out.println("\t4. Edit");
		System.out.println("\t5. Delete");
		System.out.println("\t6. Quit");		
	}
	
	public void readOption()
	{
		Scanner kbInt = new Scanner(System.in);
		System.out.println("\n\tEnter Option [1|2|3|4|5|6]");
		try
		{
			option=kbInt.nextInt();
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage() + ".\nPlease type a number.\n");
		}
	}
	
	public int getOption()
	{
		return option;
	}	
}