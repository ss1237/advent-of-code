import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class AOC2021D19P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		var pool = new ArrayList<Scan>();
		
		while (in.hasNextLine()) {
			String name = in.nextLine();
			ArrayList<Coord> cur = new ArrayList();
			
			while (true) {
				String s = in.nextLine();
				if (s.length() == 0) break;
				cur.add(new Coord(Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray()));
			}
			
			pool.add(new Scan(name, cur));
		}
		
		Scan base = pool.remove(0);
		
		while (pool.size() > 0) {
			//System.out.println("pool size:" + pool.size());
			outer: for (Scan sc : pool) {
				for (var t : sc.getAllRotations()) {
					Coord trans = base.findTranslation(t);
					
					if (trans != null) {
						base.add(t, trans);
						pool.remove(sc);
						break outer;
					}
				}
			}
		}
		
		System.out.println(base.coords.size());
		
	}
	
	static class Scan {
		String name;
		List<Coord> coords;
		Map<Coord, Integer> map = new HashMap();
		List<Scan> rot;
		
		public Scan(String n, ArrayList<Coord> c) {
			name = n;
			coords = c;
		}
		
		public Coord findTranslation(Scan peer) {
			map.clear();
			
			for (Coord c : coords) {
				for (Coord d : peer.coords) {
					Coord diff = c.subtract(d);
					map.put(diff, map.getOrDefault(diff, 0) + 1);
				}
			}
			
			
			var r = map.entrySet().stream().filter(x -> x.getValue() >= 12).collect(Collectors.toList());
			
			if (r.size() == 0) return null;
			return r.get(0).getKey();
		}
		
		public List<Scan> getAllRotations() {
			if (rot != null) return rot;
			
			ArrayList<ArrayList<Coord>> ret = new ArrayList(24);
			for (int i = 0; i < 24; i++) ret.add(new ArrayList(coords.size()));
			
			for (Coord c : coords) {
				var x = sequence(c);
				for (int i = 0; i < 24; i++) ret.get(i).add(x.get(i));
			}
			return ret.stream().map(s -> new Scan(name + "rot", s)).collect(Collectors.toList());
		}
		
		public void add(Scan t) {
			for (Coord c : t.coords) if (!coords.contains(c)) coords.add(c);
			Collections.sort(coords);
		}
		
		public void add(Scan t, Coord trans) {
			for (Coord c : t.coords) {
				c = c.sum(trans);
				if (!coords.contains(c)) coords.add(c);
			}
			Collections.sort(coords);
		}
		
		public String toString() {
			String ret = name + "\n";
			for (Coord c : coords) {
				ret += c + "\n";
			}
			return ret + "\n";
		}
	}
	
	static ArrayList<Coord> sequence(Coord v) {
		ArrayList<Coord> r = new ArrayList();
		
		for (int c = 0; c < 2; c++) {
			for (int s = 0; s < 3; s++) {
				v = roll(v);
				r.add(v);
				for (int i = 0; i < 3; i++) {
					v = turn(v);
					r.add(v);
				}
			}
			v = roll(turn(roll(v)));
		}
		
		return r;
	}
	
	static Coord roll(Coord c) {return new Coord(c.x, c.z, -c.y);}
	
	static Coord turn(Coord c) {return new Coord(-c.y, c.x, c.z);}
	
	static class Coord implements Comparable<Coord> {

		int x, y, z;
		
		public Coord(int a, int b, int c) {
			x = a;
			y = b;
			z = c;
		}
		
		public Coord(int[] nums) {
			x = nums[0];
			y = nums[1];
			z = nums[2];
		}
		
		public int[] toArray() {return new int[] {x, y, z};}
		
		@Override
		public int compareTo(Coord o) {return Arrays.compare(toArray(), o.toArray());}
		
		public boolean equals(Object o) {return compareTo((Coord) o) == 0;}
		
		Coord subtract(Coord o) {return new Coord(x - o.x, y - o.y, z - o.z);}
		
		Coord sum(Coord o) {return new Coord(x + o.x, y + o.y, z + o.z);}
		
		public String toString() {return "(" + x + ", " + y + ", " + z + ")";}
		
		public int hashCode() {return x + 1000*y + 1000000*z;}
	}
}