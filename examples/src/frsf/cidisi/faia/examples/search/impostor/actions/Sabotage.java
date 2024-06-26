package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.GameStructure;
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

    // Increase the action cost count
    impostorState.increaseActionCost(GameStructure.ACTION_SABOTAGE_COST);

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

      int[] newSabotageRooms = new int[sabotageRooms.length - 1];

      for (int i = 0, j = 0; i < sabotageRooms.length; i++) {
        if (sabotageRooms[i] != pos) {
          newSabotageRooms[j] = sabotageRooms[i];
          j++;
        }
      }

      impostorState.setSabotageRooms(newSabotageRooms);
      impostorState.setTotalSabotageRooms(newSabotageRooms.length);

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

    ImpostorAgentState impostorState = ((ImpostorAgentState) ast);
    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;

    // Increase the action cost count
    impostorState.increaseActionCost(GameStructure.ACTION_SABOTAGE_COST);

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

      // Remove the sabotage room
      int[] newSabotageRooms = new int[sabotageRooms.length - 1];

      for (int i = 0, j = 0; i < sabotageRooms.length; i++) {
        if (sabotageRooms[i] != pos) {
          newSabotageRooms[j] = sabotageRooms[i];
          j++;
        }
      }

      // Update sabotage rooms on agent
      impostorState.setSabotageRooms(newSabotageRooms);
      impostorState.setTotalSabotageRooms(newSabotageRooms.length);

      // Update sabotage rooms on environment
      environmentState.setSabotageRooms(newSabotageRooms);

      // Update agent and environment energy
      impostorState.consumeEnergy();
      environmentState.consumeEnergy();

      return environmentState;
    }

    return null;
  }

  /**
   * This method returns the action cost.
   */
  @Override
  public Double getCost() {
    return new Double(GameStructure.ACTION_SABOTAGE_COST);
  }

  /**
   * This method is not important for a search based agent, but is essensial
   * when creating a calculus based one.
   */
  @Override
  public String toString() {
    return "-SABOTAGE";
  }
}
