package Graphics;

public class Vertex {
	private final int x;
	private final int y;
	private final int size;
	private final int number;

	public Vertex(int x, int y, int number) {
		this.size = 50;
		this.x = x - size / 2;
		this.y = y - size / 2;
		this.number = number;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getSize() {
		return size;
	}
	public int getNumber() {
		return number;
	}
	public int getXNumber() {
		return x + (int)(0.4 * size);
	}
	public int getYNumber() {
		return y + (int)(0.6 * size);
	}
}