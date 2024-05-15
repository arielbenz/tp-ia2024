package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class ImpostorEnvironmentState extends EnvironmentState {

  private int[][] ship;
  private int[] sabotageRooms;
  private int agentPosition;
  private int agentEnergy;
  private int totalCrew;

  public ImpostorEnvironmentState(int[][] m) {
    ship = m;
  }

  public ImpostorEnvironmentState() {
    ship = GameStructure.SHIP;
    this.initState();
  }

  /**
   * This method is used to setup the initial real world.
   */
  @Override
  public void initState() {

    GameStructure shipStructure = new GameStructure();

    this.setAgentPosition(GameStructure.INITIAL_AGENT_POSITION);
    this.setAgentEnergy(GameStructure.INITIAL_AGENT_ENERGY);
    this.setTotalCrew(GameStructure.INITIAL_TOTAL_CREW);
    this.setSabotageRooms(GameStructure.INITIAL_SABOTAGE_ROOMS);
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

  public int getAgentPosition() {
    return agentPosition;
  }

  public void setAgentPosition(int agentPosition) {
    this.agentPosition = agentPosition;
  }

  public int[] getSabotageRooms() {
    return sabotageRooms;
  }

  public void setSabotageRooms(int[] sabotageRooms) {
    this.sabotageRooms = sabotageRooms;
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
    return ship[pos][GameStructure.UP];
  }

  public int getDownPosition(int pos) {
    return ship[pos][GameStructure.DOWN];
  }

  public int getLeftPosition(int pos) {
    return ship[pos][GameStructure.LEFT];
  }

  public int getRightPosition(int pos) {
    return ship[pos][GameStructure.RIGHT];
  }
}
