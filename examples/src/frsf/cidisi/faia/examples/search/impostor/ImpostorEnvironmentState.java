package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class ImpostorEnvironmentState extends EnvironmentState {

  private int[][] ship;
  private int[] sabotageRooms;
  private int[] crewPosition;
  private int agentPosition;
  private int agentEnergy;
  private int envTotalCrew;

  public ImpostorEnvironmentState(int[][] m) {
    ship = m;
  }

  public ImpostorEnvironmentState() {
    ship = GameStructure.SHIP;
    crewPosition = new int[GameStructure.TOTAL_ROOMS];
    this.initState();
  }

  /**
   * This method is used to setup the initial real world.
   */
  @Override
  public void initState() {

    GameStructure shipStructure = new GameStructure();
    shipStructure.init();

    this.setAgentPosition(GameStructure.INITIAL_AGENT_POSITION);
    this.setAgentEnergy(GameStructure.INITIAL_AGENT_ENERGY);
    this.setEnvTotalCrew(GameStructure.INITIAL_TOTAL_CREW);
    this.setSabotageRooms(GameStructure.INITIAL_SABOTAGE_ROOMS.clone());
    this.setCrewPosition(GameStructure.INITIAL_CREW_POSITION.clone());
  }

  /**
   * String representation of the real world state.
   */
  @Override
  public String toString() {
    String str = "";

    str = str + "\n\nTOTAL TRIPULANTES = " + envTotalCrew;

    str = str + "\n\nTRIPULANTES=(";
    for (int i = 0; i < crewPosition.length; i++) {
      str = str + "[ " + crewPosition[i];
      str = str + " ]";
    }
    str = str + ")\n";

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

  private void setEnvTotalCrew(int envTotalCrew) {
    this.envTotalCrew = envTotalCrew;
  }

  public int getEnvTotalCrew() {
    return this.envTotalCrew;
  }

  public void setCrewPosition(int[] crewPosition) {
    this.crewPosition = crewPosition;
  }

  public int[] getCrewPosition() {
    return this.crewPosition;
  }

  public int getCrewInPosition(int pos) {
    return crewPosition[pos];
  }

  public void eliminateCrewFromEnvironment(int pos) {
    this.crewPosition[pos] = this.crewPosition[pos] - 1;
    this.envTotalCrew = this.envTotalCrew - 1;
  }

  public int getAgentEnergy() {
    return agentEnergy;
  }

  public void setAgentEnergy(int agentEnergy) {
    this.agentEnergy = agentEnergy;
  }

  public void consumeEnergy() {
    agentEnergy = agentEnergy - GameStructure.Q_CONSUME_ENERGY;
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
