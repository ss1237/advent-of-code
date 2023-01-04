import java.util.*;
import java.io.*;

public class AOC2022D22P2 {
	static String filename = "aoc";
	static int[] d = new int[] {0, -1, 0, 1}; //ENWS
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<String> tempGrid = new ArrayList();
		
		while (in.hasNextLine()) {
			String l = in.nextLine();
			tempGrid.add(l);
		}
		
		char[][] grid = new char[tempGrid.size() - 2][tempGrid.get(0).length()];
		String[] moves = tempGrid.get(tempGrid.size() - 1).split("(?<=[RL])(?=\\d)|(?<=\\d)(?=[RL])");
		int r = 0, c = 0, dir = 0, side = tempGrid.get(0).length() / 3;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < tempGrid.get(i).length(); j++) {
				if (i == 0 && c == 0 && tempGrid.get(i).charAt(j) != ' ') c = j;
				grid[i][j] = tempGrid.get(i).charAt(j);
			}
			for (int j = tempGrid.get(i).length(); j < 3 * side; j++) {
				grid[i][j] = ' ';
			}
		}
		
		int[][][][] map = new int[grid.length][grid[0].length][4][3];
		
		for (int i = 0; i < side; i++) {
			map[2 * side][i][1] = new int[] {i + side, side, 0};
			map[4 * side - 1][i][3] = new int[] {0, i + 2 * side, 3};
		}
		
		for (int i = side; i < 2 * side; i++) {
			map[0][i][1] = new int[] {i + 2 * side, 0, 0};
			map[3 * side - 1][i][3] = new int[] {i + 2 * side, side - 1, 2};
		}
		
		for (int i = 2 * side; i < 3 * side; i++) {
			map[0][i][1] = new int[] {4 * side - 1, i - 2 * side, 1};
			map[side - 1][i][3] = new int[] {i - side, 2 * side - 1, 2};
		}
		
		for (int i = 0; i < side; i++) {
			map[i][side][2] = new int[] {3 * side - 1 - i, 0, 0};
			map[i][3 * side - 1][0] = new int[] {3 * side - 1 - i, 2 * side - 1, 2};
		}
		
		for (int i = side; i < 2 * side; i++) {
			map[i][side][2] = new int[] {2 * side, i - side, 3};
			map[i][2 * side - 1][0] = new int[] {side - 1, i + side, 1};
		}
		
		for (int i = 2 * side; i < 3 * side; i++) {
			map[i][0][2] = new int[] {3 * side - 1 - i, side, 0};
			map[i][2 * side - 1][0] = new int[] {3 * side - 1 - i, 3 * side - 1, 2};
		}
		
		for (int i = 3 * side; i < 4 * side; i++) {
			map[i][0][2] = new int[] {0, i - 2 * side, 3};
			map[i][side - 1][0] = new int[] {3 * side - 1, i - 2 * side, 1};
		}
		
		for (String s : moves) {
			if (s.equals("R")) {
				dir = (dir + 3) % 4;
				//System.out.println(r + " " + c + " " + dir);
			}
			else if (s.equals("L")) {
				dir = (dir + 1) % 4;
				//System.out.println(r + " " + c + " " + dir);
			}
			else {
				
				for (int i = 0; i < Integer.parseInt(s); i++) {
					int nr = (r + d[dir] + grid.length) % grid.length;
					int nc = (c + d[3 - dir] + grid[0].length) % grid[0].length;
					if (grid[nr][nc] == ' ') {
						nr = map[r][c][dir][0];
						nc = map[r][c][dir][1];
						if (grid[nr][nc] != '#') dir = map[r][c][dir][2];
					}
					if (grid[nr][nc] == '#') break;
					r = nr;
					c = nc;
					//System.out.println(r + " " + c + " " + dir);
				}
			}
		}
		
		//System.out.println(Arrays.toString(moves));
		out.println(1000*(r+1) + 4*(c+1) + (4-dir)%4);
		
		out.close();
	}
	
	
}