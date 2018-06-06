package model;

import java.util.LinkedList;
import java.util.List;

public class Node  {
	private boolean start;
	private boolean target;
	private Node parent;
	private Node children;
	private int posX;
	private int posY;
	private int H;
	private int G;
	private int F;
	private boolean buffable;
	private final int MOVE_COST = 50;

	public Node(int x, int y, boolean buffable) {
		posX = x;
		posY = y;
		this.buffable = buffable;

	}

	public void calH(Node goal) {
		H = (Math.abs(getPosX() - goal.getPosX()) + Math.abs(getPosX() - goal.getPosY())) * MOVE_COST;

	}

	public int calG(Node parent) {
		return parent.getG() + MOVE_COST;
	}

	public int calF() {
		return F = G + H;

	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isTarget() {
		return target;
	}

	public void setTarget(boolean target) {
		this.target = target;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public boolean isBuffable() {
		return buffable;
	}

	public void setBuffable(boolean buffable) {
		this.buffable = buffable;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getChildren() {
		return children;
	}

	public void setChildren(Node children) {
		this.children = children;
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node) || obj == null)
			return false;
		Node that = (Node) obj;
		if (this.getPosX() == that.getPosX() && this.getPosY() == that.getPosY() && this.isBuffable() == that.isBuffable())
			return true;
		return false;
	}

}
