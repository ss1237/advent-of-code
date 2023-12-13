import java.util.*;
import java.io.*;

public class AOC2021D04P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		String[] snums = in.nextLine().split(",");
		
		
		ArrayList<Integer> nums = new ArrayList();
		for (int i = 0; i < snums.length; i++) {
			nums.add(Integer.parseInt(snums[i]));
		}
		
		int minTurn = nums.size();
		int val = 0;
		
		while (in.hasNextLine()) {
			in.nextLine();
			
			int[][] board = new int[5][5];
			int[][] rows = new int[5][2];
			int[][] cols = new int[5][2];
			
			for (int i = 0; i < 5; i++) {
				rows[i][0] = -1;
				cols[i][0] = -1;
			}
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					board[i][j] = in.nextInt();
					
					if (nums.contains(board[i][j])) {
						rows[i][0] = Math.max(rows[i][0], nums.indexOf(board[i][j]));
						rows[i][1]++;
						cols[j][0] = Math.max(cols[j][0], nums.indexOf(board[i][j]));
						cols[j][1]++;
					}
				}
			}
			
			int newMinTurn = nums.size();
			
			for (int i = 0; i < 5; i++) {
				if (rows[i][1] == 5 && rows[i][0] < minTurn) {
					newMinTurn = Math.min(newMinTurn, rows[i][0]);
				}
				if (cols[i][1] == 5 && cols[i][0] < minTurn) {
					newMinTurn = Math.min(newMinTurn, cols[i][0]);
				}
			}
			
			//out.println(Arrays.deepToString(rows));
			//out.println(Arrays.deepToString(cols));
			
			if (newMinTurn != nums.size()) {
				int unmarked = 0;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (nums.indexOf(board[i][j]) == -1 || nums.indexOf(board[i][j]) > newMinTurn) {
							unmarked += board[i][j];
						}
					}
				}
				
				val = unmarked * nums.get(newMinTurn);
				
				minTurn = newMinTurn;
			}
			
			
			
			if (in.hasNextLine()) in.nextLine();
		}
		
		out.println(val);
		
		out.close();

	}
}