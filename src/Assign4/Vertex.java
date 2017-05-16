/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assign4;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shakeel
 */
public class Vertex {

    public String name;   // Vertex name
    public List<Edge> adj;    // Adjacent vertices
    public double dist;   // Cost
    public Vertex prev;   // Previous vertex on shortest path
    public int scratch;// Extra variable used in algorithm

    public Vertex(String nm) {
        name = nm;
        adj = new LinkedList<>();
        reset();
    }

    public void reset() //  { dist = SimulatorOne.INFINITY; prev = null; pos = null; scratch = 0; }    
    {
        dist = SimulatorOne.INFINITY;
        prev = null;
        scratch = 0;
    }
    
 
}
