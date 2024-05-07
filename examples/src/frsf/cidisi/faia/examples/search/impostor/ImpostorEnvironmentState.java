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
    ship = ShipStructure.SHIP;
    this.initState();
  }

  /**
   * This method is used to setup the initial real world.
   */
  @Override
  public void initState() {

    ShipStructure shipStructure = new ShipStructure();
    shipStructure.init();

    this.setSabotageRooms(new int[] { ShipStructure.ROOM_LOWER_ENGINE });
    this.setAgentPosition(ShipStructure.INITIAL_AGENT_POSITION);
    this.setAgentEnergy(ShipStructure.INITIAL_AGENT_ENERGY);
    this.setTotalCrew(ShipStructure.INITIAL_TOTAL_CREW);
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
    return ship[pos][ShipStructure.UP];
  }

  public int getDownPosition(int pos) {
    return ship[pos][ShipStructure.DOWN];
  }

  public int getLeftPosition(int pos) {
    return ship[pos][ShipStructure.LEFT];
  }

  public int getRightPosition(int pos) {
    return ship[pos][ShipStructure.RIGHT];
  }
}
