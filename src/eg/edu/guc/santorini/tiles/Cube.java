package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {

	public Cube(Location loc) {
		super(loc);

	}

	public Cube() {
		super();

	}

	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> res = new ArrayList<Location>();
		Location possible;
		if (isUp()) {
			possible = new Location(up(), this.getLoc().getX());
			res.add(possible);
		}
		if (isRight()) {
			possible = new Location(this.getLoc().getY(), right());
			res.add(possible);
		}

		if (isLeft()) {
			possible = new Location(this.getLoc().getY(), left());
			res.add(possible);
		}

		if (isDown()) {
			possible = new Location(down(), this.getLoc().getX());
			res.add(possible);
		}

		return res;
	}

}
