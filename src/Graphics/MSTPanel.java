package Graphics;

import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MSTPanel extends JPanel {
	private  List<Edge> mstEdges;
	private final List<Vertex> vertices;

	public MSTPanel(List<Vertex> vertices) {
		this.mstEdges = new ArrayList<>();
		this.vertices = vertices;
		setSize(400, 400);
		setBackground(Color.WHITE);
		setFocusable(true);
	}

	public void setMstEdges(List<Edge> mstEdges) {
		this.mstEdges = mstEdges;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

		for (Vertex vertex : vertices) {
			g.drawOval(vertex.getX(), vertex.getY(), vertex.getSize(), vertex.getSize());

			g.setFont(new Font("Monospaced", Font.BOLD, 18));
			g.drawString(Integer.toString(vertex.getNumber()), vertex.getXNumber(), vertex.getYNumber());
		}

		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (Edge edge : mstEdges) {
			Vertex vertex1 = vertices.stream().filter(vertex -> edge.getFrom() == vertex.getNumber()).findAny().orElse(null);
			Vertex vertex2 = vertices.stream().filter(vertex -> edge.getTo() == vertex.getNumber()).findAny().orElse(null);
			assert vertex1 != null;
			assert vertex2 != null;
			if (vertex1 == vertex2) {
				g2d.drawOval(vertex1.getXNumber(), vertex1.getYNumber(), 30, 30);
				g2d.drawString(Integer.toString(edge.getWeight()), vertex1.getXNumber() + 30, vertex1.getYNumber() + 40);
			}
			else {
				int m =(int) (0.5 * vertex1.getSize());
				int n = (int) (Math.sqrt(Math.pow(vertex2.getXNumber() - vertex1.getXNumber(), 2) + Math.pow(vertex2.getYNumber() - vertex1.getYNumber(), 2))) - m;
				int x1 = (n * vertex1.getXNumber() + m * vertex2.getXNumber()) / (m + n) + 5;
				int y1 = (n * vertex1.getYNumber() + m * vertex2.getYNumber()) / (m + n) - 5;
				int x2 = (m * vertex1.getXNumber() + n * vertex2.getXNumber()) / (m + n) + 5;
				int y2 = (m * vertex1.getYNumber() + n * vertex2.getYNumber()) / (m + n) - 5;
				g2d.drawLine(x1, y1, x2, y2);


				double angle;
				if (x2 > x1)
					angle = 0.24;
				else
					angle = -0.24;
				int halfX = (x1 + x2) / 2;
				int halfY = (y1 + y2) / 2;

				int x = (int)((halfX - x1) * Math.cos(angle) + (halfY - y1) * Math.sin(angle)) + x1;
				int y = (int)(-(halfX - x1) * Math.sin(angle) + (halfY - y1) * Math.cos(angle)) + y1;
				g2d.drawString(Integer.toString(edge.getWeight()), x, y);
			}
		}
	}
}