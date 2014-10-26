package players;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import ships.*;

public class AI extends Players {

	public AI() {
		super(make_grid());
		// TODO Auto-generated constructor stub
	}

	private static Map<String, Ships> make_grid() {

		Map<String, Ships> output_list = new HashMap();
		
		output_list.put("B", randomly_place("Battleship", output_list));
		output_list.put("D", randomly_place("Destroyer", output_list));
		output_list.put("S", randomly_place("Submarine", output_list));
		output_list.put("C", randomly_place("Cruiser", output_list));
		
		return output_list;
	}
	
	public boolean do_attack(Players p1, Scanner in) {
		Random attackvalue = new Random();
		p1.attack(attackvalue.nextInt(10), attackvalue.nextInt(10), in, this);
		return false;
	}
	
	public static Ships randomly_place(String type, Map<String, Ships> list){
		System.out.println(type);
		Random ranpos = new Random();
		int x = ranpos.nextInt(9);
        int y = ranpos.nextInt(9);
        int direction = ranpos.nextInt(2);

        Ships newship = null;

        if (type == "Battleship")
            newship = new Battleship(x, y,ranpos.nextInt(2));
        else if (type == "Destroyer")
            newship = new Destroyer(x, y,ranpos.nextInt(2));
        else if (type == "Submarine")
            newship = new Submarine(x, y,ranpos.nextInt(2));
        else
            newship = new Cruiser(x, y,ranpos.nextInt(2));

        // Check to see if our new ship placement would overlap existing placed ships
        for (Ships s : list.values()){
            if(s.check_overlap(x, y, newship.get_length(),direction))
                return randomly_place(type, list);
        }

        return newship;
	}
	
}
