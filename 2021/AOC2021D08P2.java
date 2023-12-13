import java.util.*;
import java.io.*;

public class AOC2021D08P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		
		HashMap<Integer, Integer> binToOutput = new HashMap();
		binToOutput.put(119, 0);
		binToOutput.put(36, 1);
		binToOutput.put(93, 2);
		binToOutput.put(109, 3);
		binToOutput.put(46, 4);
		binToOutput.put(107, 5);
		binToOutput.put(123, 6);
		binToOutput.put(37, 7);
		binToOutput.put(127, 8);
		binToOutput.put(111, 9);
		
		int sum = 0;
		
		while (in.hasNextLine()) {
			
			String[] output = in.nextLine().split(" ");
			int[] freq = new int[7];
			for (int i = 0; i < 10; i++) {
				for (char c : output[i].toString().toCharArray()) {
					freq[c - 'a']++;
				}
			}
			
			System.out.println(Arrays.toString(freq));
			
			int[] signalToOutput = new int[7];
			Arrays.fill(signalToOutput, -1);
			
			for (int i = 0; i < 7; i++) {
				if (freq[i] == 6) {
					signalToOutput[i] = 1;
				}
				else if (freq[i] == 4) {
					signalToOutput[i] = 4;
				}
				else if (freq[i] == 9) {
					signalToOutput[i] = 5;
				}
			}
			
			System.out.println(Arrays.toString(signalToOutput));
			
			for (int i = 0; i < 10; i++) {
				if (output[i].length() == 2) {
					for (char c : output[i].toString().toCharArray()) {
						if (signalToOutput[c - 'a'] == -1) {
							signalToOutput[c - 'a'] = 2;
						}
					}
				}
			}
			
			System.out.println(Arrays.toString(signalToOutput));
			
			for (int i = 0; i < 10; i++) {
				if (output[i].length() == 3) {
					for (char c : output[i].toString().toCharArray()) {
						if (signalToOutput[c - 'a'] == -1) {
							signalToOutput[c - 'a'] = 0;
						}
					}
				}
			}
			
			System.out.println(Arrays.toString(signalToOutput));
			
			for (int i = 0; i < 10; i++) {
				if (output[i].length() == 4) {
					for (char c : output[i].toString().toCharArray()) {
						if (signalToOutput[c - 'a'] == -1) {
							signalToOutput[c - 'a'] = 3;
						}
					}
				}
			}
			
			System.out.println(Arrays.toString(signalToOutput));
			
			for (int i = 0; i < 10; i++) {
				if (output[i].length() == 7) {
					for (char c : output[i].toString().toCharArray()) {
						if (signalToOutput[c - 'a'] == -1) {
							signalToOutput[c - 'a'] = 6;
						}
					}
				}
			}
			
			System.out.println(Arrays.toString(signalToOutput));
			
			for (int i = 11; i <= 14; i++) {
				char[] cur = output[i].toCharArray();
				int num = 0;
				for (char c : cur) {
					num ^= 1 << signalToOutput[c - 'a'];
				}
				sum += binToOutput.get(num) * Math.pow(10, 14 - i);
			}
			
		}
		
		out.println(sum);
		
		out.close();

	}
}