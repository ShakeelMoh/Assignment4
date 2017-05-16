/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assign4;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author shakeel
 */
public class SimulatorOne {

    static ReadGraph r;
    public static boolean flag;
    //Init variable for unknown lengths
    public static final double INFINITY = Double.MAX_VALUE;

    //Creates vertex map
    public Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();//Changed from private

    //Methods
    /**
     * If vertexName is not present, add it to vertexMap. In either case, return
     * the Vertex.
     */
    private Vertex getVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {
            //Creates new vertex
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }

    /**
     * Add a new edge to the graph.
     */
    public void addEdge(String sourceName, String destName, double cost) {
        Vertex v = getVertex(sourceName);
        Vertex w = getVertex(destName);
        v.adj.add(new Edge(w, cost));
    }

    /**
     * It calls recursive routine to print shortest path to destNode after a
     * shortest path algorithm has run.
     */
    public double printPath(String destName) {
        Vertex w = vertexMap.get(destName);
        if (w == null) {
            throw new NoSuchElementException("Destination vertex not found");
        } else if (w.dist == INFINITY) {
            System.out.println(destName + " is unreachable");
        } else {
            //System.out.print("(Cost is: " + w.dist + ") ");
            printPath(w);

            //System.out.println();
        }
        return w.dist;
    }

    /**
     * Recursive routine to print shortest path to dest after running shortest
     * path algorithm. The path is known to exist.
     */
    private void printPath(Vertex dest) {
        if (dest.prev != null) {
            if (flag == false) {
                printPath(dest.prev);
                System.out.print(" ");
            }

        }
        System.out.print(dest.name);
    }
    
        public double printCostPath(String destName) {
        Vertex w = vertexMap.get(destName);
        if (w == null) {
            throw new NoSuchElementException("Destination vertex not found");
        } else if (w.dist == INFINITY) {
            System.out.println(destName + " is unreachable");
        } else {
            //System.out.print("(Cost is: " + w.dist + ") ");
            printPath(w);

            //System.out.println();
        }
        return w.dist;
    }

    /**
     * Recursive routine to print shortest path to dest after running shortest
     * path algorithm. The path is known to exist.
     */
    private void printCostPath(Vertex dest) {
        if (dest.prev != null) {
            if (flag == false) {
                printPath(dest.prev);
                System.out.print(" ");
            }

        }
        System.out.print(dest.name);
    }

    public double calcPath(String destName) {
        Vertex w = vertexMap.get(destName);
        if (w == null) {
            throw new NoSuchElementException("Destination vertex not found");
        } else if (w.dist == INFINITY) {
            //System.out.println(destName + " is unreachable");
        } else {
            //System.out.print("(Cost is: " + w.dist + ") ");
            calcPath(w);

            //System.out.println();
        }
        return w.dist;
    }

    /**
     * Recursive routine to print shortest path to dest after running shortest
     * path algorithm. The path is known to exist.
     */
    private void calcPath(Vertex dest) {
        if (dest.prev != null) {
            if (flag == false) {
                calcPath(dest.prev);
                //System.out.print(" ");
            }

        }
        //System.out.print(dest.name);
    }

    /**
     * Initializes the vertex output info prior to running any shortest path
     * algorithm.
     */
    private void clearAll() {
        for (Vertex v : vertexMap.values()) {
            v.reset();
        }
    }

    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra) using priority
     * queues based on the binary heap
     */
    public void dijkstra(String startName) {

        PriorityQueue<Path> pq = new PriorityQueue<Path>();

        Vertex start = vertexMap.get(startName);
        if (start == null) {
            throw new NoSuchElementException("Start vertex not found");
        }

        clearAll();
        pq.add(new Path(start, 0));
        start.dist = 0;

        int nodesSeen = 0;
        while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {
            Path vrec = pq.remove();
            Vertex v = vrec.dest;
            if (v.scratch != 0) // already processed v
            {
                continue;
            }

            v.scratch = 1;
            nodesSeen++;

            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double cvw = e.cost;

                if (cvw < 0) {
                    System.out.println("Graph has negative edges");
                    System.exit(0);
                }

                if (w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                    pq.add(new Path(w, w.dist));
                }
            }
        }
    }

    public static boolean processRequest(Scanner in, SimulatorOne g) {
        //Read graph instantiated to access victim and hospital arraylists
        ReadGraph r = new ReadGraph();
        r.read();
        ArrayList<Integer> vNode = r.getVictimNodes();
        ArrayList<Integer> hNode = r.getHospitalNodes();

        System.out.println();
        for (int i = 0; i < vNode.size(); i++) {

            System.out.println("victim " + vNode.get(i));
            //g.dijkstra(vNode.get(i) + "");

            double shortest = INFINITY;
            for (int j = 0; j < hNode.size(); j++) {

                double currentShort = 0;
                flag = false;

                //System.out.println("hospital " + hNode.get(j));
                g.dijkstra(hNode.get(j) + "");//DIJK
                currentShort += g.calcPath(vNode.get(i) + "");//PATH

                
                g.dijkstra(vNode.get(i) + "");//DIJK
                flag = true;
                currentShort += g.calcPath(hNode.get(j) + "");//PATH

                //System.out.println(currentShort);
                if (currentShort < shortest) {
                    shortest = currentShort;

                }

            }

            for (int k = 0; k < hNode.size(); k++) {
                flag = false;
                double check = 0;
                g.dijkstra(hNode.get(k) + "");
                check += g.calcPath(vNode.get(i) + "");
                g.dijkstra(vNode.get(i) + "");

                check += g.calcPath(hNode.get(k) + "");
                if (check == shortest) {
                    System.out.println("hospital " + hNode.get(k));
                    g.dijkstra(hNode.get(k) + "");
                    g.printPath(vNode.get(i) + "");
                    System.out.print(" ");
                    g.dijkstra(vNode.get(i) + "");
                    flag = true;
                    g.printPath(hNode.get(k) + "");
                    System.out.println();
                }
            }

        }
        return false;
//            System.out.print("Enter start node:");
//            String startName = in.nextLine();
//
//            System.out.print("Enter destination node:");
//            String destName = in.nextLine();
//
//            System.out.print("Using Dijkstra's shortest path algorithm... ");
//
//            g.dijkstra(startName);
//            g.printPath(destName);
//
//            g.printPath(destName);

    }

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        SimulatorOne g = new SimulatorOne();

        try {
            //FileReader fin = new FileReader(args[0]);
            FileReader fin = new FileReader("Data/Graph.txt");
            Scanner graphFile = new Scanner(fin);

            // Read the edges and insert
            String line;
            while (graphFile.hasNextLine()) {
                line = graphFile.nextLine();
                StringTokenizer st = new StringTokenizer(line);

                try {
                    if (st.countTokens() != 3) {
                        System.err.println("Skipping ill-formatted line " + line);
                        continue;
                    }
                    String source = st.nextToken();
                    String dest = st.nextToken();
                    int cost = Integer.parseInt(st.nextToken());
                    g.addEdge(source, dest, cost);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping ill-formatted line " + line);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        System.out.println("File read...");
        System.out.println(g.vertexMap.size() + " vertices");

        Scanner in = new Scanner(System.in);
        while (processRequest(in, g));
    }

}
