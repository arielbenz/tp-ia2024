package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class ImpostorPerception extends Perception {

  public static int EMPTY_PERCEPTION = 0;

  public static int WALL = -1;

  private int upSensor;
  private int downSensor;
  private int leftSensor;
  private int rightSensor;
  private int energy;

  public ImpostorPerception() {
    energy = Constants.START_ENERGY;
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

    int pos = environmentState.getAgentPosition();

    this.setUpSensor(impostorEnvironment.getUpPosition(pos));
    this.setLeftSensor(impostorEnvironment.getLeftPosition(pos));
    this.setRightSensor(impostorEnvironment.getRightPosition(pos));
    this.setDownSensor(impostorEnvironment.getDownPosition(pos));
  }

  // The following methods are Impostor-specific:

  public int getLeftSensor() {
    return leftSensor;
  }

  public void setLeftSensor(int leftSensor) {
    this.leftSensor = leftSensor;
  }

  public int getUpSensor() {
    return upSensor;
  }

  public void setUpSensor(int upSensor) {
    this.upSensor = upSensor;
  }

  public int getRightSensor() {
    return rightSensor;
  }

  public void setRightSensor(int rightSensor) {
    this.rightSensor = rightSensor;
  }

  public int getDownSensor() {
    return downSensor;
  }

  public void setDownSensor(int downSensor) {
    this.downSensor = downSensor;
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

    str.append("\n" + "* Energía: " + this.energy + "\n");
    str.append("* Sensor Arriba: " + "(" + this.upSensor + ")" + " : " + Constants.ROOMS.get(this.upSensor) + "\n");
    str.append("* Sensor Abajo: " + "(" + this.downSensor + ")" + " : " + Constants.ROOMS.get(this.downSensor) + "\n");
    str.append("* Sensor Izquierda: " + "(" + this.leftSensor + ")" + " : " + Constants.ROOMS.get(this.leftSensor) + "\n");
    str.append("* Sensor Derecha: " + "(" + this.rightSensor + ")" + " : " + Constants.ROOMS.get(this.rightSensor) + "\n");

    return str.toString();
  }
}
