package eg.edu.guc.santorini.players;

import java.util.ArrayList;

import eg.edu.guc.santorini.tiles.*;
import eg.edu.guc.santorini.utilities.Location;

public class Player {
	private String name;
	private Piece t1, t2;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public ArrayList<Location> getLocs() {
		ArrayList<Location> res = new ArrayList<Location>();
		res.add(t1.getLoc());
		res.add(t2.getLoc());
		

		return res;
	}
	public Player(String name, int type) {
		this.name = name;
		this.type = type;
		if (type == 1) {
			t1 = new Cube();
			t2 = new Cube();
		}
		if (type == 2) {
			t1 = new Pyramid();
			t2 = new Pyramid();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Piece getT1() {
		return t1;
	}

	public void setT1(Piece t1) {
		this.t1 = t1;
	}

	public Piece getT2() {
		return t2;
	}

	public void setT2(Piece t2) {
		this.t2 = t2;
	}

}
