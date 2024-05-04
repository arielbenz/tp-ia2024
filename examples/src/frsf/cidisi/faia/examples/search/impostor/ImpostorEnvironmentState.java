package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class ImpostorEnvironmentState extends EnvironmentState {

  private int[][] ship;
  private int agentPosition;
  private int agentEnergy;
  private int totalCrew;

  public ImpostorEnvironmentState(int[][] m) {
    ship = m;
  }

  public ImpostorEnvironmentState() {
    ship = new int[5][4];
    this.initState();
  }

  /**
   * This method is used to setup the initial real world.
   */
  @Override
  public void initState() {

    // Sets all positions as empty
    for (int row = 0; row < ship.length; row++) {
      for (int col = 0; col < ship.length; col++) {
        ship[row][col] = ImpostorPerception.EMPTY_PERCEPTION;
      }
    }

    /* Set rooms structure of the ship. */

    // Lower Engine
    ship[0][0] = ImpostorPerception.HALL_F; // Arriba
    ship[0][1] = ImpostorPerception.WALL; // Abajo
    ship[0][2] = ImpostorPerception.WALL; // Izquierda
    ship[0][3] = ImpostorPerception.WALL; // Derecha

    // Security
    ship[1][0] = ImpostorPerception.WALL;
    ship[1][1] = ImpostorPerception.WALL;
    ship[1][2] = ImpostorPerception.HALL_F;
    ship[1][3] = ImpostorPerception.WALL;

    // Reactor
    ship[2][0] = ImpostorPerception.WALL;
    ship[2][1] = ImpostorPerception.WALL;
    ship[2][2] = ImpostorPerception.WALL;
    ship[2][3] = ImpostorPerception.HALL_F;

    // Upper Engine
    ship[3][0] = ImpostorPerception.WALL;
    ship[3][1] = ImpostorPerception.HALL_F;
    ship[3][2] = ImpostorPerception.WALL;
    ship[3][3] = ImpostorPerception.WALL;

    // Hall F
    ship[4][0] = ImpostorPerception.ROOM_UPPER_ENGINE;
    ship[4][1] = ImpostorPerception.ROOM_LOWER_ENGINE;
    ship[4][2] = ImpostorPerception.ROOM_REACTOR;
    ship[4][3] = ImpostorPerception.ROOM_SECURITY;

    ////////////////////////
    // Orientation matriz //
    ////////////////////////
    // [     UP  DO  LE  RI]
    // [10] [20, -1, -1, -1]
    // [11] [-1, -1, 20, -1]
    // [12] [-1, -1, -1, 20]
    // [13] [-1, 20, -1, -1]
    // [20] [13, 10, 12, 11]
    //
    ////////////////////////

    this.setAgentPosition(1);
    this.setAgentEnergy(50);
    this.setTotalCrew(2);
  }

  /**
   * String representation of the real world state.
   */
  @Override
  public String toString() {
    String str = "";

    str = str + "[ \n";
    for (int row = 0; row < ship.length; row++) {
      str = str + "[ ";
      for (int col = 0; col < ship.length; col++) {
        str = str + ship[row][col] + " ";
      }
      str = str + " ]\n";
    }
    str = str + " ]";

    return str;
  }

  // The following methods are Impostor-specific:

  public int[][] getShip() {
    return ship;
  }

  public void setShip(int row, int col, int value) {
    this.ship[row][col] = value;
  }

  public int getAgentPosition() {
    return agentPosition;
  }

  public void setAgentPosition(int agentPosition) {
    this.agentPosition = agentPosition;
  }

  public int getTotalCrew() {
    return totalCrew;
  }

  public void setTotalCrew(int totalCrew) {
    this.totalCrew = totalCrew;
  }

  public int getAgentEnergy() {
    return agentEnergy;
  }

  public void setAgentEnergy(int agentEnergy) {
    this.agentEnergy = agentEnergy;
  }

  public int getTopPosition(int pos) {
    return ship[pos][0];
  }

  public int getBottomPosition(int pos) {
    return ship[pos][1];
  }

  public int getLeftPosition(int pos) {
    return ship[pos][2];
  }

  public int getRightPosition(int pos) {
    return ship[pos][3];
  }
}
