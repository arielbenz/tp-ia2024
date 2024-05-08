package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.ShipStructure;
import frsf.cidisi.faia.examples.search.impostor.ImpostorAgentState;
import frsf.cidisi.faia.examples.search.impostor.ImpostorEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Sabotage extends SearchAction {

  /**
   * This method updates a tree node state when the search process is running.
   * It does not updates the real world state.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {
    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    /*
     * The 'Sabotage' action can be selected only if there is a sabotage room in
     * the current position. Otherwise return 'null'.
     */

    int pos = impostorState.getPosition();
    int totalSabotate = impostorState.getTotalSabotageRooms();

    int[] sabotageRooms = impostorState.getSabotageRooms();
    boolean isSabotageRoom = false;

    for (int i = 0; i < sabotageRooms.length; i++) {
      if (sabotageRooms[i] == pos) {
        isSabotageRoom = true;
      }
    }

    if (isSabotageRoom && impostorState.getEnergy() > 0 && totalSabotate > 0) {
      impostorState.setEnergy(impostorState.getEnergy() - ShipStructure.Q_CONSUME_ENERGY);
      impostorState.setSabotageRooms(new int[0]);
      impostorState.setTotalSabotageRooms(0);

      System.out.println("-- Sabotage Action - Agent pos: " + pos);

      return impostorState;
    }

    return null;
  }

  /**
   * This method updates the agent state and the real world state.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {

    ImpostorAgentState impostorState = ((ImpostorAgentState) ast);
    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;

    int pos = environmentState.getAgentPosition();

    int[] sabotageRooms = environmentState.getSabotageRooms();
    boolean isSabotageRoom = false;

    for (int i = 0; i < sabotageRooms.length; i++) {
      if (sabotageRooms[i] == pos) {
        isSabotageRoom = true;
      }
    }

    int totalSabotate = impostorState.getTotalSabotageRooms();

    if (isSabotageRoom && environmentState.getAgentEnergy() > 0 && totalSabotate > 0) {

      // Update sabotage rooms on agent
      impostorState.setSabotageRooms(new int[0]);
      impostorState.setTotalSabotageRooms(0);

      // Update sabotage rooms on environment
      environmentState.setSabotageRooms(new int[0]);

      // Update agent and environment energy
      impostorState.setEnergy(impostorState.getEnergy() - ShipStructure.Q_CONSUME_ENERGY);
      environmentState.setAgentEnergy(environmentState.getAgentEnergy() - ShipStructure.Q_CONSUME_ENERGY);

      return environmentState;
    }

    return null;
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
    return "**SABOTAGE**";
  }
}
