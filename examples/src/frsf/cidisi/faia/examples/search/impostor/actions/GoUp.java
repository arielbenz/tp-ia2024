package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.examples.search.impostor.ShipStructure;
import frsf.cidisi.faia.examples.search.impostor.ImpostorAgentState;
import frsf.cidisi.faia.examples.search.impostor.ImpostorEnvironmentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoUp extends SearchAction {

  /**
   * This method updates a tree node state when the search process is running.
   * It does not updates the real world state.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {

    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    int pos = impostorState.getPosition();

    int upPosition = impostorState.getImpostorOrientation(ShipStructure.UP);

    /* The agent can always go up */
    if (impostorState.getEnergy() > 0
        && impostorState.getImpostorOrientation(ShipStructure.UP) != ShipStructure.WALL) {

      int[] newOrientation = new int[] { ShipStructure.getShipPosition(pos, 0),
          ShipStructure.getShipPosition(pos, 1), ShipStructure.getShipPosition(pos, 2),
          ShipStructure.getShipPosition(pos, 3) };

      impostorState.setImpostorOrientation(newOrientation);
      impostorState.setPosition(upPosition);
      impostorState.setEnergy(impostorState.getEnergy() - ShipStructure.Q_CONSUME_ENERGY);

      System.out.println("-- Go Up Action - Agent pos: " + pos);
    }

    return impostorState;
  }

  /**
   * This method updates the agent state and the real world state.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;
    ImpostorAgentState impostorState = ((ImpostorAgentState) ast);

    int pos = environmentState.getAgentPosition();

    if (impostorState.getEnergy() > 0
        && impostorState.getImpostorOrientation(ShipStructure.UP) != ShipStructure.WALL) {
      int[] newOrientation = new int[] { ShipStructure.getShipPosition(pos, 0),
          ShipStructure.getShipPosition(pos, 1), ShipStructure.getShipPosition(pos, 2),
          ShipStructure.getShipPosition(pos, 3) };
      impostorState.setImpostorOrientation(newOrientation);

      impostorState.setPosition(pos);
      environmentState.setAgentPosition(pos);
    }

    return environmentState;
  }

  /**
   * This method returns the action cost.
   */
  @Override
  public Double getCost() {
    return new Double(0);
  }

  /**
   * This method is not important for a search based agent, but is essensial
   * when creating a calculus based one.
   */
  @Override
  public String toString() {
    return "GoUp";
  }
}
