package code;

import code.Algorithms.BFS;
import code.Algorithms.Node.Node;
import code.Tools.Bottle;
import code.Tools.Colors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//import static code.Tools.Bottle.printBottles;

public class WaterSortSearch extends GenericSearch {
    static String grid0 = "3;" + "4;" + "r,y,r,y;" + "y,r,y,r;" + "e,e,e,e;";
    static String grid1 = "5;" + "4;" + "b,y,r,b;" + "b,y,r,r;" + "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
    static String grid2 = "5;" + "4;" + "b,r,o,b;" + "b,r,o,o;" + "r,o,b,r;" + "e,e,e,e;" + "e,e,e,e;";
    static String grid3 = "6;" + "4;" + "g,g,g,r;" + "g,y,r,o;" + "o,r,o,y;" + "y,o,y,b;" + "r,b,b,b;" + "e,e,e,e;";
    String grid4 = "6;" + "3;" + "r,r,y;" + "b,y,r;" + "y,b,g;" + "g,g,b;" + "e,e,e;" + "e,e,e;";


    public WaterSortSearch(ArrayList<Bottle> initialState, ArrayList<String> setOfActions) {
        super(initialState, setOfActions);
    }

    private static ArrayList<Bottle> initialStateHandler(String initialState) {
        StringTokenizer st = new StringTokenizer(initialState, ";");
        int numberOfBottles = Integer.parseInt(st.nextToken());
        int bottleCapacity = Integer.parseInt(st.nextToken());
        ArrayList<Bottle> bottles = new ArrayList<>();

        for (int i = 0; i < numberOfBottles; i++) {
            Bottle bottle = new Bottle(bottleCapacity);
            StringTokenizer layers = new StringTokenizer(st.nextToken(), ",");
            ArrayList<Colors> bottleLayers = new ArrayList<>();
            while (layers.hasMoreTokens()) {
                String layer = layers.nextToken();
                switch (layer) {
                    case "r":
                        bottle.addLayer(Colors.RED);
                        break;
                    case "g":
                        bottle.addLayer(Colors.GREEN);
                        break;
                    case "b":
                        bottle.addLayer(Colors.BLUE);
                        break;
                    case "y":
                        bottle.addLayer(Colors.YELLOW);
                        break;
                    case "o":
                        bottle.addLayer(Colors.ORANGE);
                        break;
                    default:
                        break;
                }
            }
            bottles.add(bottle);
        }
        return bottles;
    }

    public boolean isPourable(Bottle source, Bottle destination) {
        return !source.isEmpty() && !destination.isFull() && source.getLayers().peek() == destination.getLayers().peek()
                || !source.isEmpty() && destination.isEmpty();
    }

    private int countMatchingTopLayers(Bottle source, Colors color) {
        int count = 0;

        ArrayDeque<Colors> tempLayers = new ArrayDeque<>(source.getLayers());

        while (!tempLayers.isEmpty() && tempLayers.peek().equals(color)) {
            count++;
            tempLayers.pop();
        }

        return count;
    }

    public int Pour(Bottle source, Bottle destination) {
        if (isPourable(source, destination)) {
            Colors topLayerColor = source.getTopLayer();
            int pourCount = Math.min(destination.getRemainingSpace(), countMatchingTopLayers(source, topLayerColor));
            for (int i = 0; i < pourCount; i++) {
                destination.addLayer(source.removeLayer());
            }
            return pourCount;
        }
        return 0;
    }

    private static ArrayList<Bottle> deepCopyState(ArrayList<Bottle> state) {
        ArrayList<Bottle> newState = new ArrayList<>();
        for (Bottle bottle : state) {
            newState.add(bottle.deepCopy());
        }
        return newState;
    }


    public List<Node> generateChildren(Node parent) {
        List<Node> children = new ArrayList<>();
        ArrayList<Bottle> parentState = parent.getState();

        for (int i = 0; i < parentState.size(); i++) {
            for (int j = 0; j < parentState.size(); j++) {
                if (i != j) {
                    Bottle sourceBottle = parentState.get(i);
                    Bottle destinationBottle = parentState.get(j);
                    if (isPourable(sourceBottle, destinationBottle)) {
                        ArrayList<Bottle> nextState = deepCopyState(parentState);
                        int pouredLayers = Pour(nextState.get(i), nextState.get(j));
                        if (pouredLayers > 0) {
                            String operator = "pour_" + i + "_" + j;
                            int childPathCost = parent.getPathCost() + pouredLayers;
                            Node childNode = new Node(nextState, parent, operator, parent.getDepth() + 1, childPathCost);
                            children.add(childNode);
                        }
                    }
                }
            }
        }
        return children;
    }
    @Override
    public boolean isGoal(ArrayList<Bottle> state) {
        return state.stream().allMatch(bottle -> bottle.isEmpty() || bottle.isHomogeneous());
    }

    @Override
    protected int pathCost(String setOfActions) {
        int cost = 0;
        String[] actions = setOfActions.split(",");
        for (String action : actions) {
            String[] parts = action.split("_");
            int sourceIndex = Integer.parseInt(parts[1]);
            int destIndex = Integer.parseInt(parts[2]);
            cost += Math.min(initialState.get(sourceIndex).getCurrentSize(),
                    initialState.get(destIndex).getRemainingSpace());
        }
        return cost;
    }

    public static String solve(String initialState, String strategy, boolean visualize) {
        String solution = "";
        ArrayList<Bottle> initialStateList = initialStateHandler(initialState);
        if (strategy.equals("BF")) {
            BFS bfs = new BFS(initialStateList, new WaterSortSearch(initialStateList, new ArrayList<>()));
            solution = bfs.search();
        }

        return solution;
    }

    public static void main(String[] args) {
//        Node node = new Node(initialStateHandler(grid1), null, null, 0, 0);
//        WaterSortSearch waterSortSearch = new WaterSortSearch(initialStateHandler(grid1), new ArrayList<>());
//        Bottle bottle1 = node.getState().get(0);
//        Bottle bottle2 = node.getState().get(1);
//        Bottle bottle3 = node.getState().get(2);
//        Bottle bottle4 = node.getState().get(3);
//        Bottle bottle5 = node.getState().get(4);
//        waterSortSearch.Pour(bottle1, bottle4);
//         // waterSortSearch.Pour(bottle1, bottle5);
//       // waterSortSearch.Pour(bottle2, bottle4);
//        // waterSortSearch.Pour(bottle1, bottle2);
//        //waterSortSearch.Pour(bottle3, bottle5);
//        //waterSortSearch.Pour(bottle3, bottle2);
//        // waterSortSearch.Pour(bottle3, bottle1);
//        ArrayList<Bottle> bottles = node.getState();
//        printBottles(bottles);
//        List<Node> children = waterSortSearch.generateChildren(node);
//        for (Node child : children) {
//            //System.out.println("Child Node: ");
//            //  printBottles(child.getState());
//        }

        System.out.println(solve(grid1, "BF", true));


    }


}
