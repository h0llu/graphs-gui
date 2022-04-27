package Graph;

/*
* Взвешенный неориентированный граф
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Graph {
	// список рёбер
	private final List<Edge> edges;
	private int verticesNum;

	public Graph() {
		edges = new ArrayList<>();
		verticesNum = 0;
	}

	public void insertVertex() {
		verticesNum++;
	}

	public void insertEdge(int vertex1, int vertex2, int weight) throws GraphException {
		if (vertex1 < 0 || vertex1 >= verticesNum) {
			throw new GraphException("There is no vertex " + vertex1);
		}
		if (vertex2 < 0 || vertex2 >= verticesNum) {
			throw new GraphException("There is no vertex " + vertex2);
		}
		edges.add(new Edge(vertex1, vertex2, weight));
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public List<Edge> kruskalMST() throws GraphException {
		if (verticesNum == 0) {
			throw new GraphException("There are no vertices");
		}
		DSU dsu = new DSU(verticesNum);
		List<Edge> mstEdges = new ArrayList<>();
		edges.sort((edge1, edge2) -> edge1.weight - edge2.weight);
		for (Edge edge : edges) {
			if (dsu.find(edge.from) != dsu.find(edge.to)) {
				mstEdges.add(edge);
				dsu.unite(edge.from, edge.to);
			}
		}
		return mstEdges;
	}

	private static class DSU {
		private final int[] dsu;

		public DSU(int n) {
			dsu = new int[n];
			for (int i = 0; i < n; i++) {
				dsu[i] = i;
			}
		}

		public int find(int x) {
			if (dsu[x] == x) return x;
			return dsu[x] = find(dsu[x]);
		}

		public void unite(int x, int y) {
			x = find(x);
			y = find(y);
			if (x == y)
				return;
			if (new Random().nextInt() % 2 == 0) {
				dsu[x] = y;
			} else {
				dsu[y] = x;
			}
		}
	}

}