package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
//import frsf.cidisi.faia.examples.search.impostor.ImpostorPerception;

/**
 * Represent the internal state of the Impostor.
 */
public class ImpostorAgentState extends SearchBasedAgentState {

  private int position;
  private int energy;
  private int[] sabotageRooms;
  private int totalSabotageRooms;
  private int crewInPosition;
  private int totalCrew;
  private int[] impostorOrientation; // [UP, DOWN, LEFT, RIGHT]
  private int actionCost;

  public ImpostorAgentState() {
    crewInPosition = GameStructure.CREW_IN_POSITION;
    totalCrew = GameStructure.INITIAL_TOTAL_CREW;
    sabotageRooms = new int[0];
    impostorOrientation = GameStructure.AGENT_ORIENTATION;
    this.initState();
  }

  public ImpostorAgentState(int e, int crew, int pos, int[] sabRooms, int[] orientation) {
    position = pos;
    energy = e;
    sabotageRooms = sabRooms;
    totalSabotageRooms = sabRooms.length;
    crewInPosition = crew;
    impostorOrientation = orientation;
    actionCost = 0;
  }

  /**
   * This method is optional, and sets the initial state of the agent.
   */
  @Override
  public void initState() {
    energy = GameStructure.INITIAL_AGENT_ENERGY;
    position = GameStructure.INITIAL_AGENT_POSITION;
    crewInPosition = GameStructure.CREW_IN_POSITION;
    totalCrew = GameStructure.INITIAL_TOTAL_CREW;
    totalCrew = GameStructure.INITIAL_TOTAL_CREW;
    sabotageRooms = GameStructure.INITIAL_SABOTAGE_ROOMS;
    totalSabotageRooms = sabotageRooms.length;
  }

  /**
   * This method clones the state of the agent. It's used in the search
   * process, when creating the search tree.
   */
  @Override
  public SearchBasedAgentState clone() {

    int[] newImpostorOrientation = new int[impostorOrientation.length];
    for (int i = 0; i < impostorOrientation.length; i++) {
      newImpostorOrientation[i] = impostorOrientation[i];
    }

    int[] newSabotageRooms = new int[sabotageRooms.length];
    for (int i = 0; i < sabotageRooms.length; i++) {
      newSabotageRooms[i] = sabotageRooms[i];
    }

    int newEnergy = this.getEnergy();
    int newPosition = this.getPosition();
    int newCrewInPosition = this.getCrewInPosition();

    ImpostorAgentState newState = new ImpostorAgentState(newEnergy, newCrewInPosition, newPosition,
        newSabotageRooms, newImpostorOrientation);

    return newState;
  }

  /**
   * This method is used to update the Impostor State when a Perception is
   * received by the Simulator.
   */
  @Override
  public void updateState(Perception p) {
    ImpostorPerception impostorPerception = (ImpostorPerception) p;

    impostorOrientation[GameStructure.UP] = impostorPerception.getUpSensor();
    impostorOrientation[GameStructure.DOWN] = impostorPerception.getDownSensor();
    impostorOrientation[GameStructure.LEFT] = impostorPerception.getLeftSensor();
    impostorOrientation[GameStructure.RIGHT] = impostorPerception.getRightSensor();
    crewInPosition = impostorPerception.getCrewSensor();
  }

  /**
   * This method returns the String representation of the agent state.
   */
  @Override
  public String toString() {
    String str = "";

    str = str + "\n\n* Posición: (" + this.getPosition() + " - " + GameStructure.ROOMS.get(this.getPosition()) + ")";
    str = str + "\n* Energía: " + energy + "\n";

    str = str + "\nORIENTACIÓN EN NAVE = \"( ";
    for (int row = 0; row < impostorOrientation.length; row++) {
      str = str + "[ ";
      if (impostorOrientation[row] == GameStructure.WALL) {
        str = str + "* ";
      } else {
        str = str + impostorOrientation[row] + " ";
      }
      str = str + "]";
    }

    str = str + " )\"\n";
    str = str + "\nHabitaciones a Sabotear = \"( ";
    for (int row = 0; row < sabotageRooms.length; row++) {
      str = str + "[";
      str = str + GameStructure.ROOMS.get(sabotageRooms[row]);
      str = str + "] ";
    }
    str = str + ")\"\n";
    str = str + "\nHabitaciones c/Tripulantes = \"( " + crewInPosition + " )\"\n";

    return str;
  }

  /**
   * This method is used in the search process to verify if the node already
   * exists in the actual search.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ImpostorAgentState))
      return false;

    int totalSabotageRoomsObj = ((ImpostorAgentState) obj).getTotalSabotageRooms();
    if (this.totalSabotageRooms != totalSabotageRoomsObj) {
      return false;
    }

    int crewInPositionObj = ((ImpostorAgentState) obj).getCrewInPosition();
    if (crewInPosition != crewInPositionObj) {
      return false;
    }

    int totalCrewObj = ((ImpostorAgentState) obj).getRemainingCrewRoom();
    if (this.totalCrew != totalCrewObj) {
      return false;
    }

    int positionObj = ((ImpostorAgentState) obj).getPosition();
    if (this.position != positionObj) {
      return false;
    }

    return true;
  }

  // The following methods are Impostor-specific:

  public int getImpostorOrientation(int orientation) {
    return impostorOrientation[orientation];
  }

  public int[] getImpostorOrientation() {
    return impostorOrientation;
  }

  public void setImpostorOrientation(int[] orientation) {
    this.impostorOrientation = orientation;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int value) {
    this.position = value;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public void consumeEnergy() {
    this.energy = this.energy - GameStructure.Q_CONSUME_ENERGY;
  }

  public int[] getSabotageRooms() {
    return sabotageRooms;
  }

  public void setSabotageRooms(int[] sabotageRooms) {
    this.sabotageRooms = sabotageRooms;
  }

  public int getCrewInPosition() {
    return crewInPosition;
  }

  public void eliminateCrew() {
    this.crewInPosition = this.crewInPosition - 1;
    this.totalCrew = this.totalCrew - 1;
  }

  public int getTotalSabotageRooms() {
    return totalSabotageRooms;
  }

  public void setTotalSabotageRooms(int totalSabotageRooms) {
    this.totalSabotageRooms = totalSabotageRooms;
  }

  public boolean isNoMoreSabotageRooms() {
    if (totalSabotageRooms == 0) {
      return true;
    }
    return false;
  }

  public boolean isNoMoreCrew() {
    if (this.totalCrew > 0) {
      return false;
    }

    return true;
  }

  public int getActionCost() {
    return actionCost;
  }

  public void increaseActionCost(int n) {
    this.actionCost = +n;
  }

  public int getRemainingCrewRoom() {
    return totalCrew;
  }

}
