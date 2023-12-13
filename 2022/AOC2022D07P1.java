import java.util.*;
import java.io.*;

public class AOC2022D07P1 {
	static String filename = "aoc";
	static HashMap<String, Long> sizes = new HashMap();
	static HashMap<String, String> parent = new HashMap();
	static HashMap<String, HashSet<String>> child = new HashMap();
	static HashSet<String> leaf = new HashSet();
	static long total = 0;
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		String curDir = "";
		child.put("/", new HashSet<String>());
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			
			if (line[0].equals("$")) {
				if (line[1].equals("cd")) {
					if (line[2].equals("..")) {
						curDir = parent.get(curDir);
					}
					else if (line[2].equals("/")) {
						curDir = "/";
					}
					else {
						String newDir = curDir + "/" + line[2];
						if (child.get(newDir) == null) {
							child.put(newDir, new HashSet<String>());
						}
						curDir = newDir;
					}
				}
			}
			else {
				String path = curDir + "/" + line[1];
				if (line[0].equals("dir")) {
					parent.put(path, curDir);
					child.get(curDir).add(path);
				}
				else {
					long size = Long.parseLong(line[0]);
					sizes.put(path, size);
					parent.put(path, curDir);
					child.get(curDir).add(path);
					leaf.add(path);
				}
			}
		}
		
		System.out.println(sizes);
		System.out.println(parent);
		System.out.println(child);
		System.out.println(leaf);
		
		dfs("/");
		
		System.out.println(sizes);
		System.out.println(sizes.get("/"));
		
		out.println(total);
		
		out.close();
	}
	
	static void dfs(String s) {
		for (String to : child.get(s)) {
			if (!leaf.contains(to)) {
				dfs(to);
			}
			sizes.put(s, sizes.getOrDefault(s, 0L) + sizes.get(to));
		}
		if (sizes.get(s) <= 100000) {
			total += sizes.get(s);
		}
	}
}