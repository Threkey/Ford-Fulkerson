package PackageNo2;

import java.io.*;
import java.util.*;

public class Ford_Fulkerson {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int MAX_SIZE = 52;
	static int N, maxFlow, S, T = 25, capacity[][], flow[][];
	static boolean check[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		capacity = new int[MAX_SIZE][MAX_SIZE];
		flow = new int[MAX_SIZE][MAX_SIZE];
		check = new boolean[MAX_SIZE];
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = charToInt(st.nextToken().charAt(0));
			int end = charToInt(st.nextToken().charAt(0));
			int weight = Integer.parseInt(st.nextToken());
			capacity[start][end] += weight; 
			capacity[end][start] += weight;
		}
		
		while(true) {
			Arrays.fill(check, false);
			int flowAmount = dfs(S, Integer.MAX_VALUE);
			if(flowAmount == 0) break;
			maxFlow += flowAmount;
		}
		
		System.out.println(maxFlow);
	}
	
	public static int dfs(int from, int amount) {
		if(from == T) return amount;

		if(check[from]) return 0;
		check[from] = true;
		
		for(int to = 0; to < MAX_SIZE; to++) {
			if(capacity[from][to] > flow[from][to]) {
				int flowAmount = dfs(to, Math.min(amount, capacity[from][to]-flow[from][to]));
				if(flowAmount > 0) {
					flow[from][to] += flowAmount;
					flow[to][from] -= flowAmount;
					return flowAmount;
				}
			}
		}
		
		return 0;
	}
	
	public static int charToInt(char c) {
		if('a' <= c && c <= 'z') c -= 6;
		return c - 65;
	}
}
