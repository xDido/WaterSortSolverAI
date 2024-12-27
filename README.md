
# Water Sort Puzzle Solver  
---

## Table of Contents  
1. [Project Overview](#project-overview)  
2. [Key Features](#key-features)  
3. [Usage Instructions](#usage-instructions)  
4. [Implementation Details](#implementation-details)  
5. [Class Structure](#class-structure)  
6. [Search Strategies](#search-strategies)  
7. [Performance Metrics](#performance-metrics)  

---

## Project Overview  
This project implements an AI-based search agent to solve the **Water Sort Puzzle**, a problem where liquids of different colors in various bottles must be sorted so that each bottle contains only one color. The solution leverages various search strategies to find an optimal plan to achieve this goal.

### Problem Description:  
1. **Bottles:** Each bottle has a maximum capacity and a unique identifier.  
2. **Actions:** The only permissible action is `pour(i, j)` which pours liquid from bottle `i` to bottle `j`, adhering to specific constraints.  
3. **Objective:** Sort the liquids so that each bottle contains uniform layers of a single color.  

### Input Format:  
A string defines the initial state as:  
```
numberOfBottles;bottleCapacity;color0,1,color0,2,...;color1,1,color1,2,...;
```

Example:  
```  
"5;4;b,y,r,b;b,y,r,r;y,r,b,y;e,e,e,e;e,e,e,e;"
```

---

## Key Features  
- Implements six search strategies: BFS, DFS, ID, UCS, Greedy Search (with 2 heuristics), and A* Search (with 2 admissible heuristics).  
- Tracks and reports metrics: runtime, nodes expanded, memory usage, and CPU utilization.  
- Visualizes the solution process via console output (optional).  
- Validates the solution's correctness and optimality (for applicable strategies).  

---

## Usage Instructions  

1. **Clone the Repository:**  
   ```bash  
   git clone <repository-url>  
   cd WaterSortPuzzleSolver  
   ```  

2. **Compile the Code:**  
   Ensure you have Java installed. Then, compile the project:  
   ```bash  
   javac -d bin src/code/*.java  
   ```  

3. **Run the Program:**  
   Use the `solve` method in the `WaterSortSearch` class to solve a specific puzzle instance:  
   ```bash  
   java -cp bin code.WaterSortSearch  
   ```  

4. **Parameters for `solve`:**  
   - **initialState:** String defining the initial puzzle setup.  
   - **strategy:** Search strategy to use (`BF`, `DF`, `ID`, `UC`, `GR1`, `GR2`, `AS1`, or `AS2`).  
   - **visualize:** Boolean to print step-by-step solution states.  

   Example:  
   ```java  
   String result = WaterSortSearch.solve("5;4;b,y,r,b;b,y,r,r;y,r,b,y;e,e,e,e;e,e,e,e;", "BF", true);  
   ```  

---

## Implementation Details  
### Actions:  
- **Pour:** Transfers liquid layers from one bottle to another. Ensures constraints on bottle capacity, color continuity, and emptiness are respected.  

### Optimality:  
The goal is to minimize the total liquid layers poured across all actions.  

---

## Class Structure  

1. **`GenericSearch`**  
   - Implements a generic search problem framework, including state management and frontier expansion.  

2. **`WaterSortSearch`**  
   - Subclass of `GenericSearch`, specializing in the water-sort puzzle.  
   - Implements the `solve` method to orchestrate the solution process.  

3. **`Node`**  
   - Represents a node in the search tree, encapsulating state, parent, action, path cost, and heuristic values.  

Additional utility classes may be implemented for modularity and clarity.  

---

## Search Strategies  

1. **Breadth-First Search (BF):** Explores all nodes at a given depth before proceeding deeper.  
2. **Depth-First Search (DF):** Explores as deep as possible along each branch before backtracking.  
3. **Iterative Deepening Search (ID):** Combines the benefits of BFS and DFS by incrementally deepening the search.  
4. **Uniform Cost Search (UC):** Expands the node with the lowest cumulative cost.  
5. **Greedy Search (GR1, GR2):** Uses heuristics to prioritize nodes likely leading to the goal.  
6. **AStar Search (AS1, AS2):** Combines path cost and heuristic values to find an optimal path.  

---

## Performance Metrics  

Each strategy is evaluated on:  
- **Completeness:** Does it always find a solution if one exists?  
- **Optimality:** Does it guarantee the optimal solution?  
- **Runtime:** Time taken to solve the puzzle.  
- **Memory Usage:** RAM consumed during execution.  
- **CPU Utilization:** Processor usage during execution.  
- **Nodes Expanded:** Number of nodes explored to reach the solution.  

---
