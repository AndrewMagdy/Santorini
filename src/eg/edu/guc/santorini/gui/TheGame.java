package eg.edu.guc.santorini.gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.border.Border;

import java.awt.event.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class TheGame extends JLayeredPane implements MouseListener {
	private JPanel textPanel;
	private JLabel[][] labels;
	private JLabel text;
	private JButton newGame;
	private Adapter adapter;
	private GuiMain parent;
	private InitPlayers previous;
	private String p1 = "";
	private String p2 = "";
	private int p1Type = 1;
	private int p2Type = 1;

	private ImageIcon[] images = new ImageIcon[21];

	public TheGame(GuiMain parent) throws IOException {
		super();
		this.parent = parent;
		textPanel = new JPanel(new FlowLayout());

		newGame = new JButton("New Game");
		newGame.setBounds(398, 0, 120, 60);
		newGame.addMouseListener(this);
		newGame.setName("n");

		text = new JLabel();
		text.setFont(new Font("Arial", Font.PLAIN, 15));

		textPanel.add(text);
		textPanel.setBounds(0, 15, 399, 60);

		this.add(newGame, new Integer(1));
		this.add(textPanel, new Integer(0));

		labels = new JLabel[5][5];

		loadImages();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				labels[i][j] = new JLabel(images[0]);
				labels[i][j].setName("" + i + j);
				labels[i][j].setBounds(j * 105, (i * 105) + 70, 98, 101);
				labels[i][j].addMouseListener(this);
				this.add(labels[i][j], new Integer(2));

			}

		}

	}

	public void start(String player1Name, int type1, String player2Name,
			int type2) {
		adapter = new Adapter(player1Name, type1, player2Name, type2);
		render();
	}

	public void loadImages() throws IOException {

		for (int i = 0; i < 21; ++i) {

			images[i] = new ImageIcon(ImageIO.read(getClass().getResource(
					"t" + i + ".png")));

		}
	}

	public void render() {
		Border redBorder = BorderFactory.createLineBorder(Color.ORANGE, 5);
		text.setText(adapter.getTurn());
		int[][] x = adapter.getBoard();
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				labels[i][j].setIcon(images[x[i][j]]);

			}

		}
		if (!adapter.isGameover()) {
			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 5; ++j) {
					if (adapter.getHighlight()[i][j]) {
						labels[i][j].setBorder(redBorder);
					} else {
						labels[i][j].setBorder(null);
					}
				}
			}
		}

		if (adapter.isGameover()) {
			text.setText(adapter.getWinner());

			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 5; ++j) {
					labels[i][j].setBorder(null);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String cmd = ((JComponent) e.getSource()).getName();
		if (cmd.equals("n")) {
			parent.remove(this);
			parent.add(previous);
			parent.validate();
			parent.repaint();
		} else {
			int y = cmd.charAt(0) - '0';
			int x = cmd.charAt(1) - '0';
			adapter.click(y, x);
			render();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public int getP1Type() {
		return p1Type;
	}

	public void setP1Type(int p1Type) {
		this.p1Type = p1Type;
	}

	public int getP2Type() {
		return p2Type;
	}

	public void setP2Type(int p2Type) {
		this.p2Type = p2Type;
	}

	public Adapter getAdapter() {
		return adapter;
	}

	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}

	public InitPlayers getPrevious() {
		return previous;
	}

	public void setPrevious(InitPlayers previous) {
		this.previous = previous;
	}

}
