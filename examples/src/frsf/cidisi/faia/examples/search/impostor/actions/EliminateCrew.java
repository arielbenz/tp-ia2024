package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.GameStructure;
import frsf.cidisi.faia.examples.search.impostor.ImpostorAgentState;
import frsf.cidisi.faia.examples.search.impostor.ImpostorEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class EliminateCrew extends SearchAction {

  /**
   * This method updates a tree node state when the search process is running.
   * It does not updates the real world state.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {

    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    int pos = impostorState.getPosition();

    /*
     * The 'EliminateCrew' action can be selected only if there is a crew member in
     * the current position. Otherwise return 'null'.
     */
    if ((impostorState.getCrewInRoom() > 0) && (impostorState.getEnergy() > 0)) {

      // Increase the action cost count
      impostorState.increaseActionCost(GameStructure.ACTION_ELIMINATE_COST);

      impostorState.setCrewInRoom();
      impostorState.consumeEnergy();

      System.out
          .println("\n-- Eliminate Action - Agent pos: " + pos + "  -remaining energy: " + impostorState.getEnergy());
      return impostorState;
    }

    return null;
  }

  /**
   * This method updates the agent state and the real world state.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {

    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;
    ImpostorAgentState impostorState = ((ImpostorAgentState) ast);

    if ((impostorState.getCrewInRoom() > 0) && (environmentState.getAgentEnergy() > 0)) {

      // Increase the action cost count
      impostorState.increaseActionCost(GameStructure.ACTION_ELIMINATE_COST);

      impostorState.setCrewInRoom();
      environmentState.eliminateCrew();

      // Update agent and environment energy
      impostorState.consumeEnergy();
      environmentState.consumeAgentEnergy();

      return environmentState;
    }

    return null;
  }

  /**
   * This method returns the action cost.
   */
  @Override
  public Double getCost() {
    return new Double(1);
  }

  /**
   * This method is not important for a search based agent, but is essensial
   * when creating a calculus based one.
   */
  @Override
  public String toString() {
    return "Eliminate Crew";
  }
}
