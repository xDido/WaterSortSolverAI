package code.Algorithms;

import code.Algorithms.Node.Node;
import code.Tools.Bottle;
import code.Tools.Colors;
import code.WaterSortSearch;

import java.util.*;

//import static code.Tools.Bottle.printBottles;


public class BFS {
    private Node initialNode;
    private WaterSortSearch waterSortSearch;
    private int nodeCount;

    public BFS(ArrayList<Bottle> initialState, WaterSortSearch waterSortSearch) {
        this.initialNode = new Node(initialState, null, null, 0, 0);
        this.waterSortSearch = waterSortSearch;
        this.nodeCount = 0;
    }

    public String search() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(initialNode);
        Set<String> visitedStates = new HashSet<>();
        int i = 0;
        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();
            System.out.println("Node " + i++);
            System.out.println("depth = " + currentNode.getDepth());
            System.out.println("Current Node: ");
           // printBottles(currentNode.getState());

            nodeCount++;
            String stateString = stateToString(currentNode.getState());
            if (visitedStates.contains(stateString)) {
                continue;
            }
            if (waterSortSearch.isGoal(currentNode.getState())) {
                return formatSolution(currentNode);
            }
            visitedStates.add(stateString);

            List<Node> children = waterSortSearch.generateChildren(currentNode);
            queue.addAll(children);

        }

        return "NOSOLUTION";
    }

    private String stateToString(ArrayList<Bottle> state) {
        StringBuilder sb = new StringBuilder();
        for (Bottle bottle : state) {
            List<Colors> layers = new ArrayList<>(bottle.getLayers());
            for (Colors color : layers) {
                sb.append(color).append(",");
            }
            sb.append(";");
        }
        return sb.toString();
    }

    private String formatSolution(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node current = goalNode;
        int pathCost = goalNode.getPathCost();

        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);

        // Build the plan (sequence of actions)
        StringBuilder plan = new StringBuilder();
        for (int i = 1; i < path.size(); i++) {
            plan.append(path.get(i).getOperator());
            if (i < path.size() - 1) {
                plan.append(",");
            }
        }
        return plan.toString() + ";" + pathCost + ";" + nodeCount;
    }

}