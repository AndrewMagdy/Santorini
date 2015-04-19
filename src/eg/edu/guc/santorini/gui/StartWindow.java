package eg.edu.guc.santorini.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class StartWindow extends JLayeredPane implements MouseListener {
	private JButton multi;
	private JButton single;
	private InitPlayers next;
	private GuiMain parent;

	public StartWindow(GuiMain parent) throws IOException {
		super();

		this.parent = parent;

		JLabel back = new JLabel();
		back.setBounds(0, 0, 519, 600);
		ImageIcon img = new ImageIcon(ImageIO.read(getClass().getResource(
				"back.png")));
		back.setIcon(img);
		this.add(back, new Integer(0));

		multi = new JButton("2 Players");
		multi.setBounds(150, 300, 200, 100);
		multi.setActionCommand("multiplayer");
		multi.addMouseListener(this);
		this.add(multi, new Integer(1));

		single = new JButton("1 Player");
		single.setBounds(150, 420, 200, 100);
		single.setActionCommand("singleplayer");
		single.addMouseListener(this);
		single.setEnabled(false);
		this.add(single, new Integer(1));

		next = null;

		this.setVisible(true);
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
	public void mouseReleased(MouseEvent e) {
		String action = ((JButton) e.getSource()).getActionCommand();
		if (action.equals("multiplayer")) {
			parent.remove(this);
			parent.add(next);
			parent.validate();
		}

	}

	public InitPlayers getNext() {
		return next;
	}

	public void setNext(InitPlayers next) {
		this.next = next;
	}

}