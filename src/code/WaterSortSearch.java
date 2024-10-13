package code;

import code.Algorithms.BFS;
import code.Tools.Bottle;
import code.Tools.Colors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static code.Tools.Bottle.printBottles;

public class WaterSortSearch extends GenericSearch {
    static String grid0 = "3;" + "4;" + "r,y,r,y;" + "y,r,y,r;" + "e,e,e,e;";
    static String grid1 = "5;" + "4;" + "b,y,r,b;" + "b,y,r,r;" + "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
    String grid2 = "5;" + "4;" + "b,r,o,b;" + "b,r,o,o;" + "r,o,b,r;" + "e,e,e,e;" + "e,e,e,e;";
    String grid3 = "6;" + "4;" + "g,g,g,r;" + "g,y,r,o;" + "o,r,o,y;" + "y,o,y,b;" + "r,b,b,b;" + "e,e,e,e;";
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

    public static boolean isPourable(Bottle source, Bottle destination) {
        return !source.isEmpty() && !destination.isFull() && source.getLayers().peek() == destination.getLayers().peek()
                || !source.isEmpty() && !destination.isFull() && destination.isEmpty();
    }

    private static int countMatchingTopLayers(Bottle source, Colors color) {
        int count = 0;

        ArrayDeque<Colors> tempLayers = new ArrayDeque<>(source.getLayers());

        while (!tempLayers.isEmpty() && tempLayers.peek().equals(color)) {
            count++;
            tempLayers.pop();
        }

        return count;
    }

    public static void Pour(Bottle source, Bottle destination) {

        if (isPourable(source, destination)) {

            Colors topLayerColor = source.getTopLayer();

            int pourCount = Math.min(destination.getRemainingSpace(), countMatchingTopLayers(source, topLayerColor));

            for (int i = 0; i < pourCount; i++) {

                destination.addLayer(source.removeLayer());
            }
        }
    }


    @Override
    public  boolean isGoal(ArrayList<Bottle> state) {
        for (Bottle bottle : state) {
            if (!bottle.isEmpty()) {
                ArrayDeque<Colors> layers = bottle.getLayers();
                for (Colors layer : layers) {
                    if (layer != layers.peek()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    protected int pathCost(String setOfActions) {
        return 0;
    }

    public static String solve(String initialState, String strategy, boolean visualize) {
        ArrayList<Bottle> initialStateList = initialStateHandler(initialState);
        if (strategy.equals("BFS")) {
            System.out.println("**************beforeBFS********************");
            printBottles(initialStateList);
            System.out.println("*************************************************");
            BFS bfs = new BFS(initialStateList, new WaterSortSearch(initialStateList, new ArrayList<>()));
            bfs.search();
        }


        return " LESA ";
    }

    public static void main(String[] args) {
        ArrayList<Bottle> initialState = new ArrayList<>();
        Bottle bottle1 = new Bottle(4, new ArrayDeque<>());
        bottle1.addLayer(Colors.BLUE);
        bottle1.addLayer(Colors.YELLOW);
        bottle1.addLayer(Colors.RED);
        bottle1.addLayer(Colors.BLUE);
        Bottle bottle2 = new Bottle(4, new ArrayDeque<>());
        bottle2.addLayer(Colors.BLUE);
        bottle2.addLayer(Colors.YELLOW);
        bottle2.addLayer(Colors.RED);
        bottle2.addLayer(Colors.RED);
        Bottle bottle3 = new Bottle(4, new ArrayDeque<>());
        bottle3.addLayer(Colors.YELLOW);
        bottle3.addLayer(Colors.RED);
        bottle3.addLayer(Colors.BLUE);
        bottle3.addLayer(Colors.YELLOW);
        Bottle bottle4 = new Bottle(4, new ArrayDeque<>());
        Bottle bottle5 = new Bottle(4, new ArrayDeque<>());
        initialState.add(bottle1);
        initialState.add(bottle2);

        initialState.add(bottle3);
        initialState.add(bottle4);
        initialState.add(bottle5);
        WaterSortSearch waterSortSearch = new WaterSortSearch(initialState, new ArrayList<>());
        System.out.println( waterSortSearch.isGoal(initialState));
        solve(grid1, "BFS", true);
    }

}
