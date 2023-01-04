import java.util.*;
import java.io.*;

public class AOC2022D20P1 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<Node> nodes = new ArrayList();
		while (in.hasNextLine()) {
			nodes.add(new Node(tl(in.nextLine())));
		}
		
		int len = nodes.size();
		for (int i = 0; i < len; i++) {
			nodes.get(i).r = nodes.get((i+1) % len);
			nodes.get(i).l = nodes.get((i-1+len) % len);
		}
		
		//printList(nodes.get(0), len);
		
		for (int i = 0; i < len; i++) {
			Node c = nodes.get(i), t = nodes.get(i);
			
			if (c.val % (len - 1) == 0) continue;
			
			if (c.val > 0) {
				long amt = c.val % (len - 1);
				
				while (amt-- > 0) {
					t = t.r;
				}
				
				Node cl = c.l, cr = c.r, tr = t.r;
				cr.l = cl;
				cl.r = cr;
				tr.l = c;
				t.r = c;
				c.r = tr;
				c.l = t;
			}
			else if (c.val < 0) {
				long amt = c.val % (len - 1);
				
				while (amt++ < 0) {
					t = t.l;
				}
				
				Node cl = c.l, cr = c.r, tl = t.l;
				cr.l = cl;
				cl.r = cr;
				tl.r = c;
				t.l = c;
				c.r = t;
				c.l = tl;
			}
			
			//printList(nodes.get(0), len);
		}
		
		Node n = nodes.get(0);
		for (Node x : nodes) {
			if (x.val == 0) {
				n = x;
				break;
			}
		}
		
		long sum = 0;
		
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j < 1000; j++) {
				n = n.r;
			}
			sum += n.val;
		}
		
		out.println(sum);
		out.close();
	}
	
	static void printList(Node n, int len) {
		for (int i = 0; i < len; i++) {
			System.out.printf(n + " ");
			n = n.r;
		}
		System.out.println();
		for (int i = 0; i < len; i++) {
			System.out.print(n.val + " ");
			n = n.r;
		}
		System.out.println();
	}
	
	static long tl(String s) {
		return Long.parseLong(s);
	}
	
	static class Node {
		Node l, r;
		long val;
		
		public Node(long v) {
			val = v;
		}
		
		public String toString() {
			return "(" + l.val + " " + val + " " + r.val + ")";
		}
	}
}