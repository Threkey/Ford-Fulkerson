package PackageNo2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class Ford_Fulkerson {

		static Map<Integer, Map<Integer, Integer>> residual;
		static Map<Integer, Integer> prev;
		static final int INF = Integer.MAX_VALUE;
		public static boolean bfs(int src, int dst) {
			Map<Integer, Boolean> visited = new HashMap<>();
			Queue<Integer> q = new LinkedList<>();
			visited.put(src, true);
			q.add(src);
			while(!q.isEmpty()) {
				int u = q.poll();
				Iterator<Integer> iter = residual.get(u).keySet().iterator();
				while(iter.hasNext()) {
					int v = iter.next();
					if(!visited.getOrDefault(v, false) && residual.get(u).get(v) > 0) {
						prev.put(v, u);
						visited.put(v, true);
						q.add(v);
					}
				}
			}
			
			return visited.getOrDefault(dst, false);
		}
		
		public static int FordFulkerson(int src, int dst) {
			int maxFlow = 0;
			
			while(bfs(src, dst)) {
				int pathFlow = INF;
	            
	      for(int v = dst; v != src; v = prev.get(v)) {
					int u = prev.get(v);
					pathFlow = Math.min(pathFlow, residual.get(u).get(v));
				}
				
				for(int v = dst; v != src; v = prev.get(v)) {
					int u = prev.get(v);
					residual.get(u).put(v, residual.get(u).get(v) - pathFlow); 
					residual.get(v).put(u, residual.get(v).get(u) + pathFlow); 
				}
				
				maxFlow += pathFlow;
			}
			
			return maxFlow;
		}

	
	

	public static void main(String[] args) {
		System.out.println(FordFulkerson(1, 3));
	}

}
