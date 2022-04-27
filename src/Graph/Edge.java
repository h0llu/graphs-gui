package Graph;

public class Edge {
	final int from;
	final int to;
	final int weight;

	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}
}