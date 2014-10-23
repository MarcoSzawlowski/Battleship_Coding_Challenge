package players;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ships.*;

public class Human extends Players{
	public Human(Scanner in){
		super(input_grid(in));
	}

	// Get user inputs for all types of ships
	private static Map<String, Ships> input_grid(Scanner in) {
		Map<String, Ships> output_list = new HashMap();
		
		output_list.put("B", generate(in, "Battleship"));
		output_list.put("D", generate(in, "Destroyer"));
		output_list.put("S", generate(in, "Submarine"));
		output_list.put("C", generate(in, "Cruiser"));
		return output_list;
	}
	
	// Generate the ships in the area the player wants
	public static Ships generate(Scanner in, String type){
		System.out.printf("Set your %s:\n", type);
		String command = in.nextLine();
		int x = 0;
		int y = 0;

		
		// turn A-J into an ascii representation and subtract 65 (A's ascii) to get the x co-ordinate
		// Error check to make sure nothing goes wrong with parseInt (need correct format of A-J and 1-10
		x = (int)command.substring(0,1).toCharArray()[0] - 65;
		try{
			y = Integer.parseInt(command.substring(1,command.length())) - 1;
		}catch (NumberFormatException e){
			System.out.println("INPUT ERROR: use a number for the second coordinate");
			return generate(in, "Battleship");	
		}
		
		int z;
		
		// Check to see if any of coordinates are out of bounds
		if (x < 0 || x > 9 || y < 0 || y > 9){
			System.out.println("INPUT ERROR: your input was not A-J 1-10 format");
			return generate(in,"Battleship");
		}
		
		// Get the direction and pass it in
		System.out.printf("Enter the direction it goes (right/down):\n");
		String direction = in.nextLine();
		System.out.println(x + " " + y);
		
		if (direction.equals("right")){
			z = 0;
			if ((x + 5) > 9 || x < 0){
				System.out.println("You can't plant it it there, try again");
				return generate(in, "Battleship");
			}
		}
		else{
			z = 1;
			if ((y + 5) > 9 || y < 0){
				System.out.println("You can't plant it it there, try again");
				return generate(in, "Battleship");
			}			
		}
		
		// Decide which ship we are doing based on the passed in type
		if (type == "Battleship")
			return new Battleship(x,y,z);
		else if (type == "Destroyer")
			return new Destroyer(x,y,z);
		else if (type == "Submarine")
			return new Submarine(x,y,z);
		else
			return new Cruiser(x,y,z);
	}
	
	// Manage input for attacking
	public void manage_input(Scanner in, Players p2) {
		String location = in.nextLine();
		int x;
		int y;
		
		// turn A-J into an ascii representation and subtract 65 (A's ascii) to get the x co-ordinate
		// Error check to make sure nothing goes wrong with parseInt (need correct format of A-J and 1-10
		x = (int)location.substring(0,1).toCharArray()[0] - 65;
		try{		
			y = Integer.parseInt(location.substring(1,location.length())) - 1;
		}catch (NumberFormatException e){
			System.out.println("INPUT ERROR: you did not input a number in your second coordinate");
			manage_input(in, p2);
			return;
		}
		
		// Make sure nothing is out of bounds
		if (y > 9 || y < 0 || x < 0 || x > 9){
			System.out.println("INPUT ERROR: please input A-J 1-10");
			manage_input(in, p2);
		}
		else{
			p2.attack(x, y, in, this);
		}
	}
}
