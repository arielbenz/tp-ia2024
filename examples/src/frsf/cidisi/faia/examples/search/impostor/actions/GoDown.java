package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.*;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoDown extends SearchAction {

  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {

    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    // Increase the visited cells count
    impostorState.increaseVisitedCellsCount();

    int row = impostorState.getRowPosition();
    int col = impostorState.getColumnPosition();

    // Check the limits of the world
    if (row == 3) {
      row = 0;
    } else {
      row = row + 1;
    }

    impostorState.setRowPosition(row);

    /* The agent can always go down */
    if (impostorState.getWorldPosition(row, col) == ImpostorPerception.UNKNOWN_PERCEPTION) {

      impostorState.setWorldPosition(row, col,
          ImpostorPerception.EMPTY_PERCEPTION);
    }

    return impostorState;
  }

  /**
   * See comments in the Eat class.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;
    ImpostorAgentState impostorState = ((ImpostorAgentState) ast);

    impostorState.increaseVisitedCellsCount();

    int row = environmentState.getAgentPosition()[0];
    int col = environmentState.getAgentPosition()[1];

    if (row == 3) {
      row = 0;
    } else {
      row = row + 1;
    }

    impostorState.setRowPosition(row);

    environmentState.setAgentPosition(new int[] { row, col });

    return environmentState;
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
    return "GoDown";
  }
}
