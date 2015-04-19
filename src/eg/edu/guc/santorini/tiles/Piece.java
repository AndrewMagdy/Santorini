package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {

	private Location loc;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		int add;
		if (loc == null) {
			add = 0;
		} else {
			add = loc.hashCode();
		}
		result = prime * result + add;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Piece)) {
			return false;
		}
		Piece other = (Piece) obj;
		if (loc == null) {
			if (other.loc != null) {
				return false;
			}
		} else if (!loc.equals(other.loc)) {
			return false;
		}
		return true;
	}

	public Location getLoc() {
		return loc;
	}

	public Piece(Location loc) {
		this.loc = loc;
	}

	public Piece() {

	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	protected int up() {
		return getLoc().getY() + 1;
	}

	protected int down() {
		return getLoc().getY() - 1;
	}

	protected int right() {
		return getLoc().getX() + 1;
	}

	protected int left() {
		return getLoc().getX() - 1;
	}

	protected boolean isUp() {
		return up() < 5;
	}

	protected boolean isDown() {
		return down() >= 0;
	}

	protected boolean isRight() {
		return right() < 5;
	}

	protected boolean isLeft() {
		return left() >= 0;
	}

	public abstract ArrayList<Location> possibleMoves();

	public ArrayList<Location> possiblePlacements() {
		ArrayList<Location> res = new ArrayList<Location>();
		Location possible;
		if (isUp()) {
			possible = new Location(up(), this.getLoc().getX());
			res.add(possible);
			if (isRight()) {
				possible = new Location(up(), right());
				res.add(possible);
			}

			if (isLeft()) {
				possible = new Location(up(), left());
				res.add(possible);
			}
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
