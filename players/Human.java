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
		
		output_list.put("B", generate(in, "Battleship", 5, output_list));
		output_list.put("D", generate(in, "Destroyer", 4, output_list));
		output_list.put("S", generate(in, "Submarine", 3, output_list));
		output_list.put("C", generate(in, "Cruiser", 2, output_list));
		return output_list;
	}
	
	// Generate the ships in the area the player wants
	public static Ships generate(Scanner in, String type, int length, Map<String, Ships> list){
		System.out.printf("Set your %s:\n", type);
		String command = in.nextLine();
		int x = 0;
		int y = 0;
        int z = 0;
        Ships outship = null;

		
		// turn A-J into an ascii representation and subtract 65 (A's ascii) to get the x co-ordinate
		// Error check to make sure nothing goes wrong with parseInt (need correct format of A-J and 1-10
		x = (int)command.substring(0,1).toCharArray()[0] - 65;
		try{
			y = Integer.parseInt(command.substring(1,command.length())) - 1;
		}catch (NumberFormatException e){
			System.out.println("INPUT ERROR: use a number for the second coordinate");
			return generate(in, type, length, list);
		}

		
		// Check to see if any of coordinates are out of bounds
		if (x < 0 || x > 9 || y < 0 || y > 9){
			System.out.println("INPUT ERROR: your input was not A-J 1-10 format");
			return generate(in,type, length, list);
		}
		
		// Get the direction and pass it in
		System.out.printf("Enter the direction it goes (right/down):\n");
		String direction = in.nextLine();
		System.out.println(x + " " + y);
		
		if (direction.equals("right")){
			z = 0;
			if ((x + length) > 9 || x < 0){
				System.out.println("You can't plant it it there, your ship is going off the x axis, try again");
				return generate(in, type, length, list);
			}
		}
		else if (direction.equals("down")){
			z = 1;
			if ((y + length) > 9 || y < 0){
				System.out.println("You can't plant it it there, your ship is going off the y axis, try again");
				return generate(in, type, length, list);
			}			
		}
        else{
            System.out.println("INPUT ERROR: Please enter either right or down");
            return generate(in, type, length, list);
        }
		
		// Decide which ship we are doing based on the passed in type
		if (type == "Battleship")
			outship = new Battleship(x,y,z);
		else if (type == "Destroyer")
			outship = new Destroyer(x,y,z);
		else if (type == "Submarine")
			outship =  new Submarine(x,y,z);
		else
			outship = new Cruiser(x,y,z);

        // check to see if our ship overlaps other ships we placed
        for (Ships s : list.values()){
            if(s.check_overlap(x, y, length, z)) {
                System.out.println("INPUT ERROR: Your new " + type + " overlaps your " + s.get_type());
                return generate(in, type, length, list);
            }
        }

        System.out.println("Created: " + type);
        return outship;
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
