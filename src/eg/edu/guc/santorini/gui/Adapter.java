package eg.edu.guc.santorini.gui;

import java.util.ArrayList;
import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Adapter {
	private Board board;
	private Player player1;
	private Player player2;
	private boolean move;
	private boolean place;
	private boolean highlight;
	private Piece piece;
	private Location clickLoc;
	// private String winner;
	private String message = "";

	public boolean isGameover() {
		return board.isGameOver();
	}

	public String getTurn() {
		if (message.length() > 1) {
			return message;
		}
		return board.getTurn().getName() + "'s Turn !!!";

	}

	public String getWinner() {
		return board.getWinner().getName()
				+ " Won The Game !!!!! \n Play Again ?";
	}

	public Adapter(String name1, int type1, String name2, int type2) {
		player1 = new Player(name1, type1);
		player2 = new Player(name2, type2);
		board = new Board(player1, player2);
	}

	public boolean[][] getHighlight() {
		boolean[][] res = new boolean[5][5];
		if (getPiece() == null) {
			return res;
		}
		ArrayList<Location> allPossible;
		ArrayList<Location> possible = new ArrayList<Location>();
		if (highlight) {
			allPossible = getPiece().possibleMoves();
			for (Location location : allPossible) {
				if (board.canMove(getPiece(), location)) {
					possible.add(location);

				}
			}

		}

		if (place) {
			allPossible = getPiece().possiblePlacements();
			for (Location location : allPossible) {
				if (board.canPlace(getPiece(), location)) {
					possible.add(location);

				}
			}

		}

		for (Location location : possible) {
			int y = location.getY();
			int x = location.getX();
			res[y][x] = true;
		}

		return res;
	}

	public int[][] getBoard() {
		int[][] res = new int[5][5];
		String[][] b = board.display();
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				res[i][j] = b[i][j].charAt(0) - '0';
				if (b[i][j].length() > 1) {
					char type = b[i][j].charAt(1);
					char player = b[i][j].charAt(2);
					if (type == 'C') {
						if (player == '1') {
							res[i][j] += 5;
						} else {
							res[i][j] += 13;
						}
					}
					if (type == 'P') {
						if (player == '1') {
							res[i][j] += 9;
						} else {
							res[i][j] += 17;
						}

					}

				}
			}

		}
		return res;
	}

	public void place(Location location) {
		Piece piece = getPiece();
		try {
			board.place(piece, location);
			place = false;
			clickLoc = null;

		} catch (InvalidPlacementException e) {
			place = true;
			message = " Wrong Placement";
		}

	}

	public void move(Location location) {
		piece = getPiece();
		try {
			place = true;
			move = false;
			highlight = false;
			board.move(piece, location);

		} catch (InvalidMoveException e) {
			place = false;
			move = true;
			highlight = true;
			message = "Wrong Move";
		}

	}

	public Piece getPiece() {
		Piece piece;
		if (player1.getT1().getLoc().equals(clickLoc)) {
			piece = player1.getT1();
		} else if (player1.getT2().getLoc().equals(clickLoc)) {
			piece = player1.getT2();
		}

		else if (player2.getT1().getLoc().equals(clickLoc)) {
			piece = player2.getT1();
		}

		else if (player2.getT2().getLoc().equals(clickLoc)) {
			piece = player2.getT2();
		} else {
			piece = null;
		}
		return piece;
	}

	// add click on my piece
	public void click(int y, int x) {
		Location curr = new Location(y, x);
		message = "";

		if (move && !board.getLocs().contains(curr)) {

			move(curr);
			clickLoc = curr;
			return;
		}
		if (place) {

			place(curr);

			return;
		}
		if (board.getLocs().contains(curr)) {
			highlight = true;
			clickLoc = curr;
			if (board.getTurn().getLocs().contains(curr)) {
				move = true;
			}

			// highlight
			return;
		}

		move = false;

	}

}
