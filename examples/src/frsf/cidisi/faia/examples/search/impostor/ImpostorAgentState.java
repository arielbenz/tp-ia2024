package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Impostor.
 */
public class ImpostorAgentState extends SearchBasedAgentState {
  private int position;
  private int energy;

  private int[] sabotageRooms; // Array of 1 positions
  private int[] crewPerRoom; // [0, 0, 0, 0, 0]

  // [UP, DO, LE, RI]
  private int[] impostorOrientation; // [0, 0, 0, 0]

  public ImpostorAgentState(int e, int[] crew, int pos, int[] sabRooms, int[] orientation) {
    crewPerRoom = crew;
    position = pos;
    sabotageRooms = sabRooms;
    energy = e;
    impostorOrientation = orientation;
  }

  public ImpostorAgentState() {
    crewPerRoom = new int[5];
    sabotageRooms = new int[1];
    impostorOrientation = new int[4];
    position = Constants.ROOM_LOWER_ENGINE;
    energy = 0;
    this.initState();
  }

  /**
   * This method is optional, and sets the initial state of the agent.
   */
  @Override
  public void initState() {
    // for (int row = 0; row < crewPerRoom.length; row++) {
    // crewPerRoom[row] = 0;
    // }

    // for (int row = 0; row < sabotageRooms.length; row++) {
    // sabotageRooms[row] = 0;
    // }

    crewPerRoom = new int[] { 0, 1, 1, 0, 0 };
    sabotageRooms = new int[] { Constants.ROOM_REACTOR };

    this.setPosition(Constants.ROOM_LOWER_ENGINE);
    this.setEnergy(Constants.START_ENERGY);
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

    int[] newImpostorOrientation = new int[impostorOrientation.length]; // la orientacion cambia segun la posicion del agente
    for (int col = 0; col < impostorOrientation.length; col++) { // [UP, DO, LE, RI]
      newImpostorOrientation[col] = impostorOrientation[col];
    }

    int[] newSabotageRooms = new int[sabotageRooms.length];
    for (int i = 0; i < sabotageRooms.length; i++) { // [UP, DO, LE, RI]
      newSabotageRooms[i] = sabotageRooms[i];
    }

    ImpostorAgentState newState = new ImpostorAgentState(this.energy, newCrewPerRoom, this.position,
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

    // int pos = this.getPosition();

    // crewPerRoom[pos] = 1;
    // sabotageRooms[pos] = 0;
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
        return false;
      }
    }

    if (position != positionObj) {
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
    if(sabotageRooms.length == 0) {
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
