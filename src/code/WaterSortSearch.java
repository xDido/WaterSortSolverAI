package code;

import code.Tools.Bottle;
import code.Tools.Colors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WaterSortSearch extends GenericSearch {
    String grid0 = "3;" + "4;" + "r,y,r,y;" + "y,r,y,r;" + "e,e,e,e;";
    String grid1 = "5;" + "4;" + "b,y,r,b;" + "b,y,r,r;" + "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
    String grid2 = "5;" + "4;" + "b,r,o,b;" + "b,r,o,o;" + "r,o,b,r;" + "e,e,e,e;" + "e,e,e,e;";
    String grid3 = "6;" + "4;" + "g,g,g,r;" + "g,y,r,o;" + "o,r,o,y;" + "y,o,y,b;" + "r,b,b,b;" + "e,e,e,e;";
    String grid4 = "6;" + "3;" + "r,r,y;" + "b,y,r;" + "y,b,g;" + "g,g,b;" + "e,e,e;" + "e,e,e;";

    public WaterSortSearch(ArrayList<Bottle> initialState, ArrayList<String> setOfActions, ArrayList<ArrayDeque<Object>> goalTest) {
        super(initialState, setOfActions, goalTest);
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
                    case "e":
                        bottle.addLayer(Colors.EMPTY);
                        break;
                    default:
                        break;
                }
            }
            bottles.add(bottle);
        }
        return bottles;
    }

    public static String solve(String initialState, String strategy, boolean visualize) {
        ArrayList<Bottle> initialStateList = initialStateHandler(initialState);

        return " LESA ";
    }

}
