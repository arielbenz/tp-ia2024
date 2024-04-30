package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.examples.search.impostor.ImpostorAgentState;
import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoLeft extends SearchAction {

  /**
   * See comments in the Eat class.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {

    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    impostorState.increaseVisitedCellsCount();

    int row = impostorState.getRowPosition();
    int col = impostorState.getColumnPosition();

    // Check the limits of the world
    if (col == 0) {
      col = 3;
    } else {
      col = col - 1;
    }

    impostorState.setColumnPosition(col);

    /* The agent can only go left when the cell is not empty */
    if (impostorState.getWorldPosition(row, col) != PacmanPerception.EMPTY_PERCEPTION) {

      impostorState.setWorldPosition(row, col,
          PacmanPerception.EMPTY_PERCEPTION);

      return impostorState;
    }

    return null;
  }

  /**
   * See comments in the Eat class.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {

    PacmanEnvironmentState environmentState = (PacmanEnvironmentState) est;
    ImpostorAgentState impostorState = ((ImpostorAgentState) ast);

    impostorState.increaseVisitedCellsCount();

    int row = environmentState.getAgentPosition()[0];
    int col = environmentState.getAgentPosition()[1];

    // Check the limits of the world
    if (col == 0) {
      col = 3;
    } else {
      col = col - 1;
    }

    impostorState.setColumnPosition(col);

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
    return "GoLeft";
  }
}
