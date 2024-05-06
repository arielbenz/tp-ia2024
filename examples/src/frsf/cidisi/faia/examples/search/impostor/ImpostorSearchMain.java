package frsf.cidisi.faia.examples.search.impostor;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class ImpostorSearchMain {

  public static void main(String[] args) throws PrologConnectorException {

    Constants constan = new Constants();

    ImpostorAgent impostorAgent = new ImpostorAgent();

    ImpostorEnvironment impostorEnvironment = new ImpostorEnvironment();

    SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(impostorEnvironment, impostorAgent);

    simulator.start();
  }
}
