package eg.edu.guc.santorini;

import java.util.ArrayList;
import java.util.Arrays;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.*;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {

	private Player fPlayer, sPlayer, winner;
	private int[][] board = new int[SIDE][SIDE];
	private boolean turn, moved;
	private Piece lastMoved;

	public Board(Player fPlayer, Player sPlayer) {
		this.fPlayer = fPlayer;
		this.sPlayer = sPlayer;
		Location fLoc = new Location(0, 0);
		Location sLoc = new Location(4, 1);
		fPlayer.getT1().setLoc(fLoc);
		fPlayer.getT2().setLoc(sLoc);
		fLoc = new Location(0, 3);
		sLoc = new Location(4, 4);
		sPlayer.getT1().setLoc(fLoc);
		sPlayer.getT2().setLoc(sLoc);

	}

	public boolean isWinner(Player player) {
		return player == getWinner();
	}

	public boolean hasNoMoves(Player player) {
		ArrayList<Location> poss1 = player.getT1().possibleMoves();
		ArrayList<Location> poss2 = player.getT2().possibleMoves();

		if (poss1.isEmpty()) {
			if (poss2.isEmpty()) {
				return true;
			}
		}
		for (Location l : poss1) {
			if (canMove(player.getT1(), l)) {
				return false;
			}
		}
		for (Location l : poss2) {
			if (canMove(player.getT2(), l)) {
				return false;
			}
		}
		return true;
	}

	public boolean isGameOver() {
		return getWinner() != null;
	}

	public ArrayList<Location> getLocs() {
		ArrayList<Location> res = new ArrayList<Location>();
		res.add(fPlayer.getT1().getLoc());
		res.add(fPlayer.getT2().getLoc());
		res.add(sPlayer.getT1().getLoc());
		res.add(sPlayer.getT2().getLoc());

		return res;
	}

	
	public void move(Piece piece, Location newLocation)
			throws InvalidMoveException {

		if (!isGameOver()
				&& (getTurn().getT1().equals(piece) || getTurn().getT2()
						.equals(piece))) {
			if (!moved && canMove(piece, newLocation)) {
				piece.setLoc(newLocation);
				moved = true;
				lastMoved = piece;
				if ((board[newLocation.getY()][newLocation.getX()]) == 3) {
					winner = getTurn();

				}

				return;
			}
		}
		throw new InvalidMoveException();
	}

	// Make 4 Killed Cell
	public void place(Piece piece, Location newLocation)
			throws InvalidPlacementException {

		if (!isGameOver()
				&& (getTurn().getT1().equals(piece) || getTurn().getT2()
						.equals(piece))) {
			if (canPlace(piece, newLocation) && moved
					&& (lastMoved.equals(piece))) {
				board[newLocation.getY()][newLocation.getX()]++;
				moved = false;
				turn = !turn;
				lastMoved = null;

				if (hasNoMoves(getTurn())) {
					turn = !turn;
					winner = getTurn();
					turn = !turn;
				}

				return;
			}

		}
		throw new InvalidPlacementException();
	}

	public Player getfPlayer() {
		return fPlayer;
	}

	public void setfPlayer(Player fPlayer) {
		this.fPlayer = fPlayer;
	}

	public Player getsPlayer() {
		return sPlayer;
	}

	public void setsPlayer(Player sPlayer) {
		this.sPlayer = sPlayer;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean canMove(Piece piece, Location location) {
		int cX = piece.getLoc().getX();
		int cY = piece.getLoc().getY();
		int nX = location.getX();
		int nY = location.getY();

		if (piece.possibleMoves().contains(location)) {
			if (!getLocs().contains(location)) {
				if ((board[nY][nX] - board[cY][cX]) <= 1) {
					return true;
				}
			}
		}
		return false;
	}

	public String[][] display() {
		String[][] res = new String[SIDE][SIDE];
		for (String[] s : res) {
			Arrays.fill(s, "");
		}
		for (int i = 0; i < SIDE; ++i) {
			for (int j = 0; j < SIDE; ++j) {
				res[i][j] += board[i][j];
			}
		}
		int type1 = fPlayer.getType();
		int type2 = sPlayer.getType();
		Location f1 = fPlayer.getT1().getLoc();
		Location f2 = fPlayer.getT2().getLoc();
		Location s1 = sPlayer.getT1().getLoc();
		Location s2 = sPlayer.getT2().getLoc();
		if (type1 == 1) {
			res[f1.getY()][f1.getX()] += "C" + "1";
			res[f2.getY()][f2.getX()] += "C" + "1";
		}
		if (type1 == 2) {
			res[f1.getY()][f1.getX()] += "P" + "1";
			res[f2.getY()][f2.getX()] += "P" + "1";
		}

		if (type2 == 1) {
			res[s1.getY()][s1.getX()] += "C" + "2";
			res[s2.getY()][s2.getX()] += "C" + "2";
		}
		if (type2 == 2) {
			res[s1.getY()][s1.getX()] += "P" + "2";
			res[s2.getY()][s2.getX()] += "P" + "2";
		}

		return res;
	}

	public Player getWinner() {

		return winner;
	}

	public boolean canPlace(Piece piece, Location location) {
		if (piece.possiblePlacements().contains(location)) {
			if (!getLocs().contains(location)) {
				if (board[location.getY()][location.getX()] < 4) {
					return true;
				}
			}
		}
		return false;
	}

	public Player getTurn() {
		if (turn) {
			return sPlayer;
		}
		return fPlayer;
	}
}
