import java.util.*;
import java.io.*;

public class AOC2022D22P1 {
	static String filename = "aoc";
	static int[] d = new int[] {0, -1, 0, 1}; //ENWS
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		int len = 0;
		ArrayList<String> tempGrid = new ArrayList();
		
		while (in.hasNextLine()) {
			String l = in.nextLine();
			len = Math.max(len, l.length());
			tempGrid.add(l);
		}
		
		char[][] grid = new char[tempGrid.size() - 2][len];
		String[] moves = tempGrid.get(tempGrid.size() - 1).split("(?<=[RL])(?=\\d)|(?<=\\d)(?=[RL])");
		int r = 0, c = 0, dir = 0;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < tempGrid.get(i).length(); j++) {
				if (i == 0 && c == 0 && tempGrid.get(i).charAt(j) != ' ') c = j;
				grid[i][j] = tempGrid.get(i).charAt(j);
			}
			for (int j = tempGrid.get(i).length(); j < len; j++) {
				grid[i][j] = ' ';
			}
		}
		
		for (String s : moves) {
			if (s.equals("R")) {
				dir = (dir + 3) % 4;
			}
			else if (s.equals("L")) {
				dir = (dir + 1) % 4;
			}
			else {
				for (int i = 0; i < Integer.parseInt(s); i++) {
					int nr = (r + d[dir] + grid.length) % grid.length;
					int nc = (c + d[3 - dir] + grid[0].length) % grid[0].length;
					while (grid[nr][nc] == ' ') {
						nr = (nr + d[dir] + grid.length) % grid.length;
						nc = (nc + d[3 - dir] + grid[0].length) % grid[0].length;
					}
					if (grid[nr][nc] == '#') break;
					r = nr;
					c = nc;
				}
			}
			//System.out.println(r + " " + c + " " + dir);
		}
		
		//System.out.println(Arrays.toString(moves));
		out.println(1000*(r+1) + 4*(c+1) + (4-dir)%4);
		
		out.close();
	}
	
	
}