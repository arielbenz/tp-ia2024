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
  private int[] crewPerRoom;
  private int[] impostorOrientation; // [UP, DOWN, LEFT, RIGHT]

  public ImpostorAgentState(int e, int[] crew, int pos, int[] sabRooms, int[] orientation) {
    crewPerRoom = crew;
    position = pos;
    sabotageRooms = sabRooms;
    energy = e;
    impostorOrientation = orientation;
  }

  public ImpostorAgentState() {
    crewPerRoom = Constants.CREW_PER_ROOM;
    sabotageRooms = Constants.SABOTAGE_ROOMS;
    impostorOrientation = Constants.AGENT_ORIENTATION;
    this.initState();
  }

  /**
   * This method is optional, and sets the initial state of the agent.
   */
  @Override
  public void initState() {
    crewPerRoom = Constants.INITIAL_CREW_PER_ROOM;
    sabotageRooms = Constants.INITIAL_SABOTAGE_ROOMS;
    energy = Constants.INITIAL_AGENT_ENERGY  ;
    position = Constants.INITIAL_AGENT_POSITION;
  }

  /**
   * This method clones the state of the agent. It's used in the search
   * process, when creating the search tree.
   * VEEERRR BIEN CLONE
   */
  @Override
  public SearchBasedAgentState clone() {

    int[] newCrewPerRoom = new int[crewPerRoom.length]; // esta es fija no cambia por ahora
    for (int row = 0; row < crewPerRoom.length; row++) { // [UP, DO, LE, RI]
      newCrewPerRoom[row] = crewPerRoom[row];
    }

    // la orientacion cambia segun la posicion del agente
    int[] newImpostorOrientation = new int[impostorOrientation.length];
    for (int col = 0; col < impostorOrientation.length; col++) { // [UP, DO, LE, RI]
      newImpostorOrientation[col] = impostorOrientation[col];
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

    impostorOrientation[Constants.UP] = impostorPerception.getUpSensor();
    impostorOrientation[Constants.DOWN] = impostorPerception.getDownSensor();
    impostorOrientation[Constants.LEFT] = impostorPerception.getLeftSensor();
    impostorOrientation[Constants.RIGHT] = impostorPerception.getRightSensor();

    energy = impostorPerception.getEnergy();
  }

  /**
   * This method returns the String representation of the agent state.
   */
  @Override
  public String toString() {
    String str = "";

    str = str + "\n\n* Posición: (" + getPosition() + " - " + Constants.ROOMS.get(getPosition()) + ")";
    str = str + "\n* Energía: " + energy + "\n";

    str = str + "\nORIENTACIÓN EN NAVE DEL IMPOSTOR=\"( ";
    for (int row = 0; row < impostorOrientation.length; row++) {
      str = str + "[ ";
      if (impostorOrientation[row] == Constants.WALL) {
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

    int[] impostorOrientationObj = ((ImpostorAgentState) obj).getImpostorOrientation();
    int positionObj = ((ImpostorAgentState) obj).getPosition();

    for (int row = 0; row < impostorOrientation.length; row++) {
      if (impostorOrientation[row] != impostorOrientationObj[row]) {
        System.out.println("-- EQUALS FALSE --");
        return false;
      }
    }

    if (position != positionObj) {
      System.out.println("-- EQUALS FALSE --");
      return false;
    }

    System.out.println("-- EQUALS TRUE --");
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
    this.crewPerRoom[pos] = crewPerRoom[pos] - 1;
  }

  public boolean isNoMoreSabotageRooms() {
    if (sabotageRooms.length == 0) {
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
