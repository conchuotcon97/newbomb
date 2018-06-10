package model;

import java.util.LinkedList;

import java.util.List;

public class Node {
	private int g;
	private int f;
	private int h;
	private int row;
	private int col;
	private Node parent;

	public Node(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	// tính heurictic
	public void calculateHeuristic(Node finalNode) {
		// if (map[row][col] != 1) {

		this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
		// System.out.println("final node=" + finalNode.getRow() + " " +
		// finalNode.getCol() + " | startNode="
		// + getRow() + " " + getCol());
		// System.out.println("H=" + h + " G=" + g + " F=" + f);
		// }
	}

	public boolean checkBetterPath(Node currentNode, int cost) {
		int gCost = currentNode.getG() + cost;
		if (gCost < getG()) {
			setNodeData(currentNode, cost);
			// System.out.println("gCost + " + gCost);
			return true;
		}
		// System.out.println("G=" + getG());
		return false;
	}

	//
	public void setNodeData(Node currentNode, int cost) {
		int gCost = currentNode.getG() + cost;
		setParent(currentNode);
		setG(gCost);
		calculateFinalCost();
	}

	private void calculateFinalCost() {
		int finalCost = getG() + getH();
		setF(finalCost);
	}

	// so sánh 2 node
	@Override
	public boolean equals(Object arg0) {
		Node other = (Node) arg0;
		return this.getRow() == other.getRow() && this.getCol() == other.getCol();
	}

	@Override
	public String toString() {
		return "Node [row=" + row + ", col=" + col + "]";
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}

// public class Node {
// private boolean start;
// private boolean target;
// private Node parent;
// private Node children;
// private int posX;
// private int posY;
// private int H;
// private int G;
// private int F;
// private boolean buffable;
// private final int MOVE_COST = 50;
//
// public Node(int x, int y, boolean buffable) {
// posX = x;
// posY = y;
// this.buffable = buffable;
//
// }
//
// public void calH(Node goal) {
// H = (Math.abs(getPosX() - goal.getPosX()) + Math.abs(getPosX() -
// goal.getPosY())) * MOVE_COST;
//
// }
//
// public int calG(Node parent) {
// return parent.getG() + MOVE_COST;
// }
//
// public int calF() {
// return F = G + H;
//
// }
//
// public boolean isStart() {
// return start;
// }
//
// public void setStart(boolean start) {
// this.start = start;
// }
//
// public boolean isTarget() {
// return target;
// }
//
// public void setTarget(boolean target) {
// this.target = target;
// }
//
// public int getPosX() {
// return posX;
// }
//
// public void setPosX(int posX) {
// this.posX = posX;
// }
//
// public int getPosY() {
// return posY;
// }
//
// public void setPosY(int posY) {
// this.posY = posY;
// }
//
// public int getH() {
// return H;
// }
//
// public void setH(int h) {
// H = h;
// }
//
// public int getG() {
// return G;
// }
//
// public void setG(int g) {
// G = g;
// }
//
// public int getF() {
// return F;
// }
//
// public void setF(int f) {
// F = f;
// }
//
// public boolean isBuffable() {
// return buffable;
// }
//
// public void setBuffable(boolean buffable) {
// this.buffable = buffable;
// }
//
// public Node getParent() {
// return parent;
// }
//
// public void setParent(Node parent) {
// this.parent = parent;
// }
//
// public Node getChildren() {
// return children;
// }
//
// public void setChildren(Node children) {
// this.children = children;
// }
//
//
// @Override
// public boolean equals(Object obj) {
// if (!(obj instanceof Node) || obj == null)
// return false;
// Node that = (Node) obj;
// if (this.getPosX() == that.getPosX() && this.getPosY() == that.getPosY() &&
// this.isBuffable() == that.isBuffable())
// return true;
// return false;
// }
//
// }
