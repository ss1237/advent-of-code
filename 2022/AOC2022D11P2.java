import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class AOC2022D11P2 {
	static String filename = "aoc";
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		int size = 0;
		ArrayList<ArrayList<BigInteger>> monkeys = new ArrayList();
		ArrayList<String[]> ops = new ArrayList();
		ArrayList<BigInteger> div = new ArrayList();
		ArrayList<int[]> to = new ArrayList();
		
		while (in.hasNextLine()) {
			in.nextLine();
			String[] line = in.nextLine().split("[,: ] *");
			monkeys.add(new ArrayList<BigInteger>());
			for (int i = 3; i < line.length; i++) {
				monkeys.get(size).add(new BigInteger(line[i]));
			}
			
			line = in.nextLine().split("[,: ] *");
			String[] op = new String[] {line[4], line[5], line[6]};
			ops.add(op);
			
			line = in.nextLine().split("[,: ] *");
			div.add(new BigInteger(line[4]));
			
			to.add(new int[2]);
			line = in.nextLine().split("[,: ] *");
			to.get(size)[0] = Integer.parseInt(line[6]);
			line = in.nextLine().split("[,: ] *");
			to.get(size)[1] = Integer.parseInt(line[6]);
			
			
			if (in.hasNextLine()) in.nextLine();
			size++;
		}
		
		BigInteger finMod = BigInteger.ONE;
		for (BigInteger x : div) {
			finMod = finMod.multiply(x);
		}
		
		long[] amts = new long[size];
		
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < size; j++) {
				amts[j] -= monkeys.get(j).size();
				
				//out.println(monkeys.get(j));
				
				for (BigInteger old : monkeys.get(j)) {
					BigInteger oldN = newNum(ops.get(j), old).mod(finMod);
					if (oldN.mod(div.get(j)).compareTo(BigInteger.ZERO) == 0) {
						monkeys.get(to.get(j)[0]).add(oldN);
					}
					else {
						monkeys.get(to.get(j)[1]).add(oldN);
					}
				}
				monkeys.set(j, new ArrayList<BigInteger>());
			}
			if (i % 1000 == 0) {
				System.out.println(Arrays.toString(amts));
			}
		}
		
		Arrays.sort(amts);
		out.println(amts[0] * amts[1]);
		
		out.close();
	}
	
	static BigInteger newNum(String[] op, BigInteger old) {
		BigInteger x = op[0].equals("old") ? old : new BigInteger(op[0]);
		BigInteger y = op[2].equals("old") ? old : new BigInteger(op[2]);
		return op[1].equals("+") ? x.add(y) : x.multiply(y);
	}
	
}