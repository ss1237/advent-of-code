import java.util.*;
import java.io.*;

public class AOC2021D01P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		int i1 = in.nextInt(), i2 = in.nextInt(), i3 = in.nextInt();
		int ct = 0;
		
		while (in.hasNextInt()) {
			int next = in.nextInt();
			if (next > i1) ct++;
			i1 = i2;
			i2 = i3;
			i3 = next;
		}

		System.out.println(ct);
	}
}