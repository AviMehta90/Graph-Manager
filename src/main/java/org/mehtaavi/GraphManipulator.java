package org.mehtaavi;

import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class GraphManipulator {

    private static MutableGraph g;
    // Feature 1: Parse a DOT graph file to create a graph
    public static void parseGraph(String filePath) throws IOException {
        InputStream dot = new FileInputStream(filePath);
        g = new Parser().read(dot);
        System.out.println("Dot File Parsed at " + filePath);
    }

    public static int getNumberOfNodes() {
        return g.nodes().size();
    }

    public static Set<String> getNodeLabels() {
        Set<String> labels = new HashSet<>();
        for (var node : g.nodes()) {
            labels.add(node.name().toString());
        }
        labels.add("Number of Nodes: "+getNumberOfNodes());
        return labels;
    }

    public static int getNumberOfEdges() {
        return g.edges().size();
    }

    public static Set<String> getEdgeInfo() {
        Set<String> edgeInfo = new HashSet<>();
        for (var edge : g.edges()) {
            assert edge.from() != null;
            String ef = edge.from().toString();
            edgeInfo.add(ef.substring(0, ef.indexOf("{")) + " -> " + ef.substring(ef.indexOf(">")+1, ef.indexOf(":")));
        }
        edgeInfo.add("Number of Edges: "+ getNumberOfEdges());
        return edgeInfo;

    }

    public static String toGraphString() {
        return "Nodes: " + getNodeLabels() + "\nEdges: " + getEdgeInfo();
    }

    public static void outputGraph(String filePath) throws IOException {
        parseGraph(filePath);
        System.out.println(g);
    }

    // Feature 2: Adding nodes from the imported graph

    public static void addNode(String label) {
        if(!getNodeLabels().contains(label)){
            g.nodes().add(mutNode(label));
        }
    }

    // Feature 3: Adding edges from the imported graph

    public static void addEdge(String srcLabel, String dstLabel){
        g.add(mutNode(srcLabel).addLink(dstLabel));
    }



    public static void addNodes(String[] labels) {
        for (String label : labels) {
            addNode(label);
        }
    }



    public static void main(String[] args) {

    }

}
