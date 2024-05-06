package frsf.cidisi.faia.examples.search.impostor.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.impostor.Constants;
import frsf.cidisi.faia.examples.search.impostor.ImpostorAgentState;
import frsf.cidisi.faia.examples.search.impostor.ImpostorEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class EliminateCrew extends SearchAction {

  /**
   * This method updates a tree node state when the search process is running.
   * It does not updates the real world state.
   */
  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {

    ImpostorAgentState impostorState = (ImpostorAgentState) s;

    int pos = impostorState.getPosition();

    System.out.println("\n-- Eliminate Action -- Agent pos: " + pos);

    /*
     * The 'EliminateCrew' action can be selected only if there is a crew member in
     * the current position. Otherwise return 'null'.
     */
    if ((impostorState.getCrewPerRoom(pos) > 0) && (impostorState.getEnergy() > 0)) {
      impostorState.setCrewPerRoom(pos);

      System.out.println("-- CrewPerRoom[0]: " + impostorState.getCrewPerRoom(0));
      System.out.println("-- CrewPerRoom[1]: " + impostorState.getCrewPerRoom(1));
      System.out.println("-- CrewPerRoom[2]: " + impostorState.getCrewPerRoom(2));
      System.out.println("-- CrewPerRoom[3]: " + impostorState.getCrewPerRoom(3));
      System.out.println("-- CrewPerRoom[4]: " + impostorState.getCrewPerRoom(4));

    }

    return impostorState;
  }

  /**
   * This method updates the agent state and the real world state.
   */
  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {

    ImpostorEnvironmentState environmentState = (ImpostorEnvironmentState) est;

    if ((environmentState.getTotalCrew() > 0) && (environmentState.getAgentEnergy() > 0)) {
      environmentState.setTotalCrew(environmentState.getTotalCrew() - 1);
      environmentState.setAgentEnergy(environmentState.getAgentEnergy() - Constants.Q_CONSUME_ENERGY);
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
    return "Eliminate Crew";
  }
}
