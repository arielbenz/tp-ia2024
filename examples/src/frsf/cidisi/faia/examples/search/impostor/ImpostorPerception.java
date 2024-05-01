package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class ImpostorPerception extends Perception {

  public static int UNKNOWN_PERCEPTION = -1;
  public static int EMPTY_PERCEPTION = 0;
  public static int ENEMY_PERCEPTION = 1;
  public static int FOOD_PERCEPTION = 2;

  public static int ROOM_LOWER_ENGINE = 10;
  public static int ROOM_SECURITY = 11;
  public static int ROOM_REACTOR = 12;
  public static int ROOM_UPPER_ENGINE = 13;
  public static int HALL_F = 20;
  public static int WALL = -1;

  private int leftSensor;
  private int topSensor;
  private int rightSensor;
  private int bottomSensor;
  private int energy;

  public ImpostorPerception() {
    energy = 50;
  }

  public ImpostorPerception(Agent agent, Environment environment) {
    super(agent, environment);
  }

  /**
   * This method is used to setup the perception.
   */
  @Override
  public void initPerception(Agent agent, Environment environment) {
    ImpostorEnvironment impostorEnvironment = (ImpostorEnvironment) environment;
    ImpostorEnvironmentState environmentState = impostorEnvironment.getEnvironmentState();

    int row = environmentState.getAgentPosition()[0];
    int col = environmentState.getAgentPosition()[1];

    this.setTopSensor(impostorEnvironment.getTopPosition(row, col));
    this.setLeftSensor(impostorEnvironment.getLeftPosition(row, col));
    this.setRightSensor(impostorEnvironment.getRightPosition(row, col));
    this.setBottomSensor(impostorEnvironment.getBottomPosition(row, col));
  }

  // The following methods are Impostor-specific:

  public int getLeftSensor() {
    return leftSensor;
  }

  public void setLeftSensor(int leftSensor) {
    this.leftSensor = leftSensor;
  }

  public int getTopSensor() {
    return topSensor;
  }

  public void setTopSensor(int topSensor) {
    this.topSensor = topSensor;
  }

  public int getRightSensor() {
    return rightSensor;
  }

  public void setRightSensor(int rightSensor) {
    this.rightSensor = rightSensor;
  }

  public int getBottomSensor() {
    return bottomSensor;
  }

  public void setBottomSensor(int bottomSensor) {
    this.bottomSensor = bottomSensor;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("Energy: " + this.energy);
    str.append("; ");
    str.append("Left Sensor: " + this.leftSensor);
    str.append("; ");
    str.append("Up Sensor: " + this.topSensor);
    str.append("; ");
    str.append("Right Sensor: " + this.rightSensor);
    str.append("; ");
    str.append("Down Sensor: " + this.bottomSensor);

    return str.toString();
  }
}
