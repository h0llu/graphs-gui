package Graph;

public class GraphException extends Exception {
	public GraphException(String message) {
		super("Graph Exception: " + message);
	}
}
