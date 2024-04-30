package frsf.cidisi.faia.examples.search.amongus;

import frsf.cidisi.faia.agent.Perception;

import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImpostorAgent extends SearchBasedAgent {

  public ImpostorAgent() {

  }

  /**
   * This method is executed by the simulator to ask the agent for an action.
   */
  @Override
  public Action selectAction() {

    // Create the search strategy
    DepthFirstSearch strategy = new DepthFirstSearch();

    // Create a Search object with the strategy
    Search searchSolver = new Search(strategy);

    /*
     * Generate an XML file with the search tree. It can also be generated
     * in other formats like PDF with PDF_TREE
     */
    searchSolver.setVisibleTree(Search.EFAIA_TREE);

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