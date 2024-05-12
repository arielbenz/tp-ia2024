package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import java.util.Random;

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

    crewPositionUpdate();

    // Get the actual position of the agent to be able to create the perception
    int pos = this.getEnvironmentState().getAgentPosition();

    // Set the perception sensors
    perception.setUpSensor(this.getUpPosition(pos));
    perception.setDownSensor(this.getDownPosition(pos));
    perception.setLeftSensor(this.getLeftPosition(pos));
    perception.setRightSensor(this.getRightPosition(pos));
    perception.setCrewSensor(this.getCrewPosition(pos));

    // Return the perception
    return perception;
  }

  private void crewPositionUpdate() {
    Random random = new Random();

    // Itera en cada posicion del crewPosition (cada habitacion)
    for (int i = 0; i < GameStructure.CREW_PER_ROOM.length; i++) {

      int amountCrewInRoom = ((ImpostorEnvironmentState) this.environmentState).getCrewPosition(i);
      // Verifica si hay un tripulante en la habitacion
      if (amountCrewInRoom > 0) {

        // Itera por cada tripulante en la misma habitacion
        for (int j = 0; j < amountCrewInRoom; j++) {

          // Aca se podria considerar si la habitacion a mover es la misma, que vuelva a
          // generar otro valor
          int newPosition = random.nextInt(GameStructure.CREW_PER_ROOM.length);
          // while (newPosition == i) {
          // newPosition = random.nextInt(crewPosition.length);
          // }
          // Hace el cambio efectivo de posicion
          ((ImpostorEnvironmentState) this.environmentState).setCrewPosition(i, amountCrewInRoom - 1);

          int amountCrewNewPosition = ((ImpostorEnvironmentState) this.environmentState).getCrewPosition(newPosition);
          ((ImpostorEnvironmentState) this.environmentState).setCrewPosition(newPosition, amountCrewNewPosition + 1);
        }
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
    return agentEnergy <= 0;
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

  public int getCrewPosition(int pos) {
    return ((ImpostorEnvironmentState) this.environmentState).getCrewPosition(pos);
  }

}
