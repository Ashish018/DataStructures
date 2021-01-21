package primsAlgorithm;

import java.io.BufferedReader;
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
    }

    static class Pair implements Comparable < Pair > {
        int v;
        int wt; // weight 
        int av; 

        public Pair(int v, int av, int wt) {
            super();
            this.v = v;
            this.wt = wt;
            this.av = av;
        }
        @Override
        public int compareTo(Pair o) {

            return this.wt - o.wt;
        }

    }


    public static void main(String[] args) throws Exception {
    	
    	
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

        PriorityQueue < Pair > queue = new PriorityQueue < Main.Pair > ();
        queue.add(new Pair(0, -1, 0));
        boolean visited[] = new boolean[vertices];


        while (!queue.isEmpty()) {
            Pair rem = queue.remove();
            if (!visited[rem.v]) {
                visited[rem.v] = true;
                if (rem.av != -1)
                    System.out.println("[" + rem.v + "-" + rem.av + "@" + rem.wt + "]");
                for (Edge e: graph[rem.v]) {
                    if (!visited[e.nbr]) queue.add(new Pair(e.nbr, rem.v, e.wt));
                }

            }
        }

    }

}