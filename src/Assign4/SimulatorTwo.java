/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assign4;

import static Assign4.SimulatorOne.INFINITY;
import static Assign4.SimulatorOne.flag;
import static Assign4.SimulatorOne.processRequest;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author shakeel
 */
public class SimulatorTwo {

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

        Scanner in = new Scanner(System.in);

        System.out.println("Ambulances roam from node to node and can be at any node at call time\n\n");

        while (ambulanceCall(in, g)); //When process request returns false it stops

        System.out.println();

        System.out.println("Assuming edge weights represent time (in minutes): \n\n");
        
        while(costCall(in, g));

    }

    public static boolean ambulanceCall(Scanner in, SimulatorOne g) {
        //Read graph instantiated to access victim and hospital arraylists
        ReadGraph r = new ReadGraph();
        r.read();
        ArrayList<Integer> vNode = r.getVictimNodes();
        ArrayList<Integer> hNode = r.getHospitalNodes();

        Random ran = new Random();
        int ambulanceNode = ran.nextInt(r.numNodes);
        System.out.println();
        System.out.println("Ambulance at node: " + ambulanceNode + "\n");

        System.out.println();
        for (int i = 0; i < vNode.size(); i++) {

            System.out.println("victim " + vNode.get(i));
            //g.dijkstra(vNode.get(i) + "");

            double shortest = INFINITY;
            for (int j = 0; j < hNode.size(); j++) { //Depending on number of ambulances

                double currentShort = 0;
                flag = false;

                //System.out.println("hospital " + hNode.get(j));
                g.dijkstra(ambulanceNode + "");//DIJK
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
                g.dijkstra(ambulanceNode + "");
                check += g.calcPath(vNode.get(i) + "");
                g.dijkstra(vNode.get(i) + "");

                check += g.calcPath(hNode.get(k) + "");
                if (check == shortest) {
                    System.out.println("hospital " + hNode.get(k));
                    g.dijkstra(ambulanceNode + "");
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

    }

    public static boolean costCall(Scanner in, SimulatorOne g) {
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
                    double totalTime = 0;
                    
                    System.out.println("hospital " + hNode.get(k));
                    g.dijkstra(hNode.get(k) + "");
                    totalTime += g.printCostPath(vNode.get(i) + "");
                    System.out.print(" ");
                    g.dijkstra(vNode.get(i) + "");
                    flag = true;
                    totalTime += g.printCostPath(hNode.get(k) + "");
                    System.out.println();
                    
                    System.out.println("Total time taken: " + totalTime + " minutes" + "\n");
                }
            }

        }
        return false;

    }

}
