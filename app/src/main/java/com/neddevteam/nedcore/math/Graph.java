package com.neddevteam.nedcore.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by gdefermin on 2/15/15.
 */
public class Graph<T> {
    private HashMap<T, List<T>> graph;

    /**
     * Creates a graph from the given list of vertices with no relations
     * @param vertices Initial vertices of the graph
     */
    public Graph(List<T> vertices){
        graph = new HashMap<>();
        for(T vertex: vertices){
            graph.put(vertex, new ArrayList<T>());
        }
    }

    /**
     * Creates an empty graph
     */
    public Graph(){
        this.graph = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph with no relations.<br>
     * If relations want to be added immediately, skip this and use {@link #addEdge(Object, Object)}
     * directly.<br>
     * If the vertex exists, nothing happens.
     * @param vertex The vertex to be added
     */
    public void addVertex(T vertex){
        if(!graph.containsKey(vertex)){
            graph.put(vertex, new ArrayList<T>());
        }
    }

    /**
     * Removes the given vertex and all of its edges
     * @param vertex The vertex to be removed
     */
    public void removeVertex(T vertex){
        graph.remove(vertex);
    }

    /**
     * Creates an edge between {@code vertex1} and {@code vertex2}, which are added to
     * the graph if they aren't already there
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(T vertex1, T vertex2){
        if(vertex1.equals(vertex2)) return;

        //Add vertex2 to the list of vertex1's relations
        List<T> edges;
        if(!graph.containsKey(vertex1)){
            edges = new ArrayList<>();
        } else {
            edges = graph.get(vertex1);
        }
        if(!edges.contains(vertex2)) {
            edges.add(vertex2);
            graph.put(vertex1, edges);
        }


        //Add vertex1 to the list of vertex2's relations
        if(!graph.containsKey(vertex2)){
            edges = new ArrayList<>();
        } else {
            edges = graph.get(vertex2);
        }
        if(!edges.contains(vertex1)) {
            edges.add(vertex1);
            graph.put(vertex2, edges);
        }
    }

    /**
     * Removes the edge that connects {@code vertex1} and {@code vertex2} if it exists.<br>
     * If any of the vertices aren't in the graph, this does nothing.
     * @param vertex1
     * @param vertex2
     */
    public void removeEdge(T vertex1, T vertex2){
        if(graph.containsKey(vertex1) && graph.containsKey(vertex2)){
            //Remove v2 from v1's list
            List<T> edges = graph.get(vertex1);
            edges.remove(vertex2);
            graph.put(vertex1, edges);

            //Remove v1 from v2's list
            edges = graph.get(vertex2);
            edges.remove(vertex1);
            graph.put(vertex2, edges);
        }
    }

    /**
     * Returns a set with all the vertices in the graph.
     * @return vertices
     */
    public Set<T> getVertices(){
        return graph.keySet();
    }

    /**
     * Returns a list with all the vertices that {@code vertex} is connected with.
     * @param vertex
     * @return vertices
     */
    public List<T> getRelations(T vertex){
        return graph.get(vertex);
    }

    /**
     * True if both vertices exist an there is an edge connecting them, false otherwise.
     * @param vertex1
     * @param vertex2
     * @return
     */
    public boolean edgeExsists(T vertex1, T vertex2){
        if(!graph.containsKey(vertex1) || !graph.containsKey(vertex2))
            return false;
        return graph.get(vertex1).contains(vertex2);
    }

    /**
     *
     * @param vertex
     * @return Whether {@code vertex} belongs to the graph
     */
    public boolean vertexExists(T vertex){
        return graph.containsKey(vertex);
    }

}
