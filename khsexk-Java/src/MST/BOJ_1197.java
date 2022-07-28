package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1197 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] VE = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int V = VE[0];
		int E = VE[1];
		int[][] edges = new int[E][3];
		int[]parents = new int[V+1];
		
		for(int i=0 ; i<E ; i++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			edges[i][0] = input[0];
			edges[i][1] = input[1];
			edges[i][2] = input[2];
		}
		
		for(int i=0 ; i<V ; i++) {
			parents[i] = i;
		}
		
		int total = kruskal(edges, parents);
		
		System.out.println(total);
	}
	
	// 크루스칼 알고리즘 
	public static int kruskal(int[][] edges, int[] parents) {
		int total = 0;
		
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2];
				
			}
		});
		
		for (int i = 0; i < edges.length; i++) {
            if (find(parents, edges[i][0]) != find(parents, edges[i][1])) {
                total += edges[i][2];
                union(parents, edges[i][0], edges[i][1]);
            }
        }
		
		return total;
	}
	
	// 사이클을 방지하기 위한 메서드 
    public static int find(int[] parent, int i) {
        if (parent[i] == i) 
            return i;
        return find(parent, parent[i]);
    }
    
    // MST를 구성하는 간선들의 집합에 추가 
    public static void union(int[] parent, int a, int b) {
        int a_parent = find(parent, a);
        int b_parent = find(parent, b);
 
        if (a_parent < b_parent)
            parent[b_parent] = a_parent;
        else
            parent[a_parent] = b_parent;
    }
}
