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
    ship = Constants.SHIP;
    this.initState();
  }

  /**
   * This method is used to setup the initial real world.
   */
  @Override
  public void initState() {

    /* Init rooms structure of the ship. */

    // Upper Engine = 0
    ship[Constants.ROOM_UPPER_ENGINE][Constants.UP] = Constants.WALL;
    ship[Constants.ROOM_UPPER_ENGINE][Constants.DOWN] = Constants.HALL_F;
    ship[Constants.ROOM_UPPER_ENGINE][Constants.LEFT] = Constants.WALL;
    ship[Constants.ROOM_UPPER_ENGINE][Constants.RIGHT] = Constants.WALL;

    // Security = 1
    ship[Constants.ROOM_SECURITY][Constants.UP] = Constants.WALL;
    ship[Constants.ROOM_SECURITY][Constants.DOWN] = Constants.WALL;
    ship[Constants.ROOM_SECURITY][Constants.LEFT] = Constants.HALL_F;
    ship[Constants.ROOM_SECURITY][Constants.RIGHT] = Constants.WALL;

    // Lower Engine = 2
    ship[Constants.ROOM_LOWER_ENGINE][Constants.UP] = Constants.HALL_F;
    ship[Constants.ROOM_LOWER_ENGINE][Constants.DOWN] = Constants.WALL;
    ship[Constants.ROOM_LOWER_ENGINE][Constants.LEFT] = Constants.WALL;
    ship[Constants.ROOM_LOWER_ENGINE][Constants.RIGHT] = Constants.WALL;

    // Reactor = 3
    ship[Constants.ROOM_REACTOR][Constants.UP] = Constants.WALL;
    ship[Constants.ROOM_REACTOR][Constants.DOWN] = Constants.WALL;
    ship[Constants.ROOM_REACTOR][Constants.LEFT] = Constants.WALL;
    ship[Constants.ROOM_REACTOR][Constants.RIGHT] = Constants.HALL_F;

    // Hall F = 4
    ship[Constants.HALL_F][Constants.UP] = Constants.ROOM_UPPER_ENGINE;
    ship[Constants.HALL_F][Constants.DOWN] = Constants.ROOM_LOWER_ENGINE;
    ship[Constants.HALL_F][Constants.LEFT] = Constants.ROOM_REACTOR;
    ship[Constants.HALL_F][Constants.RIGHT] = Constants.ROOM_SECURITY;

    ////////////////////////////////
    // Initial Orientation matriz //
    ////////////////////////////////
    //
    // [    UP  DO  LE  RI]
    // [0] [-1,  4, -1, -1]
    // [1] [-1, -1,  4, -1]
    // [2] [ 4, -1, -1, -1]
    // [3] [-1, -1, -1,  4]
    // [4] [ 0,  2,  3,  1]
    //
    ////////////////////////////////

    this.setAgentPosition(Constants.INITIAL_AGENT_POSITION);
    this.setAgentEnergy(Constants.INITIAL_AGENT_ENERGY);
    this.setTotalCrew(Constants.INITIAL_TOTAL_CREW);
  }

  /**
   * String representation of the real world state.
   */
  @Override
  public String toString() {
    String str = "";

    str = str + "NAVE=( \n";
    for (int row = 0; row < ship.length; row++) {
      str = str + "                   [ ";
      for (int col = 0; col < ship[row].length; col++) {
        str = str + ship[row][col] + " ";
      }
      str = str + "]\n";
    }
    str = str + ")";

    return str;
  }

  // The following methods are Impostor-specific:

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

  public int getUpPosition(int pos) {
    return ship[pos][Constants.UP];
  }

  public int getDownPosition(int pos) {
    return ship[pos][Constants.DOWN];
  }

  public int getLeftPosition(int pos) {
    return ship[pos][Constants.LEFT];
  }

  public int getRightPosition(int pos) {
    return ship[pos][Constants.RIGHT];
  }
}
