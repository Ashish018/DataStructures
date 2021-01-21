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
        
        printPath(graph,src,dest,new boolean[vtces],Integer.toString(src),0,3); //k is kth highest weight
        
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
        /*
        1. You are given a number n (representing the number of students). Each student will have an id 
		   from 0 to n - 1.
		2. You are given a number k (representing the number of clubs)
		3. In the next k lines, two numbers are given separated by a space. The numbers are ids of 
		   students belonging to same club.
		4. You have to find in how many ways can we select a pair of students such that both students are 
		   from different clubs.
		   
		   Here number of friends is vertices and edges are bonds
		   
		   90% of question is of componenets find and after that its permutation and combination
		   Let in group1 there are 2, group 2 2 and in group 3 3 students
		   Then students of group 1(lets say 1) can be combined with group 2 with 4 ways-> student1 of group 1 in 2 ways and student2 also in
		   2 ways. Now group1 students can combine with group3.
		   After that group2 will combine with group3 and so on
         */
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
        
        /*
        1. You are given a directed acyclic graph. The vertices represent tasks and edges represent 
		   dependencies between tasks.
		2. You are required to find and print the order in which tasks could be done. The task that should be 
		   done at last should be printed first and the task which should be done first should be printed last. 
		   This is called topological sort. Check out the question video for details.
		
		Topological sort -> A permutation of vertices for a directed acyclic graph is called topological sort if 
		                    for all directed edges uv, u appears before v in the graph
		                    
		    Here we are using finding component concept.
		    Here we are findinding topological sort. Order of work will be opposite of topological sort
		    To find order of work instead of where you are putting in stack just print
         */
        
        
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
		//we are required to check if a graph has a path from the source vertex to the destination vertex or not. 
        if(src==dest) return true;
        
        ArrayList<Edge> neighbors=graph[src]; // neighours of src vertice
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
		//For graph to be connected there should be only one component
		return comps.size()==1;
	}
	
	private static void getComponents(ArrayList<Edge>[] graph, int vertice, ArrayList<Integer> comp, boolean[] visited) {
		
		/*
		1. You are given a graph.
		2. You are required to find and print all connected components of the graph.
		 */
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
		/*
		1. You are given a 2d array where 0's represent land and 1's represent water. 
		Assume every cell is linked to it's north, east, west and south cell.
		2. You are required to find and count the number of islands.
		
		We are using same concept that we used in component of graph where arr is virtual graph and i,j is the node
		We are checking if cell is 0 or not. If its 0 then then its cell
		We are not setting visited again false bcoz in for loop of main method we are going through each cell
		so if we do it to false then it will calculate component for that cell also which would have already been calculated  
		 */
		
		if(i<0 || j<0 || i>=arr.length||j>=arr[0].length||visited[i][j]||arr[i][j]==1) return;
		
		visited[i][j]=true;
		
		countIslands(arr,i-1,j,visited);
		countIslands(arr,i,j-1,visited);
		countIslands(arr,i+1,j,visited);
		countIslands(arr,i,j+1,visited);
		
		
	}
	
	
	 static void hamiltonianPathAndCycle(ArrayList < Edge > [] graph, int src, int dest, boolean[] visited, String psf,int orgSource) {
	        
		 /*
		  
		1. You are given a graph and a src vertex.
		2. You are required to find and print all hamiltonian paths and cycles starting from src. The cycles must end with "*" and paths with a "."
		
		Note -> A hamiltonian path is such which visits all vertices without visiting any twice. A hamiltonian path becomes a cycle if there is an edge between first and last vertex.
		Note -> Print in lexicographically increasing order.
		
		
		Its same as printing all paths but base case will be when all the vertices are visited
		and to check if its cyclic or not take out neighours of last vertice and compare with original source
		  */
		 
	        int count = 0;
	        for (boolean flag: visited) {
	            if (flag) count++;
	        }

	        if (count == visited.length-1) { // -1 bcoz path so far is one step ahead of visited
	            
	            
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

