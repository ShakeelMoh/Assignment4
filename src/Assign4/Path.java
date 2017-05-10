/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assign4;

/**
 *
 * @author shakeel
 */
//Path class can be compared to another Path class
// Represents an entry in the priority queue for Dijkstra's algorithm.
public class Path implements Comparable<Path>{

    public Vertex dest;   // w
    public double cost;   // d(w)

    public Path(Vertex d, double c) {
        dest = d;
        cost = c;
    }

    public int compareTo(Path rhs) {
        double otherCost = rhs.cost;

        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }

}
