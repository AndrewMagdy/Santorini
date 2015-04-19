package eg.edu.guc.santorini.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class InitPlayers extends JLayeredPane implements MouseListener {

	private GuiMain parent;
	private StartWindow previous;
	private TheGame next;
	private JTextField player1Name;
	private JTextField player2Name;
	private JButton p1Cube;
	private JButton p1Pyramid;
	private JButton p2Cube;
	private JButton p2Pyramid;
	private int p1Type = 1;
	private int p2Type = 1;
	private JButton start;

	public void init() {
		player1Name = new JTextField();
		player1Name.setBounds(30, 240, 170, 40);
		player1Name.setFont(new Font("Arial", 60, 23));
		player1Name.setText("Player 1");
		player2Name = new JTextField();
		player2Name.setBounds(330, 240, 170, 40);
		player2Name.setText("Player 2");
		player2Name.setFont(new Font("Arial", 60, 23));
		start = new JButton("Start Game");
		start.setBounds(180, 90, 170, 70);
		start.setActionCommand("start");
		start.addMouseListener(this);
	}

	public InitPlayers(GuiMain parent) throws IOException {
		init();
		this.parent = parent;
		Border redBorder = BorderFactory.createLineBorder(Color.ORANGE, 5);
		ImageIcon img = new ImageIcon(ImageIO.read(getClass().getResource(
				"cube1.png")));
		p1Cube = new JButton();
		p1Cube.setBounds(65, 300, 100, 100);
		p1Cube.setActionCommand("p1c");
		p1Cube.addMouseListener(this);
		p1Cube.setIcon(img);
		p1Cube.setBorder(redBorder);
		img = new ImageIcon(
				ImageIO.read(getClass().getResource("pyramid1.png")));
		p1Pyramid = new JButton();
		p1Pyramid.setBounds(65, 410, 100, 100);
		p1Pyramid.setActionCommand("p1p");
		p1Pyramid.addMouseListener(this);
		p1Pyramid.setIcon(img);
		img = new ImageIcon(ImageIO.read(getClass().getResource("cube2.png")));
		p2Cube = new JButton();
		p2Cube.setBounds(365, 300, 100, 100);
		p2Cube.addMouseListener(this);
		p2Cube.setActionCommand("p2c");
		p2Cube.setIcon(img);
		p2Cube.setBorder(redBorder);
		img = new ImageIcon(
				ImageIO.read(getClass().getResource("pyramid2.png")));
		p2Pyramid = new JButton();
		p2Pyramid.setBounds(365, 410, 100, 100);
		p2Pyramid.setActionCommand("p2p");
		p2Pyramid.addMouseListener(this);
		p2Pyramid.setIcon(img);
		this.add(p1Cube);
		this.add(p1Pyramid);
		this.add(p2Cube);
		this.add(p2Pyramid);
		this.add(player1Name);
		this.add(player2Name);
		this.add(start);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String action = ((JButton) e.getSource()).getActionCommand();
		if (action.equals("start")) {
			next.start(player1Name.getText(), p1Type, player2Name.getText(),
					p2Type);
			parent.remove(this);
			parent.add(next);
			parent.validate();
			parent.repaint();
			return;
		}
		Border redBorder = BorderFactory.createLineBorder(Color.ORANGE, 5);

		if (action.equals("p1c")) {
			p1Type = 1;
			p1Cube.setBorder(redBorder);
			p1Pyramid.setBorder(null);
		}
		if (action.equals("p1p")) {
			p1Type = 2;
			p1Pyramid.setBorder(redBorder);
			p1Cube.setBorder(null);
		}
		if (action.equals("p2c")) {
			p2Type = 1;
			p2Cube.setBorder(redBorder);
			p2Pyramid.setBorder(null);
		}
		if (action.equals("p2p")) {
			p2Type = 2;
			p2Pyramid.setBorder(redBorder);
			p2Cube.setBorder(null);
		}

	}

	public StartWindow getPrevious() {
		return previous;
	}

	public void setPrevious(StartWindow previous) {
		this.previous = previous;
	}

	public TheGame getNext() {
		return next;
	}

	public void setNext(TheGame next) {
		this.next = next;
	}

}
