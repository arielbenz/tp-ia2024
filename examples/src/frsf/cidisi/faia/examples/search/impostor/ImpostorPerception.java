package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class ImpostorPerception extends Perception {

  private int upSensor;
  private int downSensor;
  private int leftSensor;
  private int rightSensor;

  public ImpostorPerception() {
    super();
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

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("* Sensor Arriba: " + "(" + this.upSensor + ")" + " : " + GameStructure.ROOMS.get(this.upSensor) + "\n");
    str.append("* Sensor Abajo: " + "(" + this.downSensor + ")" + " : " + GameStructure.ROOMS.get(this.downSensor) + "\n");
    str.append(
        "* Sensor Izquierda: " + "(" + this.leftSensor + ")" + " : " + GameStructure.ROOMS.get(this.leftSensor) + "\n");
    str.append(
        "* Sensor Derecha: " + "(" + this.rightSensor + ")" + " : " + GameStructure.ROOMS.get(this.rightSensor) + "\n");

    return str.toString();
  }
}
