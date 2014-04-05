package com.github.ZXSkelobrine.neutral.components;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Switch extends JComponent implements MouseListener {
	private static final long serialVersionUID = -4731477956048335021L;
	boolean on = false;
	BufferedImage tile_on;
	BufferedImage tile_off;
	String tile_on_path = "/images/switch_on.png";
	String tile_off_path = "/images/switch_off.png";

	public Switch() {
		super();
		enableInputMethods(true);
		addMouseListener(this);
		try {
			tile_on = ImageIO.read(Switch.class.getResource(tile_on_path));
			tile_off = ImageIO.read(Switch.class.getResource(tile_off_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		int h = super.getSize().height;
		int w = super.getSize().width;
		if (on) {
			System.out.println(on);
			g.drawImage(tile_on, 0, 0, w, h, null);
		} else {
			System.out.println(on);
			g.drawImage(tile_off, 0, 0, w, h, null);
		}
		super.paintComponents(g);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		on = !on;
		super.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public boolean serverEnabled() {
		return on;
	}

}
