package eg.edu.guc.santorini.gui;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

@SuppressWarnings("serial")
public class GuiMain extends JFrame implements MouseListener {

	private StartWindow start;
	private InitPlayers players;
	private TheGame game;

	public GuiMain() throws IOException {
		super("Santorini");
		setSize(519, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		start = new StartWindow(this);
		players = new InitPlayers(this);
		game = new TheGame(this);

		start.setNext(players);

		players.setPrevious(start);
		players.setNext(game);

		game.setPrevious(players);
		this.add(start);

		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new GuiMain();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
