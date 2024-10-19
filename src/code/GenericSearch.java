package code;

import java.util.*;

public class GenericSearch {

    protected String initialState;

    HashSet<Node> expandedNodes = new HashSet<>();
    int nodeCount;

    public GenericSearch(String initialState) {
        this.initialState = initialState;

        nodeCount = 0;
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
        return plan + ";" + pathCost + ";" + nodeCount;
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

    public String generalSearch(String initialState, String strategy) {
        Node initialNode = new Node(initialStateHandler(initialState), null, null, 0, 0);
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(initialNode);

        int depth = 0;
        while (!nodes.isEmpty()) {
            Node currentNode = nodes.getFirst();
            if (currentNode.isGoal()) {
                return formatSolution(currentNode);
            } else {
                switch (strategy) {
                    case "BF":
                        nodes = BFS(nodes);
                        break;
                    case "DF":
                        nodes = DFS(nodes);
                        break;
                    case "ID":
                        nodes = IDS(nodes, depth);
                        break;
                    case "UC":
                        nodes = UCS(nodes);
                        break;
                    case "GR1":
                        nodes = Greedy(nodes, '1');
                        break;
                    case "GR2":
                        nodes = Greedy(nodes, '2');
                        break;
                    case "AS1":
                        nodes = AStar(nodes, '1');
                        break;
                    case "AS2":
                        nodes = AStar(nodes, '2');
                        break;
                    default:
                        return "INVALID STRATEGY";
                }
            }
            depth = nodes.getFirst().getDepth();
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

            for (Node child : children) {
                if (!expandedNodes.contains(child)) {
                    nodes.addLast(child);
                }
            }
        }
        return nodes;
    }

    private ArrayList<Node> DFS(ArrayList<Node> nodes) {
        Node firstNode = nodes.removeFirst();

        if (!expandedNodes.contains(firstNode)) {
            expandedNodes.add(firstNode);

            ArrayList<Node> children = firstNode.expandChildren();
            nodeCount += children.size();

            for (Node child : children) {
                if (!expandedNodes.contains(child)) {
                    nodes.addFirst(child);
                }
            }
        } else {
            System.out.println("Skipping already expanded node: " + firstNode);
        }
        return nodes;
    }


    public ArrayList<Node> IDS(ArrayList<Node> nodes, int depth) {
        Node firstNode = nodes.removeFirst();

        if (!expandedNodes.contains(firstNode) && firstNode.getDepth() <= depth) {
            expandedNodes.add(firstNode);

            ArrayList<Node> children = firstNode.expandChildren();
            nodeCount += children.size();

            for (Node child : children) {
                if (!expandedNodes.contains(child)) {
                    nodes.addFirst(child);
                }
            }
        }
        return nodes;
    }

    public ArrayList<Node> UCS(ArrayList<Node> nodes) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getPathCost));
        priorityQueue.addAll(nodes);

        Node currentNode = priorityQueue.poll();
        if (!expandedNodes.contains(currentNode)) {
            expandedNodes.add(currentNode);
            ArrayList<Node> children = currentNode.expandChildren();
            nodeCount += children.size();
            for (Node child : children) {
                if (!expandedNodes.contains(child)) {
                    priorityQueue.add(child); // Add children to the priority queue
                }
            }
        }
        return new ArrayList<>(priorityQueue);
    }

    public ArrayList<Node> Greedy(ArrayList<Node> nodes, char heuristic) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> {
            int node1Heuristic = (heuristic == '1') ? n1.getFirstHeuristic() : n1.getSecondHeuristic();
            int node2Heuristic = (heuristic == '1') ? n2.getFirstHeuristic() : n2.getSecondHeuristic();
            return Integer.compare(node1Heuristic, node2Heuristic);

        });
        priorityQueue.addAll(nodes);
        Node currentNode = priorityQueue.poll();
        if (!expandedNodes.contains(currentNode)) {
            expandedNodes.add(currentNode);
            ArrayList<Node> children = currentNode.expandChildren();
            nodeCount += children.size();
            for (Node child : children) {
                if (!expandedNodes.contains(child)) {
                    priorityQueue.add(child);
                }
            }
        }
        return new ArrayList<>(priorityQueue);
    }

    public ArrayList<Node> AStar(ArrayList<Node> nodes, char heuristic) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> {
            int node1Heuristic = (heuristic == '1') ? n1.getFirstHeuristic() : n1.getSecondHeuristic();
            int node2Heuristic = (heuristic == '1') ? n2.getFirstHeuristic() : n2.getSecondHeuristic();
                return Integer.compare(node1Heuristic + n1.getPathCost(), node2Heuristic + n2.getPathCost());

        });
        priorityQueue.addAll(nodes);
        Node currentNode = priorityQueue.poll();
        if (!expandedNodes.contains(currentNode)) {
            expandedNodes.add(currentNode);
            ArrayList<Node> children = currentNode.expandChildren();
            nodeCount += children.size();
            for (Node child : children) {
                if (!expandedNodes.contains(child)) {
                    priorityQueue.add(child);
                }
            }
        }
        return new ArrayList<>(priorityQueue);
    }

}
