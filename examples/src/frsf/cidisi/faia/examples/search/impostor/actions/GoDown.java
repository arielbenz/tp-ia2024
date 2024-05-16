package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.*;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoDown extends SearchAction {

  /**
   * This method updates a tree node state when the search process is running.
   * It does not updates the real world state.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {
    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    // Increase the visited cells count
    impostorState.increaseActionCost(GameStructure.ACTION_MOVE_COST);

    int oldPosition = impostorState.getPosition();
    int newPosition = impostorState.getImpostorOrientation(GameStructure.DOWN);

    /* The agent can always go down */
    if (impostorState.getEnergy() > 0 && newPosition != GameStructure.WALL) {

      int[] newOrientation = GameStructure.getRowShipPosition(newPosition);

      impostorState.setImpostorOrientation(newOrientation);
      impostorState.setPosition(newPosition);
      impostorState.setEnergy(impostorState.getEnergy() - GameStructure.Q_CONSUME_ENERGY);

      // System.out.printf("\nImpostor position: " + oldPosition + " - SI DOWN");

      return impostorState;
    }

    // System.out.printf("\nImpostor position: " + impostorState.getPosition() + " - NO DOWN");

    return null;
  }

  /**
   * This method updates the agent state and the real world state.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;
    ImpostorAgentState impostorState = ((ImpostorAgentState) ast);

    // Increase the visited cells count
    impostorState.increaseActionCost(GameStructure.ACTION_MOVE_COST);

    // Get new position value
    int newPosition = impostorState.getImpostorOrientation(GameStructure.DOWN);

    // Ask if it possible to move DOWN based on pre-requisits
    if (environmentState.getAgentEnergy() > 0 && newPosition != GameStructure.WALL) {

      // Update orientation agent array
      int[] newOrientation = GameStructure.getRowShipPosition(newPosition);
      impostorState.setImpostorOrientation(newOrientation);

      // Update agent and environment energy
      impostorState.setEnergy(impostorState.getEnergy() - GameStructure.Q_CONSUME_ENERGY);
      environmentState.setAgentEnergy(environmentState.getAgentEnergy() - GameStructure.Q_CONSUME_ENERGY);

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
    return "**GO DOWN**";
  }
}
