package battleship;

import java.util.Scanner;

import players.*;

public class BattleshipGame {
	public static void main(String[] args){
		// INPUT: start the input stream
		Scanner in = new Scanner(System.in);
		
		// INITIATE: players
		Human p1 = new Human(in);
		AI p2 = new AI();
		
		// INITIATE: start screen
		System.out.println("\t\t\tWelcome to Battleship!");
		p1.print_grid_alternate(p2.get_grid());
		//p2.print_grid_alternate(p1.get_grid());
		
		// INPUT: input loop
		while(true){
			// Player's move
			System.out.println("\nYour move");
			p1.manage_input(in, p2);
			p1.print_grid_alternate(p2.get_grid());
			
			// Check to see if AI has lost
			if (p2.isdead()){
				System.out.println("\n\nYOU WIN!");
				return;
			}
			
			// Computer's move
			System.out.println("Enemy move");
			p2.do_attack(p1, in);
			p1.print_grid_alternate(p2.get_grid());
			
			// Check to see if player has lost
			if (p1.isdead()){
				System.out.println("\n\nGame Over, you lose");
				return;
			}
			
			System.out.println("______________________________________________________");
		}
	
	}

}
