package de.resolveit.graphicsbug;

import javax.swing.*;
import java.awt.*;

public class LineRenderBug {
	public static void main(String[] argv) {
		JFrame frame = new JFrame("LineRenderBug");
		frame.getContentPane().add(
				new Component() {
					float globalDashPhase = 0;

					{
						setMinimumSize(new Dimension(250, 250));
						setPreferredSize(new Dimension(250, 250));
					}

					@Override
					public void paint(Graphics g_) {
						Graphics2D g = (Graphics2D) g_;
						globalDashPhase += 0.2;
						Stroke thindashed = new BasicStroke(1.0f, // line width
								/* cap style */BasicStroke.CAP_BUTT, /* join style, miter limit */BasicStroke.JOIN_BEVEL, 1.0f,
								/* the dash pattern */new float[]{8.0f, 3.0f, 8.0f, 3.0f},
								/* the dash globalDashPhase */globalDashPhase);
						Stroke thindashed2 = new BasicStroke(1.0f, // line width
								/* cap style */BasicStroke.CAP_BUTT, /* join style, miter limit */BasicStroke.JOIN_BEVEL, 1.0f,
								/* the dash pattern */new float[]{5.0f, 3.0f, 5.0f, 3.0f},
								/* the dash globalDashPhase */globalDashPhase);
						g.setXORMode(Color.WHITE);
						g.setStroke(thindashed);
						int width = getWidth();
						int height = getHeight();
						g.drawLine(0, 0, width, height);
						g.drawLine(1, 0, width, 1);
						g.drawLine(0, 1, 1, height);
						g.setStroke(thindashed2);
						g.drawLine(width, 0, width, height);
						g.drawLine(1, height, width, 1);
						g.drawLine(0, height, width, height);

						int x = (int) (globalDashPhase % width);
						g.drawLine(x, 0, x, height);
						g.setStroke(thindashed);
						int y = (int) (globalDashPhase % height);
						g.drawLine(0, y, width, y);
					}
				}, BorderLayout.CENTER);
		Timer timer = new Timer(10, (e) -> frame.repaint());
		timer.start();
		frame.pack();
		frame.setMinimumSize(new Dimension(250, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
