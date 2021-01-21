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
        int infectionTime; 
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
	        
	        
	        
	       
	        
	      //<------------------------- Spread Infection  --------------------> 
	        
	        
	        int infectionSrc = Integer.parseInt(br.readLine());
	        int infectionGraphTime = Integer.parseInt(br.readLine());  
	        System.out.println(spreadInfection(graph,infectionSrc,infectionGraphTime,vtces));
	        
	      //<------------------------- Spread Infection  --------------------> 
	        
	        
	       //<-----------------Iterative Depth Search--------------->
	        
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
		
		int count=0;
		
		boolean visited[]=new boolean[v];
		
		ArrayDeque<Pair> queue=new ArrayDeque<Main.Pair>();
		
		queue.add(new Pair(src, 1));
		
		while(!queue.isEmpty())
		{
			Pair rem=queue.remove();
			
			if(!visited[rem.v])
			{
				visited[rem.v]=true;
				if(rem.infectionTime>time) return count; 
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
