package Graphics;

import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphFrame extends JFrame {
	private final List<Vertex> vertices;
	private final Graph graph;

	public GraphFrame() {
		vertices = new ArrayList<>();
		graph = new Graph();

		setTitle("Graphs");
		setSize(800, 560);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GraphPanel graphPanel = new GraphPanel(graph, vertices);
		MSTPanel mstPanel = new MSTPanel(vertices);

		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font(error.getFont().getName(), Font.BOLD, 14));
		error.setBounds(0, 460, 300, 20);

		JLabel edge = new JLabel("Новое ребро от ");
		JTextField from = new JTextField();
		JLabel edge2 = new JLabel("к");
		JTextField to = new JTextField();
		JLabel w = new JLabel("с весом");
		JTextField weight = new JTextField();
		edge.setFont(new Font(edge.getFont().getName(), Font.BOLD, 14));
		from.setFont(new Font(from.getFont().getName(), Font.BOLD, 14));
		edge2.setFont(new Font(edge2.getFont().getName(), Font.BOLD, 14));
		to.setFont(new Font(to.getFont().getName(), Font.BOLD, 14));
		w.setFont(new Font(w.getFont().getName(), Font.BOLD, 14));
		weight.setFont(new Font(weight.getFont().getName(), Font.BOLD, 14));
		edge.setBounds(0, 420, 120, 20);
		from.setBounds(125, 420, 25, 20);
		edge2.setBounds(155, 420, 10, 20);
		to.setBounds(170, 420, 25, 20);
		w.setBounds(200, 420, 60, 20);
		weight.setBounds(265, 420, 25, 20);

		from.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Pattern p = Pattern.compile("[0-9]");
				Matcher m = p.matcher(Character.toString(e.getKeyChar()));
				if (!m.matches() || from.getText().length() == 2) {
					e.consume();
				}
			}
		});

		to.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Pattern p = Pattern.compile("[0-9]");
				Matcher m = p.matcher(Character.toString(e.getKeyChar()));
				if (!m.matches() || to.getText().length() == 2) {
					e.consume();
				}
			}
		});

		weight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Pattern p = Pattern.compile("[0-9]");
				Matcher m = p.matcher(Character.toString(e.getKeyChar()));
				if (!m.matches() || weight.getText().length() == 2) {
					e.consume();
				}
			}
		});

		add(edge);
		add(from);
		add(edge2);
		add(to);
		add(w);
		add(weight);
		add(error);

		JButton setNewEdge = new JButton("Построить ребро");
		JButton buildMST = new JButton("Построить MST");

		setNewEdge.setBounds(300, 420, 150, 20);
		buildMST.setBounds(500, 420, 150, 20);

		setNewEdge.addActionListener(actionEvent -> {
			if (from.getText().equals("") ||
				to.getText().equals("") ||
				weight.getText().equals("")) {
				return;
			}
			error.setText("");
			try {
				graph.insertEdge(Integer.parseInt(from.getText()), Integer.parseInt(to.getText()),
								 Integer.parseInt(weight.getText()));
			} catch (GraphException e) {
				error.setText(e.getMessage());
			}
			graphPanel.repaint();
		});

		buildMST.addActionListener(actionEvent -> {
			error.setText("");
			try {
				mstPanel.setMstEdges(graph.kruskalMST());
			} catch (GraphException e) {
				error.setText(e.getMessage());
			}
			mstPanel.repaint();
		});

		add(setNewEdge);
		add(buildMST);

		graphPanel.setBounds(0, 0, 400, 400);
		mstPanel.setBounds(400, 0, 400, 400);
		add(graphPanel);
		add(mstPanel);
		setVisible(true);
	}
}