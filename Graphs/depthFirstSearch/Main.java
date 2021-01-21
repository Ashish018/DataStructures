package depthFirstSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	
	static int largestWeight=Integer.MIN_VALUE;
	static String highestWeightPath;
	
	static int leastWeight=Integer.MAX_VALUE;
	static String leastWeightPath;
	
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
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

        int src = Integer.parseInt(br.readLine());
        int dest = Integer.parseInt(br.readLine());
        
        System.out.println(hasPath(graph, src, dest, new boolean[vtces]));
        
        printPath(graph,src,dest,new boolean[vtces],Integer.toString(src),0,3); 
        
        ArrayList < ArrayList < Integer >> comps = new ArrayList < > ();

        boolean visited[]=new boolean[vtces];
        for(int i=0;i<vtces;i++)
        {
        	
        	if(!visited[i]) {
        		
        		ArrayList<Integer> comp=new ArrayList<Integer>();
        		getComponents(graph,i,comp,visited);
        		comps.add(comp);
        	}
        }
        
        
        System.out.println(comps);
        System.out.println(isGraphConnected(comps));
        
        
        //<-----------------------Island Count---------------------------------------->
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of rows and cols of 2D array for island count");
        System.out.println("Enter 2D array for island count");
        int row=sc.nextInt();
		int col=sc.nextInt();
        boolean visitedIslandsCell[][]=new boolean[row][col];
        int islandCountArr[][]=new int[row][col];
        
        for(int i=0;i<row;i++)
        {
        	for(int j=0;j<col;j++) islandCountArr[i][j]=sc.nextInt();
        }
        int count=0;
        
        for(int i=0;i<row;i++)
        {
        	for(int j=0;j<col;j++) 
        	{
        		if(islandCountArr[i][j]==0 && !visitedIslandsCell[i][j]) 
        			{
        				countIslands(islandCountArr,i,j,visitedIslandsCell);
        				count++;
        			}
        	}
        }
        System.out.println(count);
        
        
        
      //<-----------------------Island Count---------------------------------------->
        
      //<----------------------Perfect Friend-------------------------------------->
        
        int countFriend=0;
        for(int i=0;i<comps.size();i++){
            for(int j=i+1;j<comps.size();j++)
            {
            	countFriend+=comps.get(j).size()*comps.get(i).size();
            }
        }
        System.out.println(countFriend);
        
      //<----------------------Perfect Friend-------------------------------------->
        
        
      //<----------------------Hamiltonian Path And Cycle-------------------------------------->
        
        boolean hamiltonianVisited[] = new boolean[vtces];
        int hamiltonianSrc = Integer.parseInt(br.readLine());
        hamiltonianPathAndCycle(graph, hamiltonianSrc, 1, hamiltonianVisited, hamiltonianSrc + "",hamiltonianSrc);
        
      //<----------------------Hamiltonian Path And Cycle-------------------------------------->
     
      //<----------------------Order Of Compilation-------------------------------------->
        
      
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList < > ();
        }

        edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            graph[v1].add(new Edge(v1, v2));
        }

        
		Stack<Integer> stack=new Stack<Integer>();
		visited=new boolean[vtces];
		for(int i=0;i<vtces;i++)
		{
			if(!visited[i]) topographicalSort(graph,i,visited,stack);
		}
		
        while(!stack.isEmpty()) System.out.println(stack.pop());
        
      //<----------------------Order Of Compilation-------------------------------------->
        
	}
	
	
	
	private static void topographicalSort(ArrayList<Edge>[] graph, int i, boolean[] visited,
			Stack<Integer> stack) {
		
		visited[i]=true;
		
		ArrayList<Edge> neighbors=graph[i];
		for(Edge edge:neighbors)
		{
			if(!visited[edge.nbr]) topographicalSort(graph,edge.nbr,visited,stack);
		}
		
		stack.push(i);
		
	}



	static boolean hasPath(ArrayList<Edge> graph[],int src,int dest,boolean[] visited)
    {
	    if(src==dest) return true;
        
        ArrayList<Edge> neighbors=graph[src];
        visited[src]=true;
        for(Edge edge:neighbors)
        {
            if(visited[edge.nbr]==false)
            {
                if(hasPath(graph,edge.nbr,dest,visited)) return true;   
            }
        }
        return false;
        
    }
	
	
	static void printPath(ArrayList < Edge > graph[], int src, int dest, boolean[] visited,String psf,int wsf,int k) {
        if (src == dest) {
            System.out.println(psf);
            if(wsf>largestWeight)
            {
            	largestWeight=wsf;
            	highestWeightPath=psf;
            }
            
            if(wsf<leastWeight)
            {
            	leastWeight=wsf;
            	leastWeightPath=psf;
            }
            
            
            return ;
        }

        ArrayList < Edge > neighbors = graph[src];
        visited[src] = true;
        for (Edge edge: neighbors) {
            if (visited[edge.nbr] == false) {
                printPath(graph, edge.nbr, dest, visited,psf+""+edge.nbr,wsf+edge.wt,k);
            }
        }
        visited[src]=false;

    }
	
	static boolean isGraphConnected(ArrayList<ArrayList<Integer>> comps)
	{
		return comps.size()==1;
	}
	
	private static void getComponents(ArrayList<Edge>[] graph, int vertice, ArrayList<Integer> comp, boolean[] visited) {
		
		comp.add(vertice);
		visited[vertice]=true;
		ArrayList<Edge> neighbors=graph[vertice];
		for(Edge edge:neighbors)
		{
			if(!visited[edge.nbr]) getComponents(graph,edge.nbr,comp,visited);
		}
	
	}
	
	static void countIslands(int arr[][],int i,int j,boolean visited[][])
	{
		
		if(i<0 || j<0 || i>=arr.length||j>=arr[0].length||visited[i][j]||arr[i][j]==1) return;
		
		visited[i][j]=true;
		
		countIslands(arr,i-1,j,visited);
		countIslands(arr,i,j-1,visited);
		countIslands(arr,i+1,j,visited);
		countIslands(arr,i,j+1,visited);
		
		
	}
	
	
	 static void hamiltonianPathAndCycle(ArrayList < Edge > [] graph, int src, int dest, boolean[] visited, String psf,int orgSource) {
	        
	        int count = 0;
	        for (boolean flag: visited) {
	            if (flag) count++;
	        }

	        if (count == visited.length-1) { 
	            
	            
	            boolean cyclic=false;
	            for(Edge e:graph[src])
	            {
	                if(e.nbr==orgSource) cyclic=true;
	            }
	            if(cyclic) System.out.println(psf+"*");
	            else System.out.println(psf+".");
	            return;
	        }
	        
	        visited[src] = true;

	        ArrayList < Edge > neighors = graph[src];
	        for (Edge edge: neighors) {
	            if (!visited[edge.nbr]) hamiltonianPathAndCycle(graph, edge.nbr, dest, visited, psf + "" + edge.nbr,orgSource);
	        }
	        visited[src] = false;
	    }
}

