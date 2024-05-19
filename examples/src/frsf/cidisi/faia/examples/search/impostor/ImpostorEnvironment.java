package frsf.cidisi.faia.examples.search.impostor;

import java.util.Random;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class ImpostorEnvironment extends Environment {

  public ImpostorEnvironment() {
    // Create the environment state
    this.environmentState = new ImpostorEnvironmentState();
  }

  @Override
  public ImpostorEnvironmentState getEnvironmentState() {
    return (ImpostorEnvironmentState) super.getEnvironmentState();
  }

  /**
   * This method is called by the simulator. Given the Agent, it creates
   * a new perception reading, for example, the agent position.
   * 
   * @param agent
   * @return A perception that will be given to the agent by the simulator.
   */
  @Override
  public Perception getPercept() {
    // Create a new perception to return
    ImpostorPerception perception = new ImpostorPerception();

    // Get the actual position of the agent to be able to create the perception
    int pos = this.getEnvironmentState().getAgentPosition();

    makeCrewRandomMovement();

    int[] crewFromEnvironment = this.getCrewInPosition();

    for (int row = 0; row < crewFromEnvironment.length; row++) {
      System.out.println("\nCrew State: " + crewFromEnvironment[row]);
    }

    // Set the perception sensors
    perception.setUpSensor(this.getUpPosition(pos));
    perception.setDownSensor(this.getDownPosition(pos));
    perception.setLeftSensor(this.getLeftPosition(pos));
    perception.setRightSensor(this.getRightPosition(pos));
    perception.setCrewSensor(this.getCrewInPosition());

    // Return the perception
    return perception;
  }

  private void makeCrewRandomMovement() {
    if (this.getEnvTotalCrew() > 0) {
      int[] crewFromEnvironment = this.getCrewInPosition();
      int[] newCrewPosition = new int[GameStructure.TOTAL_ROOMS];

      Random randomCiclePerception = new Random();

      // 0 = NO update : 1 = YES Update
      int newRandomCiclePerception = randomCiclePerception.nextInt(2);
      if (newRandomCiclePerception == 1) {

        for (int row = 0; row < crewFromEnvironment.length; row++) {
          System.out.println("\nCrew State: " + crewFromEnvironment[row]);
        }

        for (int i = 0; i < crewFromEnvironment.length; i++) {
          // Move crew
          int totalCrewInPosition = crewFromEnvironment[i];
          if (totalCrewInPosition > 0) {

            for (int crewIndex = 0; crewIndex < totalCrewInPosition; crewIndex++) {
              // Get random orientation
              Random random = new Random();
              int newRandomOrientation = random.nextInt(4);

              // Get new position
              int newPosition = GameStructure.SHIP[i][newRandomOrientation];
              if (newPosition != GameStructure.WALL) {
                newCrewPosition[newPosition] = newCrewPosition[newPosition] + 1;
              } else {
                newCrewPosition[i] = newCrewPosition[i] + 1;
              }
            }
          }
        }

        for (int row = 0; row < newCrewPosition.length; row++) {
          System.out.println("\nCrew Updated: " + newCrewPosition[row]);
        }

        // Update new crew position on env state
        this.setNewCrewPosition(newCrewPosition);
      }
    }
  }

  @Override
  public String toString() {
    return environmentState.toString();
  }

  @Override
  public boolean agentFailed(Action actionReturned) {

    ImpostorEnvironmentState impostorEnvironmentState = this.getEnvironmentState();

    int agentEnergy = impostorEnvironmentState.getAgentEnergy();

    // If the agent has no energy, he failed
    if (agentEnergy <= 0)
      return true;

    return false;
  }

  // The following methods are Impostor-specific:

  public int getUpPosition(int pos) {
    return this.getEnvironmentState().getUpPosition(pos);
  }

  public int getDownPosition(int pos) {
    return this.getEnvironmentState().getDownPosition(pos);
  }

  public int getLeftPosition(int pos) {
    return this.getEnvironmentState().getLeftPosition(pos);
  }

  public int getRightPosition(int pos) {
    return this.getEnvironmentState().getRightPosition(pos);
  }

  public int[] getCrewInPosition() {
    return this.getEnvironmentState().getCrewPosition();
  }

  public void setNewCrewPosition(int[] newCrewPosition) {
    this.getEnvironmentState().setCrewPosition(newCrewPosition);
  }

  public int getEnvTotalCrew() {
    return this.getEnvironmentState().getEnvTotalCrew();
  }

}
