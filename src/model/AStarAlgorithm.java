package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AStarAlgorithm {

	// private Node start;
	private static int DEFAULT_HV_COST = 50; // Horizontal - Vertical Cost
	private int hvCost;
	// private int diagonalCost;
	private Node[][] searchArea;
	private PriorityQueue<Node> openList;
	private List<Node> closedList;
	private Node initialNode;
	private Node finalNode;
	private int[][] map;

	public AStarAlgorithm(int[][] map1, int startX, int startY, int goalX, int goalY, int hvCost) {
		map = map1;
		this.hvCost = hvCost;
		setInitialNode(new Node(startX, startY));
		setFinalNode(new Node(goalX, goalY));
		this.searchArea = new Node[map.length][map[0].length];
		this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node node0, Node node1) {
				return node0.getF() < node1.getF() ? -1 : node0.getF() > node1.getF() ? 1 : 0;
			}
		});
		setNodes();
		this.closedList = new ArrayList<Node>();
	}

	public AStarAlgorithm(int[][] map1, int startX, int startY, int goalX, int goalY) {
		this(map1, startX, startY, goalX, goalY, DEFAULT_HV_COST);
	}

	private void setNodes() {
		for (int i = 0; i < searchArea.length; i++) {
			for (int j = 0; j < searchArea[0].length; j++) {
				Node node = new Node(i, j);
				node.calculateHeuristic(getFinalNode());
				this.searchArea[i][j] = node;
			}
		}
	}

	public List<Node> findPath() {
		openList.add(initialNode);
		while (!isEmpty(openList)) {
			Node currentNode = openList.poll();
			closedList.add(currentNode);
			if (isFinalNode(currentNode)) {
				return getPath(currentNode);
			} else {
				addAdjacentNodes(currentNode);
			}
		}
		return new ArrayList<Node>();
	}

	private List<Node> getPath(Node currentNode) {
		List<Node> path = new ArrayList<Node>();
		path.add(currentNode);
		Node parent;
		while ((parent = currentNode.getParent()) != null) {
			path.add(0, parent);
			currentNode = parent;
		}
		return path;
	}

	private void addAdjacentNodes(Node currentNode) {
		addAdjacentUpperRow(currentNode);
		addAdjacentMiddleRow(currentNode);
		addAdjacentLowerRow(currentNode);
	}

	private void addAdjacentLowerRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int lowerRow = row + 1;
		if (lowerRow < getSearchArea().length) {
			checkNode(currentNode, col, lowerRow, getHvCost());
		}
	}

	private void addAdjacentMiddleRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int middleRow = row;

		if (col - 1 >= 0) {
			checkNode(currentNode, col - 1, middleRow, getHvCost());
		}
		if (col + 1 < getSearchArea()[0].length) {
			checkNode(currentNode, col + 1, middleRow, getHvCost());
		}
	}

	private void addAdjacentUpperRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int upperRow = row - 1;
		if (upperRow >= 0) {
			checkNode(currentNode, col, upperRow, getHvCost());
			// }
		}
	}

	private void checkNode(Node currentNode, int col, int row, int cost) {
		Node adjacentNode = getSearchArea()[row][col];
		if (map[row][col] != 1) {
			if (!getClosedList().contains(adjacentNode)) {
				if (!getOpenList().contains(adjacentNode)) {
					adjacentNode.setNodeData(currentNode, cost);
					getOpenList().add(adjacentNode);
				} else {
					boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
					if (changed) {
						getOpenList().remove(adjacentNode);
						getOpenList().add(adjacentNode);
					}
				}
			}
		}
	}

	private boolean isFinalNode(Node currentNode) {
		return currentNode.equals(finalNode);
	}

	private boolean isEmpty(PriorityQueue<Node> openList) {
		return openList.size() == 0;
	}

	public Node getInitialNode() {
		return initialNode;
	}

	public void setInitialNode(Node initialNode) {
		this.initialNode = initialNode;
	}

	public Node getFinalNode() {
		return finalNode;
	}

	public void setFinalNode(Node finalNode) {
		this.finalNode = finalNode;
	}

	public Node[][] getSearchArea() {
		return searchArea;
	}

	public void setSearchArea(Node[][] searchArea) {
		this.searchArea = searchArea;
	}

	public PriorityQueue<Node> getOpenList() {
		return openList;
	}

	public void setOpenList(PriorityQueue<Node> openList) {
		this.openList = openList;
	}

	public List<Node> getClosedList() {
		return closedList;
	}

	public void setClosedList(List<Node> closedList) {
		this.closedList = closedList;
	}

	public int getHvCost() {
		return hvCost;
	}

	public void setHvCost(int hvCost) {
		this.hvCost = hvCost;
	}

	public static void main(String[] args) {
		EntityManager en = new EntityManager();
//		AStarAlgorithm as = new AStarAlgorithm(en.theBrickArr(), 4, 1, 6, 2);
		AStarAlgorithm as = new AStarAlgorithm(en.theBrickArr(), 3, 9, 5, 13);// k tim duoc path???
		System.out.println(as.findPath());
	}
}
