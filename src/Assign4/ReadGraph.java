/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assign4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shakeel
 */
public class ReadGraph {

    public static int numNodes;
    public static int sourceNode;
    public static int destNode;
    public static int weight;

    public static int numHospitals;
    public static ArrayList<Integer> hospitalNodes;
    public static ArrayList<Integer> victimNodes;
    public static int numVictims;

    public static void main(String[] args) {

        try {
            PrintWriter wr = new PrintWriter("Graph.txt", "UTF-8");

            hospitalNodes = new ArrayList<>();
            victimNodes = new ArrayList<>();

            try {
                Scanner sc = new Scanner(new File("/home/shakeel/NetBeansProjects/Assignment4/Data/GraphData"));

                //Get number of vertexes in the graph
                numNodes = Integer.parseInt(sc.nextLine());
                //Set vertex array size

                //Create vertexes
                for (int i = 0; i < numNodes; i++) {

                    String writeLine = "";
                    int c = 0;

                    //Create vertex for each line
                    String line = sc.nextLine();
                    String[] numbers = line.split(" ");

                    sourceNode = Integer.parseInt(numbers[c]);
                    
                   
                    
                    //New vertex is created
                    for (int j = 0; j < (numbers.length - 1) / 2; j++) {
                        destNode = Integer.parseInt(numbers[c + 1]);
                        c++;

                        weight = Integer.parseInt(numbers[c + 1]);
                        c++;
                        
                        writeLine += sourceNode + " " + destNode + " " + weight + "\n";
                        //New edge created with src dest and weight

                    }
                    
                    wr.print(writeLine);

                }
                wr.close();

                numHospitals = Integer.parseInt(sc.nextLine());

                String line = sc.nextLine();
                String[] numbers = line.split(" ");

                for (int j = 0; j < numbers.length; j++) {

                    hospitalNodes.add(Integer.parseInt(numbers[j]));

                }

                numVictims = Integer.parseInt(sc.nextLine());

                line = sc.nextLine();
                numbers = line.split(" ");

                for (int i = 0; i < numbers.length; i++) {

                    victimNodes.add(Integer.parseInt(numbers[i]));
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadGraph.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("There are " + numNodes + " nodes");

            System.out.println(numHospitals + " hospitals");
            System.out.println(numVictims + " victims");

            System.out.println("The hospital nodes are " + hospitalNodes.toString());
            System.out.println("The victim nodes are " + victimNodes.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadGraph.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ReadGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
