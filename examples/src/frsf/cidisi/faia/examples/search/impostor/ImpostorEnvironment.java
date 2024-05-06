package frsf.cidisi.faia.examples.search.impostor;

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

    // Set the perception sensors
    perception.setUpSensor(this.getUpPosition(pos));
    perception.setDownSensor(this.getDownPosition(pos));
    perception.setLeftSensor(this.getLeftPosition(pos));
    perception.setRightSensor(this.getRightPosition(pos));

    // Return the perception
    return perception;
  }

  @Override
  public String toString() {
    return environmentState.toString();
  }

  @Override
  public boolean agentFailed(Action actionReturned) {

    ImpostorEnvironmentState impostorEnvironmentState = this.getEnvironmentState();

    int agentEnergy = impostorEnvironmentState.getAgentEnergy();

    // FIXME: The impostor agent always has the same energy
    // If the agent has no energy, he failed
    if (agentEnergy <= 0)
      return true;

    return false;
  }

  // The following methods are Impostor-specific:

  public int getUpPosition(int pos) {
    return ((ImpostorEnvironmentState) this.environmentState).getUpPosition(pos);
  }
  
  public int getDownPosition(int pos) {
    return ((ImpostorEnvironmentState) this.environmentState).getDownPosition(pos);
  }

  public int getLeftPosition(int pos) {
    return ((ImpostorEnvironmentState) this.environmentState).getLeftPosition(pos);
  }

  public int getRightPosition(int pos) {
    return ((ImpostorEnvironmentState) this.environmentState).getRightPosition(pos);
  }

}
