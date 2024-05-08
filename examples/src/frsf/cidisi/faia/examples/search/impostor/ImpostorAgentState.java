package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Impostor.
 */
public class ImpostorAgentState extends SearchBasedAgentState {

  private int position;
  private int energy;
  private int[] sabotageRooms;
  private int totalSabotageRooms;
  private int[] crewPerRoom;
  private int[] impostorOrientation; // [UP, DOWN, LEFT, RIGHT]

  public ImpostorAgentState() {
    crewPerRoom = ShipStructure.CREW_PER_ROOM;
    sabotageRooms = new int[0];
    impostorOrientation = ShipStructure.AGENT_ORIENTATION;
    this.initState();
  }

  public ImpostorAgentState(int e, int[] crew, int pos, int[] sabRooms, int[] orientation) {
    position = pos;
    energy = e;
    sabotageRooms = sabRooms;
    totalSabotageRooms = sabRooms.length;
    crewPerRoom = crew;
    impostorOrientation = orientation;
  }

  /**
   * This method is optional, and sets the initial state of the agent.
   */
  @Override
  public void initState() {
    energy = ShipStructure.INITIAL_AGENT_ENERGY;
    position = ShipStructure.INITIAL_AGENT_POSITION;
    crewPerRoom = ShipStructure.INITIAL_CREW_PER_ROOM;
    sabotageRooms = ShipStructure.INITIAL_SABOTAGE_ROOMS;
    totalSabotageRooms = sabotageRooms.length;
  }

  /**
   * This method clones the state of the agent. It's used in the search
   * process, when creating the search tree.
   */
  @Override
  public SearchBasedAgentState clone() {

    int[] newCrewPerRoom = new int[crewPerRoom.length]; // esta es fija no cambia por ahora
    for (int i = 0; i < crewPerRoom.length; i++) { // [UP, DO, LE, RI]
      newCrewPerRoom[i] = crewPerRoom[i];
    }

    // la orientacion cambia segun la posicion del agente
    int[] newImpostorOrientation = new int[impostorOrientation.length];
    for (int i = 0; i < impostorOrientation.length; i++) { // [UP, DO, LE, RI]
      newImpostorOrientation[i] = impostorOrientation[i];
    }

    int[] newSabotageRooms = new int[sabotageRooms.length];
    for (int i = 0; i < sabotageRooms.length; i++) { // [UP, DO, LE, RI]
      newSabotageRooms[i] = sabotageRooms[i];
    }

    int newEnergy = this.getEnergy();
    int newPosition = this.getPosition();

    ImpostorAgentState newState = new ImpostorAgentState(newEnergy, newCrewPerRoom, newPosition,
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

    impostorOrientation[ShipStructure.UP] = impostorPerception.getUpSensor();
    impostorOrientation[ShipStructure.DOWN] = impostorPerception.getDownSensor();
    impostorOrientation[ShipStructure.LEFT] = impostorPerception.getLeftSensor();
    impostorOrientation[ShipStructure.RIGHT] = impostorPerception.getRightSensor();

    energy = impostorPerception.getEnergy();
  }

  /**
   * This method returns the String representation of the agent state.
   */
  @Override
  public String toString() {
    String str = "";

    str = str + "\n\n* Posición: (" + this.getPosition() + " - " + ShipStructure.ROOMS.get(this.getPosition()) + ")";
    str = str + "\n* Energía: " + energy + "\n";

    str = str + "\nORIENTACIÓN EN NAVE = \"( ";
    for (int row = 0; row < impostorOrientation.length; row++) {
      str = str + "[ ";
      if (impostorOrientation[row] == ShipStructure.WALL) {
        str = str + "* ";
      } else {
        str = str + impostorOrientation[row] + " ";
      }
      str = str + "]";
    }
    str = str + " )\"\n";

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

    int positionObj = ((ImpostorAgentState) obj).getPosition();
    int totalSabotageRoomsObj = ((ImpostorAgentState) obj).getTotalSabotageRooms();

    if (this.totalSabotageRooms != totalSabotageRoomsObj) {
      return false;
    }

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

  public int[] getSabotageRooms() {
    return sabotageRooms;
  }

  public void setSabotageRooms(int[] sabotageRooms) {
    this.sabotageRooms = sabotageRooms;
  }

  public int getCrewPerRoom(int pos) {
    return crewPerRoom[pos];
  }

  public void setCrewPerRoom(int pos) {
    this.crewPerRoom[pos] = this.crewPerRoom[pos] - 1;
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

  public boolean isNoMoreCrewPerRoom() {
    for (int row = 0; row < crewPerRoom.length; row++) {
      if (crewPerRoom[row] > 0) {
        return false;
      }
    }
    return true;
  }
}
