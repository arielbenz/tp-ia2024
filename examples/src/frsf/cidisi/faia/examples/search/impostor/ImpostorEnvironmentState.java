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

    // Sets all cells as empty
    for (int row = 0; row < ship.length; row++) {
      for (int col = 0; col < ship.length; col++) {
        ship[row][col] = ImpostorPerception.EMPTY_PERCEPTION;
      }
    }

    /* Sets inicial
     //motor Inferior */
    ship[0][0] = ImpostorPerception.HALL_F;   //arriba
    ship[0][1] = ImpostorPerception.WALL;  //abajo
    ship[0][2] = ImpostorPerception.WALL;  //Izquierda
    ship[0][3] = ImpostorPerception.WALL;  //Derecha

    //seguridad
    ship[1][0] = ImpostorPerception.WALL;  
    ship[1][1] = ImpostorPerception.WALL;
    ship[1][2] = ImpostorPerception.HALL_F;
    ship[1][3] = ImpostorPerception.WALL;
    
    //reactor
    ship[2][0] = ImpostorPerception.WALL;
    ship[2][1] = ImpostorPerception.WALL;
    ship[2][2] = ImpostorPerception.WALL;
    ship[2][3] = ImpostorPerception.HALL_F;
    
    //motor superior
    ship[3][0] = ImpostorPerception.WALL;
    ship[3][1] = ImpostorPerception.HALL_F;
    ship[3][2] = ImpostorPerception.WALL;
    ship[3][3] = ImpostorPerception.WALL;

    // [20, -1, -1, -1]
    // [-1, -1, 20, -1]
    // [20, -1, -1, -1]
    // [20, -1, -1, -1]
    // [20, -1, -1, -1]

    this.setAgentPosition(1);
    this.setAgentEnergy(50);
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

  //public void setShip(int[][] world) {
  //  this.ship = world;
  //}

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

  public int getTopCell(int pos) {
    return ship[pos][0];
  }

  public int getBottomCell(int pos) {
    return ship[pos][1];
  }

  public int getLeftCell(int pos) {
    return ship[pos][2];
  }

  public int getRightCell(int pos) {
    return ship[pos][3];
  }
}
