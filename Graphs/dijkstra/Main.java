package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;



public class Main {
	
	static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }

		public Edge(int src, int nbr) {
			this.src = src;
            this.nbr = nbr;
		}
    }
	
	
	static class Pair implements Comparable<Pair>{
        int v;
        String psf; //path so far
        int wsf;//weight so far
        
		
		Pair(int v, int wsf,String psf) { 
			super();
			this.v = v;
			this.wsf = wsf;
			this.psf = psf;
		}
		
		@Override
		public int compareTo(Pair o) {
			
			return this.wsf-o.wsf;
		}
		
		
    }

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         
	        
	        int vertices = Integer.parseInt(br.readLine());
	        ArrayList < Edge > [] graph = new ArrayList[vertices];
	        for (int i = 0; i < vertices; i++) {
	        	graph[i] = new ArrayList < > ();
	        }

	        int edges = Integer.parseInt(br.readLine());
	        for (int i = 0; i < edges; i++) {
	            String[] parts = br.readLine().split(" ");
	            int v1 = Integer.parseInt(parts[0]);
	            int v2 = Integer.parseInt(parts[1]);
	            int wt = Integer.parseInt(parts[2]);
	            graph[v1].add(new Edge(v1, v2, wt));
	            graph[v2].add(new Edge(v2, v1, wt));
	        }

	        int src = Integer.parseInt(br.readLine());
	        
	        PriorityQueue<Pair> queue=new PriorityQueue<Main.Pair>();
	        queue.add(new Pair(src, 0,src+""));
	        boolean visited[]=new boolean[vertices];
	        
	        
	        while(!queue.isEmpty())
	        {
	        	Pair rem=queue.remove();
	        	if(!visited[rem.v])
	        	{
	        		visited[rem.v]=true;
	        		System.out.println(rem.v+" via "+rem.psf+" @ "+rem.wsf);
	        		for(Edge e:graph[rem.v])
	        		{
	        			if(!visited[e.nbr]) queue.add(new Pair(e.nbr,rem.wsf+e.wt,rem.psf+""+e.nbr));
	        		}
	        		
	        	}
	        }

	}

}
