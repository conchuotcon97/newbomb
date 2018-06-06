package model;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void increateX(int n) {
		this.x += n;
	}

	public void increateY(int n) {
		this.y += n;
	}

	public void decreateX(int n) {
		this.x -= n;
	}

	public void decreateY(int n) {
		this.y -= n;
	}
	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		if (this.x == p.getX() && this.y == p.getY()) {
			return true;
		}
		return false;
	}
}
