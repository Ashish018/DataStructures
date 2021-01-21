package breadthFirstSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;


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
	

	static class Pair {
        int v;
        int infectionTime; // for infection spread question only
        String psf; //path so far
        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
		Pair(int v, int infectionTime) { 
			super();
			this.v = v;
			this.infectionTime = infectionTime;
		}
    }
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		//<----------------------Breadth First Search------------------------------>
        
	      //Shortest path in terms of edges(uses queue)
	        //r m* w a*
	        
		int vtces = Integer.parseInt(br.readLine());
        ArrayList < Edge > [] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
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

	        int BFSSrc = Integer.parseInt(br.readLine());
	        
	        
	        ArrayDeque<Pair> queue=new ArrayDeque<Main.Pair>();
	        queue.add(new Pair(BFSSrc, BFSSrc+""));
	        boolean BFSVisited[]=new boolean[vtces];
	        bfs(graph,BFSSrc,queue,BFSVisited);
	       
	      //<----------------------Breadth First Search------------------------------>
	        
	     //<------------------------- Is a Graph Cyclic?  using BFS-------------------->
	        
	        
	        //To find cyclic check if we are reaching a vertice which was already visited. If yes then its cyclic 
	        //else no
	        

	        
	        ArrayDeque<Pair> cyclicBFSQueue=new ArrayDeque<Main.Pair>();
	        boolean BFSCyclicVisited[]=new boolean[vtces];
	     
	        
	        
	        for(int i=0;i<vtces;i++)
	        {
	        	 if(!BFSCyclicVisited[i]) {
	        		 if(cyclicBFS(graph,i,cyclicBFSQueue,BFSCyclicVisited))
	        		 {
	        			 System.out.println(true);
	        			 return;
	        		 }
	        	 }
	        	
	        }
	        System.out.println(false);
	       
	     
	     //<------------------------- Is a Graph Cyclic?  using BFS-------------------->   
	        
	        
	        
	        
	      //<------------------------- Is a Graph Bipartite?  --------------------> 
	        
	        
	        /*
	        A graph is called bipartite if it is possible to split it's vertices in two sets of mutually exclusive and exhaustive vertices such that all edges are across sets.

			1. You are given a graph.
			2. You are required to find and print if the graph is bipartite
			
			If a graph is non cyclic then it will be bipartite
			If a graph is  cyclic of even size(number of vertices is even) then it will be bipartite else no
			
			Even if one component is not bipartite then whole graph is not bipartitie
	         */
	        
	   
	      //<------------------------- Is a Graph Bipartite?  --------------------> 
	        
	        
	      //<------------------------- Spread Infection  --------------------> 
	        
	        
	        int infectionSrc = Integer.parseInt(br.readLine());
	        int infectionGraphTime = Integer.parseInt(br.readLine());
	        
	        
	        System.out.println(spreadInfection(graph,infectionSrc,infectionGraphTime,vtces));
	        
	      //<------------------------- Spread Infection  --------------------> 
	        
	        
	       //<-----------------Iterative Depth Search--------------->
	       //Same as BFS but in this instead of recursive we are using stack
	        
	        int src = Integer.parseInt(br.readLine());
	        Stack<Pair> stack=new Stack<Main.Pair>();
	        
	        stack.push(new Pair(src,src+""));
	        boolean visited[]=new boolean[vtces];
	        
	        while(!stack.isEmpty())
	        {
	        	Pair rem=stack.pop();
	        	if(!visited[rem.v])
	        	{
	        		visited[rem.v]=true;
	        		System.out.println(rem.v+"@"+rem.psf);
	        		for(Edge e:graph[rem.v])
	        		{
	        			if(!visited[e.nbr]) stack.push(new Pair(e.nbr,rem.psf+e.nbr));
	        		}
	        	}
	        	
	        }
	        
	        
	        
	      //<-----------------Iterative Depth Search--------------->

	}
	
	static void bfs(ArrayList<Edge>[] BFSGraph, int bFSSrc, ArrayDeque<Pair> queue, boolean[] BFSVisited)
	{
		////BFS is for shortest path in terms of edges
		//r m* w a*
		 while(!queue.isEmpty())
	        {
	        	Pair node=queue.remove();
	        	if(!BFSVisited[node.v]) // if not visited
	        	{
	        		BFSVisited[node.v]=true;
	        		System.out.println(node.v+"@"+node.psf);
	        		for(Edge neighour:BFSGraph[node.v])
	        		{
	        			if(!BFSVisited[neighour.nbr])
	        			{
	        				queue.add(new Pair(neighour.nbr,node.psf+""+neighour.nbr));
	        			}
	        		}
	        	}
	        }
	}
	
	static boolean cyclicBFS(ArrayList<Edge>[] BFSGraph, int bFSSrc, ArrayDeque<Pair> queue, boolean[] BFSVisited)
	{
		//r m* w a*
		queue.add(new Pair(bFSSrc,bFSSrc+""));

		while(!queue.isEmpty())
		{
		    Pair node=queue.remove();
		    if(!BFSVisited[node.v])
		    {
		        BFSVisited[node.v]=true;
		        for(Edge edge:BFSGraph[node.v])
		        {
		            if(!BFSVisited[edge.nbr]) queue.add(new Pair(edge.nbr,node.psf+""+edge.nbr));
		        }
		    }
		    else return true;
		}
		return false;
	}
	
	static int spreadInfection(ArrayList < Edge > [] graph,int src,int time,int v)
	{
		
		/*
		1. You are given a graph, representing people and their connectivity.
		2. You are also given a src person (who got infected) and time t.
		3. You are required to find how many people will get infected in time t, 
		   if the infection spreads to neighbors of infected person in 1 unit of time.
		 */
		
		int count=0;
		
		boolean visited[]=new boolean[v];
		
		ArrayDeque<Pair> queue=new ArrayDeque<Main.Pair>();
		
		queue.add(new Pair(src, 1)); // In begining this is infected at t=1;
		
		while(!queue.isEmpty())
		{
			Pair rem=queue.remove();
			
			if(!visited[rem.v])
			{
				visited[rem.v]=true;
				if(rem.infectionTime>time) return count; // Means time has reached
				count++;
				
				for(Edge e:graph[rem.v])
				{
					if(!visited[e.nbr]) queue.add(new Pair(e.nbr, rem.infectionTime+1));
				}
				
			}
			
			
		}
		return count;
	}
}
