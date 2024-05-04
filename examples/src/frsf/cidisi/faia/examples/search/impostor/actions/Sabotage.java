package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.ImpostorAgentState;
import frsf.cidisi.faia.examples.search.impostor.ImpostorEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Sabotage extends SearchAction {
  /**
   * See comments in the Eat class.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {
    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    int pos = impostorState.getPosition();

    /*
     * The 'Fight' action can be selected only if there is an enemy in
     * the current position. Otherwise return 'null'.
     */

    int[] sabotageRooms = impostorState.getSabotageRooms();

    boolean isSabotageRoom = false;

    for (int i = 0; i <= sabotageRooms.length; i++) {
      if (sabotageRooms[i] == pos) {
        isSabotageRoom = true;
      }
    }

    if (isSabotageRoom && (impostorState.getEnergy() > 0)) {
      sabotageRooms[pos] = 0;
      impostorState.setSabotageRooms(sabotageRooms);

      return impostorState;
    }

    return null;
  }

  /**
   * See comments in the Eat class.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {

    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;

    if ((environmentState.getTotalCrew() > 0) && (environmentState.getAgentEnergy() > 0)) {
      environmentState.setTotalCrew(environmentState.getTotalCrew() - 1);
      environmentState.setAgentEnergy(environmentState.getAgentEnergy() - 1);
      return environmentState;
    }

    return null;
  }

  /**
   * See comments in the Eat class.
   */
  @Override
  public Double getCost() {
    return new Double(0);
  }

  /**
   * See comments in the Eat class.
   */
  @Override
  public String toString() {
    return "Eliminate Crew";
  }
}
