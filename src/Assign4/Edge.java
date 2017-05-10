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
public class Edge {

    public Vertex dest;   // Second vertex in Edge
    public double cost;   // Edge cost

    public Edge(Vertex d, double c) {
        dest = d;
        cost = c;
    }
}
