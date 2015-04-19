package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Pyramid extends Piece {

	public Pyramid(Location loc) {
		super(loc);

	}

	public Pyramid() {

	}

	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> res = new ArrayList<Location>();
		Location possible;

		if (isUp()) {
			if (isRight()) {
				possible = new Location(up(), right());
				res.add(possible);
			}

			if (isLeft()) {
				possible = new Location(up(), left());
				res.add(possible);
			}
		}
		if (isDown()) {
			if (isRight()) {
				possible = new Location(down(), right());
				res.add(possible);
			}

			if (isLeft()) {
				possible = new Location(down(), left());
				res.add(possible);
			}
		}

		return res;
	}
}
