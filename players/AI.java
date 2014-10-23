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
		
		output_list.put("B", randomly_place("Battleship"));
		output_list.put("D", randomly_place("Destroyer"));
		output_list.put("S", randomly_place("Submarine"));
		output_list.put("C", randomly_place("Cruiser"));
		
		return output_list;
	}
	
	public boolean do_attack(Players p1, Scanner in) {
		Random attackvalue = new Random();
		p1.attack(attackvalue.nextInt(10), attackvalue.nextInt(10), in, this);
		return false;
	}
	
	public static Ships randomly_place(String type){
		System.out.println(type);
		Random ranpos = new Random();
		
		if (type == "Battleship")
			return new Battleship(ranpos.nextInt(9), ranpos.nextInt(9),ranpos.nextInt(2));
		else if (type == "Destroyer")
			return new Destroyer(ranpos.nextInt(9), ranpos.nextInt(9),ranpos.nextInt(2));
		else if (type == "Submarine")
			return new Submarine(ranpos.nextInt(9), ranpos.nextInt(9),ranpos.nextInt(2));
		else
			return new Cruiser(ranpos.nextInt(9), ranpos.nextInt(9),ranpos.nextInt(2));
	}
	
}
