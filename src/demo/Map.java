package demo;

import model.Direction;

public class Map {
	private static int PLAY1 = 6;
	private static int PLAY2 = 5;
	private static int WALL = 1;
	private static int BOMB = 2;
	private static int BRICK = 3;
	private static int FIRE = 4;
	int[][] map;
	private int Blank_Value = 0;
	private Parent stateParent;

	private Direction direction;
	private int[]Blank_Pos;
	

	public Map() {
		map = new int[8][8];

		map[0][0] = PLAY1;
		map[3][4] = WALL;
		map[5][5] = BRICK;
		map[6][2] = FIRE;
		map[7][7] = PLAY2;
		map[6][5] = BRICK;
		map[6][7] = BRICK;
		map[5][6] = BRICK;
		map[2][3] = BRICK;
		map[3][3] = BRICK;
		map[0][2] = BRICK;
		map[2][1] = BRICK;
		map[1][2] = BRICK;
		for (int i = 0; i < Blank_Pos.length; i++) {
			
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				s += map[i][j] + "\t";

			}
			s += "\n";

		}
		return s;
	}
	/*
	 * Lưu mảng vị trí đặt bomb nổ nhiều tường  nhất
	 * Dung thuat toan tim dường đi gắn nhất từ agent đến vị trí đích tính khoảng cách
	 * đánh giá lựa chọn 1 vị trí tốt để đến đựa vào nổ nhiều tường (number tường)+khoảng cách 
	 * 
	 */

	public static void main(String[] args) {
		Map map = new Map();
		System.out.println(map.toString());
	}
}
