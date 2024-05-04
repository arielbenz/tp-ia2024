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

    int pos = impostorState.getPosition();

    /* The agent can always go down */
    if (impostorState.getEnergy() > 0 && impostorState.getImpostorOrientation(pos) != ImpostorPerception.WALL) {
      impostorState.setPosition(impostorState.getImpostorOrientation(1));
      impostorState.setEnergy(impostorState.getEnergy() - 1);
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

    int pos = environmentState.getAgentPosition();

    impostorState.setPosition(pos);
    environmentState.setAgentPosition(pos);

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
