package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanPerception;

/**
 * Represent the internal state of the Impostor.
 */
public class ImpostorAgentState extends SearchBasedAgentState {
  private int position;
  private int initialPosition;
  private int energy;

  private int[] sabotageRooms; // Array of 1 positions
  private int[] crewPerRoom; // [0, 0, 0, 0, 0]

  // [UP, DO, LE, RI]
  private int[] impostorOrientation; // [0, 0, 0, 0]

  public ImpostorAgentState(int e, int[] crew, int pos, int[] sabRooms, int[] orientation) {
    crewPerRoom = crew;
    position = pos;
    initialPosition = 1;
    sabotageRooms = sabRooms;
    energy = e;
    impostorOrientation = orientation;
  }

  public ImpostorAgentState() {
    crewPerRoom = new int[5];
    sabotageRooms = new int[1];
    impostorOrientation = new int[4];
    position = 2;
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
    sabotageRooms = new int[] { 12 };

    this.setPosition(1);
    this.setEnergy(100);
  }

  /**
   * This method clones the state of the agent. It's used in the search
   * process, when creating the search tree.
   * VEEERRR BIEN CLONE
   */
  @Override
  public SearchBasedAgentState clone() {
    int[] newCrewPerRoom = new int[5];

    for (int row = 0; row < world.length; row++) {
      
    }

    int newPosition = position;

    ImpostorAgentState newState = new ImpostorAgentState(this.energy, newCrewPerRoom,
        this.getPosition(),  );

    return newState;
  }

  /**
   * This method is used to update the Impostor State when a Perception is
   * received by the Simulator.
   */
  @Override
  public void updateState(Perception p) {
    ImpostorPerception impostorPerception = (ImpostorPerception) p;

    impostorOrientation[0] = impostorPerception.getTopSensor();
    impostorOrientation[1] = impostorPerception.getBottomSensor();
    impostorOrientation[2] = impostorPerception.getLeftSensor();
    impostorOrientation[3] = impostorPerception.getRightSensor();

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

    str = str + " posicion=\"(" + getPosition() + ")\"";
    str = str + " energia=\"" + energy + "\"\n";

    str = str + "NAVE=\"[ \n";
    for (int row = 0; row < impostorOrientation.length; row++) {
      str = str + "[ ";
      if (impostorOrientation[row] == -1) {
        str = str + "* ";
      } else {
        str = str + impostorOrientation[row] + " ";
      }
      str = str + " ]\n";
    }
    str = str + " ]\"";

    return str;
  }

  /**
   * This method is used in the search process to verify if the node already
   * exists in the actual search.
   * VEEER BIEN ESTO
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ImpostorAgentState))
      return false;

    int[][] worldObj = ((ImpostorAgentState) obj).getWorld();
    int positionObj = ((ImpostorAgentState) obj).getPosition();

    for (int row = 0; row < world.length; row++) {
      if (world[row][col] != worldObj[row][col]) {
        return false;
      }
    }

    if (position[0] != positionObj[0] || position[1] != positionObj[1]) {
      return false;
    }

    return true;
  }

  // The following methods are Impostor-specific:
  // el agente no lo tiene definido como estador ver si hay que borrar esto

  // public int[][] getShip() {
  // return ship;
  // }

  // public int getShipPosition(int row, int col) {
  // return ship[row][col];
  // }

  // public void setShipPosition(int row, int col, int value) {
  // this.ship[row][col] = value;
  // }

  public int getImpostorOrientation(int pos) {
    return impostorOrientation[pos];
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

  // public boolean isAllShipKnown() {
  // for (int row = 0; row < ship.length; row++) {
  // for (int col = 0; col < ship.length; col++) {
  // if (ship[row][col] == ImpostorPerception.UNKNOWN_PERCEPTION) {
  // return false;
  // }
  // }
  // }

  // return true;
  // }

  // public int getUnknownCellsCount() {
  // int result = 0;

  // for (int row = 0; row < world.length; row++) {
  // for (int col = 0; col < world.length; col++) {
  // if (world[row][col] == ImpostorPerception.UNKNOWN_PERCEPTION) {
  // result++;
  // }
  // }
  // }

  // return result;
  // }

  // public int getRemainingFoodCount() {
  // int result = 0;

  // for (int row = 0; row < world.length; row++) {
  // for (int col = 0; col < world.length; col++) {
  // if (world[row][col] == ImpostorPerception.FOOD_PERCEPTION) {
  // result++;
  // }
  // }
  // }

  // return result;
  // }

  public boolean isNoMoreSabotageRooms() {
    for (int row = 0; row < sabotageRooms.length; row++) {
      if (sabotageRooms[row] == ImpostorPerception.ROOM_SABOTAGE_PERCEPTION) {
        return false;
      }
    }
    return true;
  }

  public boolean isNoMoreCrewPerRoom() {
    for (int row = 0; row < crewPerRoom.length; row++) {
      if (crewPerRoom[row] == ImpostorPerception.CREW_PERCEPTION) {
        return false;
      }
    }
    return true;
  }

  // public int getVisitedCellsCount() {
  // return visitedCells;
  // }

  // public void increaseVisitedCellsCount() {
  // this.visitedCells = +20;
  // }
}
