package ships;

import players.Players;

public class Ships {
	private int x, y, direction, health, length;
	private char[] pos_list;
	private String type;
	
	
	public Ships(int x, int y, int direction, int length, String type){
		// position on grid
		this.x = x;
		this.y = y;
		
		// direction 0 = right, direction 1 == down
		this.direction = direction;
		
		this.health = length;
		this.length = length;
		
		// only did this for printing out which ship sunk
		this.type = type;
		
		// a list for the ship to check which parts of itself have been hit
		pos_list = new char[health];	
	}
	
	// Outputs whether the ships been hit (same spot can't be hit more than once, will return false if that's the case)
	public boolean attack(int x, int y){
		// If going to the right, see if the hit is on the right y axis and if it collides with any position on the x axis
		if (direction == 1){
			if (x >= this.x && x < (this.x + length) && y == this.y){
				if (pos_list[x - this.x] != 'H'){
					health--;
					pos_list[x - this.x] = 'H';
					return true;
				}
			}
		}
		// If going to the down, see if the hit is on the right x axis and if it collides with any position on the y axis
		else{
			if (y >= this.y && y < (this.y + length) && x == this.x){
				if (pos_list[y - this.y] != 'H'){
					health--;
					pos_list[y - this.y] = 'H';
					return true;
				}
			}
		}
		return false;
	}
	
	// Check to see if the ship has sunk
	public boolean check(){
		if (health <= 0){
			System.out.printf("\t\tYou sunk my %s!\n", type);
			return true;
		}
		return false;
	}

	// Put the ship on a grid that is passed in
	public void initiate_grid(String[][] grid) {
		if (direction == 1){
			for(int i = x; i < x + length; i++)
				grid[i][y] = type.substring(0,1);
		}
		else{
			for(int i = y; i < y + length; i++)
				grid[x][i] = type.substring(0,1);
		}
		
	}
}
