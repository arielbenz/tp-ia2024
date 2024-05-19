package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.examples.search.impostor.GameStructure;
import frsf.cidisi.faia.examples.search.impostor.ImpostorAgentState;
import frsf.cidisi.faia.examples.search.impostor.ImpostorEnvironmentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoRight extends SearchAction {

  /**
   * This method updates a tree node state when the search process is running.
   * It does not updates the real world state.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {
    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    // Increase the action cost count
    impostorState.increaseActionCost(GameStructure.ACTION_MOVE_COST);

    int newPosition = impostorState.getImpostorOrientation(GameStructure.RIGHT);
    boolean isNotWall = newPosition != GameStructure.WALL;

    if (impostorState.getEnergy() > 0 && isNotWall) {

      int[] newOrientation = GameStructure.getRowShipPosition(newPosition);

      impostorState.setImpostorOrientation(newOrientation);
      impostorState.setPosition(newPosition);
      impostorState.consumeEnergy();

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

    // Increase the action cost count
    impostorState.increaseActionCost(GameStructure.ACTION_MOVE_COST);

    // Get new position value
    int newPosition = impostorState.getImpostorOrientation(GameStructure.RIGHT);

    // Ask if it possible to move RIGHT based on pre-requisits
    if (environmentState.getAgentEnergy() > 0 && newPosition != GameStructure.WALL) {

      // Update orientation agent array
      int[] newOrientation = GameStructure.getRowShipPosition(newPosition);
      impostorState.setImpostorOrientation(newOrientation);

      // Update agent and environment energy
      impostorState.consumeEnergy();
      environmentState.consumeEnergy();

      // Update agent state and environment state position
      impostorState.setPosition(newPosition);
      environmentState.setAgentPosition(newPosition);
    }

    return environmentState;
  }

  /**
   * This method returns the action cost.
   */
  @Override
  public Double getCost() {
    return new Double(GameStructure.ACTION_MOVE_COST);
  }

  /**
   * This method is not important for a search based agent, but is essensial
   * when creating a calculus based one.
   */
  @Override
  public String toString() {
    return "**GO RIGHT**";
  }
}
