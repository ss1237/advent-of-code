import java.util.*;
import java.io.*;

public class AOC2021D21P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		int p1 = Integer.parseInt(in.nextLine().split(": ")[1]);
		int p2 = Integer.parseInt(in.nextLine().split(": ")[1]);
		
		long t1 = 0;
		long t2 = 0;
		
		int die = 3;
		
		while (true) {
			
			if (die % 6 == 3) {
				p1 = (p1 + 3 * die - 3 - 1) % 10 + 1;
				t1 += p1;
				
				if (t1 >= 1000) {
					System.out.println(t2 * die);
					break;
				}
			}
			else {
				p2 = (p2 + 3 * die - 3 - 1) % 10 + 1;
				t2 += p2;
				
				if (t2 >= 1000) {
					System.out.println(t1 * die);
					break;
				}
			}
			
			die += 3;
		}
	}
}