package PA09;

import java.util.Arrays;
import java.util.Stack;

public class GraphAdjMatrix implements Graph {
	private int[][] edges;	// collection of all edges
	private int vertice;
	
	// constructor
	public GraphAdjMatrix(int vertices) {
		edges = new int[vertices][vertices];
		vertice = vertices;
	}
	
	public GraphAdjMatrix() {
		edges = new int[0][0];
		vertice = 0;
	}

	@Override
	public void addEdge(int v1, int v2) {
		edges[v1][v2] = 1;
		edges[v2][v1] = 1;
	}

	@Override
	public void topologicalSort() {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<Integer>();
		 
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[vertice];
        for(int i = 0; i < visited.length; i++) {
        		if(!visited[i]) {
        			dfSearch(i, visited, stack);
        		}
        }
    }
	private void dfSearch(int vertex, boolean[] visited, Stack<Integer> stack) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(new Integer(vertex));
		while(!s.empty()) {
			
			int v = s.pop();
			//System.out.println(v);				//Check sorting here.
			visited[v] = true;

			for(int i = 0; i < neighbors(v).length; i++) {
				int n = neighbors(v)[i];
				if(!visited[n]) {
					s.push(new Integer(n));
					visited[n] = true;
				}
			}
		}
	}
	
	// list of neighbors for a vertex
	public int[] neighbors(int vertex) {
		// TODO Auto-generated method stub
		int[] vertexs = new int[outdegree(vertex)];
		int index = 0;
		
		for(int i = 0; i < edges.length; i++) {
			if(edges[vertex][i] == 1) {
				vertexs[index] = i;
				index++;
			}
		}
		return vertexs;
	}
	
	// number of vertexs
	public int outdegree(int vertex) {
		int degree = 0;
		for(int i = 0; i < edges.length; i++) {
			if(edges[vertex][i] != Double.POSITIVE_INFINITY &&
					edges[vertex][i] != 0) {
				degree++;
			}
		}
		return degree;
	}

	@Override
	/*
	 * Adds an undirected edge or weight w between two vertices
	 * 
	 */
	public void addEdge(int v1, int v2, int weight) {
		// TODO Auto-generated method stub
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
	}

	@Override
	public int getEdge(int v1, int v2) {
		// TODO Auto-generated method stub
		return edges[v1][v2];
	}

	@Override
	public int createSpanningTree() {
		// TODO Auto-generated method stub
        
        int[] Distance = new int[vertice];	// list to find min vertex
        int[] Path = new int[vertice];
        // Mark all the vertices as not visited
        boolean VNotInTree[] = new boolean[vertice];
        
        int result = 0;
        
        //Create a list of all edges
        for(int i = 0; i < vertice; i++) {
        		Distance[i] = Integer.MAX_VALUE;
        }
        
        //Start vertex is in the spanning tree
        Distance[0] = 0;
        Path[0] = -1;
        
        for(int i = 0; i < vertice-1; i++) {
        		// find min vertex 
            int v = min(Distance, VNotInTree);
            VNotInTree[v] = true;
            
            for(int j = 0; j < vertice; j++) {
				if(edges[v][j] != 0 && VNotInTree[j] == false && edges[v][j] < Distance[j]) {
					Path[j] = v;
					Distance[j] = edges[v][j];					
				}
			}
        }
        for(int i = 1; i < vertice; i++) {
    			result += edges[i][Path[i]];
		}
        
		return result;
	}
	
	/*
	 * to find the vertex with minimum key value, from the set of vertices not yet included in MST
	 */
	private int min(int Distance[], boolean VNotInTree[]) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		
		//While there are vertices not in the spanning tree
			//Add the cheapest vertex to the spanning tree
		for(int i = 0; i < vertice ; i++) {
			if(VNotInTree[i] == false && Distance[i] < min) {
				min = Distance[i];
				min_index = i;
			}
		}
		return min_index;
	}
	
}
