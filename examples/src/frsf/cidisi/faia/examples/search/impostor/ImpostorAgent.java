package frsf.cidisi.faia.examples.search.impostor;
import frsf.cidisi.faia.examples.search.impostor.Heuristic;

import frsf.cidisi.faia.agent.Perception;

import frsf.cidisi.faia.examples.search.impostor.actions.EliminateCrew;
import frsf.cidisi.faia.examples.search.impostor.actions.Sabotage;
import frsf.cidisi.faia.examples.search.impostor.actions.GoLeft;
import frsf.cidisi.faia.examples.search.impostor.actions.GoUp;
import frsf.cidisi.faia.examples.search.impostor.actions.GoRight;
import frsf.cidisi.faia.examples.search.impostor.actions.GoDown;

import java.util.Vector;

import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImpostorAgent extends SearchBasedAgent {

  public ImpostorAgent() {
    // The Impostor Goal
    ImpostorGoal goal = new ImpostorGoal();

    // The Impostor Agent State
    ImpostorAgentState impostorState = new ImpostorAgentState();
    this.setAgentState(impostorState);

    // Create the operators
    Vector<SearchAction> operators = new Vector<SearchAction>();
    operators.addElement(new EliminateCrew());
    operators.addElement(new Sabotage());
    operators.addElement(new GoUp());
    operators.addElement(new GoDown());
    operators.addElement(new GoLeft());
    operators.addElement(new GoRight());

    // Create the Problem which the Impostor will resolve
    Problem problem = new Problem(goal, impostorState, operators);
    this.setProblem(problem);
  }

  /**
   * This method is executed by the simulator to ask the agent for an action.
   */
  @Override
  public Action selectAction() {

    // Create a Search object with the strategy //profundidad o amplitud
    // Search searchSolver = new Search(GameStructure.getSearchStrategy());

    // Uniform Cost:
    //IStepCostFunction costFunction = new CostFunction();
    //UniformCostSearch strategy = new UniformCostSearch(costFunction);

    /*
    * A Star (A*) Search:
    * IStepCostFunction cost = new CostFunction();
    * IEstimatedCostFunction heuristic = new Heuristic();
    * AStarSearch strategy = new AStarSearch(cost, heuristic);
    * 
     */

    // Greedy(AVARAST)  Search:
    IEstimatedCostFunction heuristic = new Heuristic();
    GreedySearch strategy = new GreedySearch(heuristic);
    

    // Create a Search object with the strategy
    Search searchSolver = new Search(strategy);

    /*
     * Generate an XML file with the search tree. It can also be generated
     * in other formats like PDF with PDF_TREE
     */
    searchSolver.setVisibleTree(GameStructure.VISIBLE_TREE);

    // Set the Search searchSolver.
    this.setSolver(searchSolver);

    // Ask the solver for the best action
    Action selectedAction = null;
    try {
      selectedAction = this.getSolver().solve(new Object[] { this.getProblem() });
    } catch (Exception ex) {
      Logger.getLogger(ImpostorAgent.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Return the selected action
    return selectedAction;
  }

  /**
   * This method is executed by the simulator to give the agent a perception.
   * Then it updates its state.
   * 
   * @param p
   */
  @Override
  public void see(Perception p) {
    this.getAgentState().updateState(p);
  }
}
