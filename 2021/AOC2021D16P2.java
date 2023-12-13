import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class AOC2021D16P2 {
	static String filename = "aoc";
	static String bits = "";
	static int pointer = 0;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String input = in.nextLine();
		
		for (int i = 0; i < input.length(); i++) {
			bits += Integer.toBinaryString(16 + Integer.parseInt(input.substring(i,  i + 1), 16)).substring(1);
		}
		
		System.out.println(bits);
		System.out.println(parsePacket());
		
		out.close();

	}
	
	static BigInteger parseBits(int n) {
		//System.out.println(pointer + " " + (pointer + n));
		pointer += n;
		//System.out.println(new BigInteger(bits.substring(pointer - n, pointer), 2));
		return new BigInteger(bits.substring(pointer - n, pointer), 2);
	}
	
	static BigInteger parsePacket() {
		long ver = parseBits(3).longValue();
		long type = parseBits(3).longValue();
		
		if (type == 4) {
			return parseLiteral();
		}
		
		long lenID = parseBits(1).longValue();
		long val = parseBits(lenID == 0 ? 15 : 11).longValue();
		ArrayList<BigInteger> packets = new ArrayList();
		
		if (lenID == 0) {
			int cur = pointer;
			
			while (pointer - cur < val) {
				packets.add(parsePacket());
			}
		}
		else {
			for (int i = 0; i < val; i++) {
				packets.add(parsePacket());
			}
		}
		
		//System.out.println(packets.toString());
		
		if (type == 0) {
			BigInteger sum = BigInteger.ZERO;
			for (BigInteger i : packets) sum = sum.add(i);
			return sum;
		}
		else if (type == 1) {
			BigInteger prod = BigInteger.ONE;
			for (BigInteger i : packets) prod = prod.multiply(i);
			return prod;
		}
		else if (type == 2) {
			BigInteger min = packets.get(0);
			for (BigInteger i : packets) min = min.min(i);
			return min;
		}
		else if (type == 3) {
			BigInteger max = packets.get(0);
			for (BigInteger i : packets) max = max.max(i);
			return max;
		}
		else if (type == 5) {
			return (packets.get(0).compareTo(packets.get(1)) > 0) ? BigInteger.ONE : BigInteger.ZERO;
		}
		else if (type == 6) {
			return (packets.get(0).compareTo(packets.get(1)) < 0) ? BigInteger.ONE : BigInteger.ZERO;
		}
		else {
			return (packets.get(0).compareTo(packets.get(1)) == 0) ? BigInteger.ONE : BigInteger.ZERO;
		}
	}
	
	static BigInteger parseLiteral() {
		pointer += 5;
		String num = bits.substring(pointer - 4, pointer);
		
		while (bits.charAt(pointer - 5) == '1') {
			num += bits.substring(pointer - 4, pointer);
			pointer += 5;
		}
		
		return new BigInteger(num, 2);
	}
}