package code;

import java.util.ArrayList;

//import static code.Bottle.printBottles;

public class WaterSortSearch extends GenericSearch {
    static String grid0 = "3;" + "4;" + "r,y,r,y;" + "y,r,y,r;" + "e,e,e,e;";
    static String grid1 = "5;" + "4;" + "b,y,r,b;" + "b,y,r,r;" + "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
    static String grid2 = "5;" + "4;" + "b,r,o,b;" + "b,r,o,o;" + "r,o,b,r;" + "e,e,e,e;" + "e,e,e,e;";
    static String grid3 = "6;" + "4;" + "g,g,g,r;" + "g,y,r,o;" + "o,r,o,y;" + "y,o,y,b;" + "r,b,b,b;" + "e,e,e,e;";
    static String grid4 = "6;" + "3;" + "r,r,y;" + "b,y,r;" + "y,b,g;" + "g,g,b;" + "e,e,e;" + "e,e,e;";


    public WaterSortSearch(String initialState, ArrayList<String> setOfActions) {
        super(initialState);
    }


    public static String solve(String initialState, String strategy, boolean visualize) {
        String solution = "TEST";
        GenericSearch search = new GenericSearch(initialState);
        solution = search.generalSearch(initialState, strategy);
        return solution;
    }

    public static void main(String[] args) {
        System.out.println(solve(grid4, "ID", true));
    }

}
