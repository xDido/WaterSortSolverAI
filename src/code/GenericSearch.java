package code;

import java.util.*;


public class GenericSearch {

    protected String initialState;

    HashSet<Node> expandedNodes = new HashSet<>();
    protected ArrayList<ArrayList<Bottle>> stateSpace;
    int nodeCount;


    public GenericSearch(String initialState) {
        this.initialState = initialState;

        nodeCount = 0;
    }

    public String generalSearch(String initialState, String strategy) {
        Node initialNode = new Node(initialStateHandler(initialState), null, null, 0, 0);
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(initialNode);
        while (!nodes.isEmpty()) {
            System.out.println("went to while");
            Node currentNode = nodes.getFirst();
            if (currentNode.isGoal()) {
                return formatSolution(currentNode);
            } else {
                switch (strategy) {
                    case "BF":
                        nodes = BFS(nodes);
                        break;
                    case "DF":
                        System.out.println("went to dfs");
                        nodes = DFS(nodes);
                        break;
                    case "ID":
                        break;
                    case "UC":
                        break;
                    case "GR":
                        break;
                    case "AS":
                        break;
                    default:
                        break;
                }
            }
        }
        return "NOSOLUTION";

    }

    private ArrayList<Node> BFS(ArrayList<Node> nodes) {

        Node firstNode = nodes.removeFirst();
        if (!expandedNodes.contains(firstNode)) {
            expandedNodes.add(firstNode);
            ArrayList<Node> children = firstNode.expandChildren();
            nodeCount += children.size();
            nodes.addAll(children);
        }
        return nodes;
    }

    private ArrayList<Node> DFS(ArrayList<Node> nodes) {
        //INFINITE LOOP
        Node firstNode = nodes.removeFirst();
        if (!expandedNodes.contains(firstNode)) {
            expandedNodes.add(firstNode);
            ArrayList<Node> children = firstNode.expandChildren();
            nodeCount += children.size();
            System.out.println("expanded nodes: " + children.size());
            for (int i = children.size() - 1; i >= 0; i--) {
                nodes.addFirst(children.get(i));
            }
        }
        return nodes;
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

        StringBuilder plan = new StringBuilder();
        for (int i = 1; i < path.size(); i++) {
            plan.append(path.get(i).getOperator());
            if (i < path.size() - 1) {
                plan.append(",");
            }
        }
        return plan.toString() + ";" + pathCost + ";" + nodeCount;
    }

    static ArrayList<Bottle> initialStateHandler(String initialState) {
        StringTokenizer st = new StringTokenizer(initialState, ";");
        int numberOfBottles = Integer.parseInt(st.nextToken());
        int bottleCapacity = Integer.parseInt(st.nextToken());
        ArrayList<Bottle> bottles = new ArrayList<>();

        for (int i = 0; i < numberOfBottles; i++) {
            Bottle bottle = new Bottle(bottleCapacity);
            StringTokenizer layers = new StringTokenizer(st.nextToken(), ",");
            while (layers.hasMoreTokens()) {
                String layer = layers.nextToken();
                switch (layer) {
                    case "r":
                        bottle.getLayers().addFirst(Color.RED);
                        break;
                    case "g":
                        bottle.getLayers().addFirst(Color.GREEN);
                        break;
                    case "b":
                        bottle.getLayers().addFirst(Color.BLUE);
                        break;
                    case "y":
                        bottle.getLayers().addFirst(Color.YELLOW);
                        break;
                    case "o":
                        bottle.getLayers().addFirst(Color.ORANGE);
                        break;
                    default:
                        break;
                }
            }
            bottles.add(bottle);
        }
        return bottles;
    }




}
