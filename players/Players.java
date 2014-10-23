package players;
import java.util.Map;
import java.util.Scanner;


import ships.*;

public class Players {
	private String[][] grid;
	private Map<String, Ships> ship_list;
	private int numships;
	
	public Players(Map<String, Ships> ship_list){
		// INITIALIZE: Make new grid
		grid = new String[10][10];
		
		// INITIALIZE: fill grid
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				grid[i][j] = "e";
			}
		}
		
		// INITIALIZE: put the ships on player's grid
		this.ship_list = ship_list;
		numships = 4;
		
		for (Ships s : this.ship_list.values())
			s.initiate_grid(grid);

	}
	
	// DEBUG: Just used for debuging and printing player's full list
	public void print_grid_full(){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++)
				System.out.print(grid[j][i]);
			System.out.println();
		}
	}
	
	// DEBUG: used for seeing simple enemy list
	public void print_grid_hidden() {
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if (grid[j][i] == "h")
					System.out.print(grid[j][i]);
				else
					System.out.print("e");
			}
			System.out.println();
		}
	}
	
	// PRINT: the main print grid function used, shows both player's grid and attack grid (like the board game)
	public void print_grid_alternate(String[][] othergrid) {
		
		// PRINT: Header
		System.out.println();
		System.out.println("       Defense Grid!\t\t\t       Attack Grid!");
		System.out.println("    1 2 3 4 5 6 7 8 9 10 \t\t    1 2 3 4 5 6 7 8 9 10 \n    ____________________\t\t   ____________________");
		
		// PRINT: Main grids
		for (int i = 0; i < 10; i++){
			
			// PRINT: Letters on side
			System.out.printf("%c | ", (char)(i + 65));
			
			// PRINT: Actual row
			for (int j = 0; j < 10; j++){
				System.out.print(grid[i][j] + " ");
			}
			
			// PRINT: Numbers on other grid
			System.out.printf("|\t\t%c | ", (char)(i + 65));
			
			// PRINT: Enemy's Grid row
			for (int j = 0; j < 10; j++){
				if (othergrid[i][j] == "H" || othergrid[i][j] == "M")
					System.out.print(othergrid[i][j]);
				else
					System.out.print("e");
				System.out.print(" ");
			}
			
			System.out.println("|");
		}
	}

	// ATTACK: Player gets attacked
	// Returns true if player got hit
	public boolean attack(int x, int y, Scanner in, Players attacker){
		// Check the grid with player's attack coordinates
		
		// Player got a hit
		if (grid[x][y] != "M" && grid[x][y] != "H" && grid[x][y] != "e"){
			System.out.println("\t\t\tHit!");
			grid[x][y] = "H";
			
			// Check if any of the ships are sunk
			for (Ships s : ship_list.values()){
				if(s.attack(x,y)){
					if(s.check())
						numships--;
				}
			}
			return true;
		}
		
		// Player Missed
		else if (grid[x][y] == "e"){
			System.out.println("\t\t\tMiss");
			grid[x][y] = "M";
		}
		
		// Player tried to attack somewhere they did before, allow them to go again
		// must redo either the AI's attack sequence or Human's input sequence
		// Must cast to the appropriate class
		else{
			if (attacker instanceof AI){
				System.out.println("Opponent already attacked, must rethink it's decision");
				((AI) attacker).do_attack(this, in);
			}
			else if (attacker instanceof Human){
				System.out.println("You already attacked this space, please choose another coordinate");
				((Human) attacker).manage_input(in, this);
			}
		}
		return false;
	}
	
	public boolean isdead(){
		if (numships <= 0)
			return true;
		return false;
	}
	
	public String[][] get_grid() {
		return grid;
	}
}
