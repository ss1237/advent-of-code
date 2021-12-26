import java.util.*;
import java.io.*;

public class AOC2021D16P1 {
	static String filename = "aoc";
	static String bits = "";
	static int verSum = 0, pointer = 0;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String input = in.nextLine();
		
		for (int i = 0; i < input.length(); i++) {
			bits += Integer.toBinaryString(16 + Integer.parseInt(input.substring(i,  i + 1), 16)).substring(1);
		}
		
		out.println(bits);
		
		parsePacket();
		
		out.println(verSum);
		
		out.close();

	}
	
	static int parseBits(int n) {
		System.out.println(pointer + " " + (pointer + n));
		pointer += n;
		System.out.println(Integer.parseInt(bits.substring(pointer - n, pointer), 2));
		return Integer.parseInt(bits.substring(pointer - n, pointer), 2);
	}
	
	static void parsePacket() {
		int ver = parseBits(3);
		int type = parseBits(3);
		
		System.out.println("ver: " + ver);
		verSum += ver;
		
		if (type == 4) {
			parseLiteral();
			return;
		}
		
		int lenID = parseBits(1);
		int val = parseBits(lenID == 0 ? 15 : 11);
		
		if (lenID == 0) {
			int cur = pointer;
			
			while (pointer - cur < val) {
				parsePacket();
			}
		}
		else {
			for (int i = 0; i < val; i++) {
				parsePacket();
			}
		}
		
		
	}
	
	static void parseLiteral() {
		int group = parseBits(5);
		
		while ((group & (1 << 4)) != 0) {
			group = parseBits(5);
		}
	}
}