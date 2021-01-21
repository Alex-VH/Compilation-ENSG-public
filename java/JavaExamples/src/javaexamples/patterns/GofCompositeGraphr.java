/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamples.patterns;

import fr.cyann.graphr.Graphr;
import fr.cyann.graphr.TreeGraph;
import static fr.cyann.graphr.declarative.Formula.*;
import static javaexamples.utils.UiUtils.*;

/**
 *
 * @author cyann
 */
public class GofCompositeGraphr {

    public static void main(String[] args) throws InterruptedException {

        Graphr graph = BuildPanel();

        TreeGraph tree = new TreeGraph(number(0), number(0), frameW(), frameH());
        graph.addGraph(tree);

        TreeGraph.TreeNode root = tree.createRootNode("Root");
        tree.createChildNode(root, "1-1");

        TreeGraph.TreeNode node2 = tree.createChildNode(root, "1-2");

        tree.createChildNode(node2, "2-1");
        TreeGraph.TreeNode node4 = tree.createChildNode(node2, "2-2");

        tree.createChildNode(node4, "3-1");
        tree.createChildNode(node4, "3-2");

    }

}
