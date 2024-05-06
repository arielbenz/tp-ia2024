package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.Constants;
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

    int pos = impostorState.getPosition();

    /*
     * The 'Sabotage' action can be selected only if there is a sabotage room in
     * the current position. Otherwise return 'null'.
     */

    int[] sabotageRooms = impostorState.getSabotageRooms();

    boolean isSabotageRoom = false;
    int[] newSabotageRooms = new int[] {};

    for (int i = 0; i < sabotageRooms.length; i++) {
      if (sabotageRooms[i] == pos) {
        isSabotageRoom = true;
      } else {
        // newSabotageRooms[0] = sabotageRooms[i];
      }
    }

    if (isSabotageRoom && (impostorState.getEnergy() > 0)) {
      impostorState.setEnergy(impostorState.getEnergy() - Constants.Q_CONSUME_ENERGY);

      //TODO - send new sabotage rooms array
      impostorState.setSabotageRooms(new int[] {});

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

    if ((environmentState.getTotalCrew() > 0) && (environmentState.getAgentEnergy() > 0)) {
      // environmentState.setTotalCrew(environmentState.getTotalCrew() - 1);
      environmentState.setAgentEnergy(environmentState.getAgentEnergy() - Constants.Q_CONSUME_ENERGY);
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
    return "Eliminate Crew";
  }
}
