package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;

import javax.management.Query;
import javax.xml.stream.events.NotationDeclaration;

public class AStar {
	private Node start;
	private Node goal;
	private Node current;
	private Node[][] nodeList;
	int width;
	int height;

	public AStar(EntityManager manager) {
		initGraph(manager);

	}

	public void initGraph(EntityManager manager) {
		width = 16;
		height = 11;
		nodeList = new Node[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Entity en = manager.getEntityFromPosition(new Position(x * 50, y * 50));
				if (en instanceof Wall || en instanceof Brick || en instanceof Bomb) {
					nodeList[x][y] = new Node(x, y, true);
				} else
					nodeList[x][y] = new Node(x, y, false);
			}
		}
	}

	public void printMap(List<Node> path) {

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (!nodeList[i][j].isBuffable()) {
					System.out.print(" blank");
				} else if (path.contains(new Node(i, j, false))) {
					System.out.print(" go");
				} else {
					System.out.print(" buff ");
				}
			}
			System.out.print("\n");
		}

	}

	public final List<Node> findPath(int startX, int startY, int goalX, int goalY) {

		// If our start position is the same as our goal position ...
		if (startX == goalX && startY == goalY) {
			// Return an empty path, because we don't need to move at all.
			return new LinkedList<Node>();
		}

		// The set of nodes already visited.
		List<Node> openList = new LinkedList<Node>();
		// The set of currently discovered nodes still to be visited.
		List<Node> closedList = new LinkedList<Node>();

		// Add starting node to open list.
		openList.add(nodeList[startX][startY]);

		// This loop will be broken as soon as the current node position is
		// equal to the goal position.
		while (true) {
			// Gets node with the lowest F score from open list.
			Node current = lowestFInList(openList);
			// Remove current node from open list.
			openList.remove(current);
			// Add current node to closed list.
			closedList.add(current);

			// If the current node position is equal to the goal position ...
			if ((current.getPosX() == goalX) && (current.getPosY() == goalY)) {
				// Return a LinkedList containing all of the visited nodes.
				return calcPath(nodeList[startX][startY], current);
			}

			List<Node> adjacentNodes = getAdjacent(current, closedList);
			for (Node adjacent : adjacentNodes) {
				// If node is not in the open list ...
				if (!openList.contains(adjacent)) {
					// Set current node as parent for this node.
					adjacent.setParent(current);
					// Set H costs of this node (estimated costs to goal).
					adjacent.setH(nodeList[goalX][goalY].getH());
					// Set G costs of this node (costs from start to this node).
					adjacent.setG(current.getG());
					// Add node to openList.
					openList.add(adjacent);
				}
				// Else if the node is in the open list and the G score from
				// current node is cheaper than previous costs ...
				else if (adjacent.getG() > adjacent.calG(current)) {
					// Set current node as parent for this node.
					adjacent.setParent(current);
					// Set G costs of this node (costs from start to this node).
					adjacent.setG(current.getG());
				}
			}

			// If no path exists ...
			if (openList.isEmpty()) {
				// Return an empty list.
				return new LinkedList<Node>();
			}
			// But if it does, continue the loop.
		}
	}

	/**
	 * @param start
	 *            The first node on the path.
	 * @param goal
	 *            The last node on the path.
	 * @return a list containing all of the visited nodes, from the goal to the
	 *         start.
	 */
	private List<Node> calcPath(Node start, Node goal) {
		LinkedList<Node> path = new LinkedList<Node>();

		Node node = goal;
		boolean done = false;
		while (!done) {
			path.addFirst(node);
			node = node.getParent();
			if (node.equals(start)) {
				done = true;
			}
		}
		return path;
	}

	/**
	 * @param list
	 *            The list to be checked.
	 * @return The node with the lowest F score in the list.
	 */
	private Node lowestFInList(List<Node> list) {
		Node cheapest = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF() < cheapest.getF()) {
				cheapest = list.get(i);
			}
		}
		return cheapest;
	}

	/**
	 * If the X and Y parameters are within the map boundaries, return the node in
	 * the specific coordinates, null otherwise.
	 * 
	 * @param x
	 *            Desired node's X coordinate.
	 * @param y
	 *            Desired node's Y coordinate.
	 * @return The desired node if the parameters are valid, null otherwise.
	 */
	public Node getNode(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return nodeList[x][y];
		} else {
			return null;
		}
	}

	/**
	 * @param node
	 *            The node to be checked for adjacent nodes.
	 * @param closedList
	 *            A list containing all of the nodes already visited.
	 * @return A LinkedList with nodes adjacent to the given node if those exist,
	 *         are walkable and are not already in the closed list.
	 */
	private List<Node> getAdjacent(Node node, List<Node> closedList) {
		List<Node> adjacentNodes = new LinkedList<Node>();
		int x = node.getPosX();
		int y = node.getPosY();

		Node adjacent;

		// Check left node
		if (x > 0) {
			adjacent = getNode(x - 1, y);
			if (adjacent != null && adjacent.isBuffable() && !closedList.contains(adjacent)) {
				adjacentNodes.add(adjacent);
			}
		}

		// Check right node
		if (x < width) {
			adjacent = getNode(x + 1, y);
			if (adjacent != null && adjacent.isBuffable() && !closedList.contains(adjacent)) {
				adjacentNodes.add(adjacent);
			}
		}

		// Check top node
		if (y > 0) {
			adjacent = this.getNode(x, y - 1);
			if (adjacent != null && adjacent.isBuffable() && !closedList.contains(adjacent)) {
				adjacentNodes.add(adjacent);
			}
		}

		// Check bottom node
		if (y < height) {
			adjacent = this.getNode(x, y + 1);
			if (adjacent != null && adjacent.isBuffable() && !closedList.contains(adjacent)) {
				adjacentNodes.add(adjacent);
			}
		}
		return adjacentNodes;
	}

	// private List<Node> path(Map<String, Node> path, Node destination) {
	// assert path != null;
	// assert destination != null;
	//
	// final List<Node> pathList = new ArrayList<Node>();
	// pathList.add(destination);
	// while (path.containsKey(destination)) {
	// destination = path.get(destination);
	// pathList.add(destination);
	// }
	// Collections.reverse(pathList);
	// return pathList;
	// }

	// public List<Node> bestNode() {// lay ra node no duoc nhieu Brick nhat va gan
	// nhat
	// List<Node> list = new LinkedList<>();
	// for (int i = 0; i < nodeList.length; i++) {
	// for (int j = 0; j < nodeList[0].length; j++) {
	// Node node = new Node(i, j);
	// list.add(node);
	// }
	//
	// }
	// Collections.sort(list);
	// for (int i = 0; i < list.size(); i++) {
	// System.out.println(list.get(i));
	// }
	// return list;
	// }
	//
	// public Node dropableBomb() {// lay ra Node se den dat bomb de no nhieu Brick
	// nhat
	// List<Node> listNode = bestNode();
	// Node result = listNode.get(0);
	// for (int i = 1; i < listNode.size(); i++) {
	// if (result.getValue() < listNode.get(i).getValue() && result.getF() >
	// listNode.get(i).getF()) {
	// result = listNode.get(i);
	// }
	// }
	// return result;
	// }

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getGoal() {
		return goal;
	}

	public void setGoal(Node goal) {
		this.goal = goal;
	}

	public Node getCurrent() {
		return current;
	}

	public void setCurrent(Node current) {
		this.current = current;
	}

	public static void main(String[] args) {
		int[][] map = new int[11][16];
		map[0][0] = 1;
		map[0][1] = 1;
		map[0][2] = 1;
		map[0][3] = 1;
		map[0][4] = 1;
		map[0][5] = 1;
		map[0][6] = 1;
		map[0][7] = 1;
		map[0][8] = 1;
		map[0][9] = 1;
		map[0][10] = 1;
		map[0][11] = 1;
		map[0][12] = 1;
		map[0][13] = 1;
		map[0][14] = 1;
		map[0][15] = 1;

		map[10][0] = 1;
		map[10][1] = 1;
		map[10][2] = 1;
		map[10][3] = 1;
		map[10][4] = 1;
		map[10][5] = 1;
		map[10][6] = 1;
		map[10][7] = 1;
		map[10][8] = 1;
		map[10][9] = 1;
		map[10][10] = 1;
		map[10][11] = 1;
		map[10][12] = 1;
		map[10][13] = 1;
		map[10][14] = 1;
		map[10][15] = 1;

		map[0][0] = 1;
		map[1][0] = 1;
		map[2][0] = 1;
		map[3][0] = 1;
		map[4][0] = 1;
		map[5][0] = 1;
		map[6][0] = 1;
		map[7][0] = 1;
		map[8][0] = 1;
		map[9][0] = 1;
		map[10][0] = 1;
		map[0][15] = 1;
		map[1][15] = 1;
		map[2][15] = 1;
		map[3][15] = 1;
		map[4][15] = 1;
		map[5][15] = 1;
		map[6][15] = 1;
		map[7][15] = 1;
		map[8][15] = 1;
		map[9][15] = 1;
		map[10][15] = 1;
		int[][] arr = new int[11][16];
		EntityManager manager = new EntityManager();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				Entity en = manager.getEntityFromPosition(new Position(j * 50, i * 50));
				if (en instanceof Wall || en instanceof Brick || en instanceof Bomb) {
					arr[i][j] = 1;
				} else {
					arr[i][j] = 0;
				}
			}

		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]);
				System.out.print("\t");

			}
			System.out.println();
		}

//		AStar astar = new AStar(manager);
//		astar.printMap(astar.findPath(1, 1, 2, 1));
//		System.out.println(astar.findPath(1, 1, 2, 1));
	}

}
