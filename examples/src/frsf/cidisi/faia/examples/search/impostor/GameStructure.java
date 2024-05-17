package frsf.cidisi.faia.examples.search.impostor;

import java.util.HashMap;

import frsf.cidisi.faia.solver.search.*;

public class GameStructure {

  // Rooms codification
  public static final int ROOM_UPPER_ENGINE = 0;
  public static final int ROOM_SECURITY = 1;
  public static final int ROOM_LOWER_ENGINE = 2;
  public static final int ROOM_REACTOR = 3;
  public static final int HALL_A = 5;
  public static final int HALL_B = 6;
  public static final int HALL_C = 7;
  public static final int HALL_D = 8;
  public static final int HALL_E = 9;
  public static final int HALL_F = 4;
  public static final int ROOM_CAFETERIA = 10;
  public static final int ROOM_WEAPONS = 11;
  public static final int ROOM_NAVIGATION = 12;
  public static final int ROOM_O2 = 13;
  public static final int ROOM_ADMIN = 14;
  public static final int ROOM_SHIELDS = 15;
  public static final int ROOM_COMUNICATION = 16;
  public static final int ROOM_STORAGE = 17;
  public static final int ROOM_ELECTRICAL = 18;
  public static final int ROOM_MEDBAY = 19;

  public static final int WALL = -1;

  public static final HashMap<Integer, String> ROOMS = new HashMap<>();
  static {
    ROOMS.put(WALL, "Wall");

    ROOMS.put(ROOM_UPPER_ENGINE, "Upper Engine");
    ROOMS.put(ROOM_SECURITY, "Security");
    ROOMS.put(ROOM_LOWER_ENGINE, "Lower Engine");
    ROOMS.put(ROOM_REACTOR, "Reactor");
    ROOMS.put(ROOM_CAFETERIA, "Cafeteria");
    ROOMS.put(ROOM_WEAPONS, "Weapons");
    ROOMS.put(ROOM_NAVIGATION, "Navigation");
    ROOMS.put(ROOM_O2, "O2");
    ROOMS.put(ROOM_ADMIN, "Admin");
    ROOMS.put(ROOM_SHIELDS, "Shields");
    ROOMS.put(ROOM_COMUNICATION, "Comunications");
    ROOMS.put(ROOM_STORAGE, "Storage");
    ROOMS.put(ROOM_ELECTRICAL, "Electical");
    ROOMS.put(ROOM_MEDBAY, "Medbay");

    ROOMS.put(HALL_A, "Hall A");
    ROOMS.put(HALL_B, "Hall B");
    ROOMS.put(HALL_C, "Hall C");
    ROOMS.put(HALL_D, "Hall D");
    ROOMS.put(HALL_E, "Hall E");
    ROOMS.put(HALL_F, "Hall F");
  }

  // Orientation codification
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;

  // Game data structure
  public static final int[][] SHIP = new int[5][4];
  public static final int[] AGENT_ORIENTATION = new int[4];
  public static final int[] CREW_PER_ROOM = new int[5];

  // Initial agent values
  public static final int INITIAL_AGENT_ENERGY = 30;
  public static final int INITIAL_AGENT_POSITION = ROOM_SECURITY;

  // Initial room values
  public static final int[] INITIAL_SABOTAGE_ROOMS = new int[] { ROOM_REACTOR, ROOM_LOWER_ENGINE };

  // Initial crew values
  public static final int INITIAL_TOTAL_CREW = 5;
  public static final int[] INITIAL_CREW_POSITION = new int[] { 1, 1, 1, 1, 1 };
  

  // Initial action values
  public static final int Q_CONSUME_ENERGY = 1;
  public static final int ACTION_ELIMINATE_COST = 1;
  public static final int ACTION_SABOTAGE_COST = 1;
  public static final int ACTION_MOVE_COST = 10;

  public GameStructure() {
  }

  public void init() {
    SHIP[ROOM_UPPER_ENGINE][UP] = WALL;
    SHIP[ROOM_UPPER_ENGINE][DOWN] = HALL_F;
    SHIP[ROOM_UPPER_ENGINE][LEFT] = WALL;
    SHIP[ROOM_UPPER_ENGINE][RIGHT] = WALL;

    // Security = 1
    SHIP[ROOM_SECURITY][UP] = WALL;
    SHIP[ROOM_SECURITY][DOWN] = WALL;
    SHIP[ROOM_SECURITY][LEFT] = HALL_F;
    SHIP[ROOM_SECURITY][RIGHT] = WALL;

    // Lower Engine = 2
    SHIP[ROOM_LOWER_ENGINE][UP] = HALL_F;
    SHIP[ROOM_LOWER_ENGINE][DOWN] = WALL;
    SHIP[ROOM_LOWER_ENGINE][LEFT] = WALL;
    SHIP[ROOM_LOWER_ENGINE][RIGHT] = WALL;

    // Reactor = 3
    SHIP[ROOM_REACTOR][UP] = WALL;
    SHIP[ROOM_REACTOR][DOWN] = WALL;
    SHIP[ROOM_REACTOR][LEFT] = WALL;
    SHIP[ROOM_REACTOR][RIGHT] = HALL_F;

    // Hall F = 4
    SHIP[HALL_F][UP] = ROOM_UPPER_ENGINE;
    SHIP[HALL_F][DOWN] = ROOM_LOWER_ENGINE;
    SHIP[HALL_F][LEFT] = ROOM_REACTOR;
    SHIP[HALL_F][RIGHT] = ROOM_SECURITY;

    // // Hall A =
    // SHIP[HALL_A][UP] = ROOM_WEAPONS;
    // SHIP[HALL_A][DOWN] = ROOM_SHIELDS;
    // SHIP[HALL_A][LEFT] = ROOM_O2;
    // SHIP[HALL_A][RIGHT] = ROOM_NAVIGATION;

    // // Hall B =
    // SHIP[HALL_B][UP] = WALL;
    // SHIP[HALL_B][DOWN] = ROOM_COMUNICATION;
    // SHIP[HALL_B][LEFT] = ROOM_STORAGE;
    // SHIP[HALL_B][RIGHT] = ROOM_SHIELDS;

    // // Hall C =
    // SHIP[HALL_C][UP] = ROOM_CAFETERIA;
    // SHIP[HALL_C][DOWN] = ROOM_STORAGE;
    // SHIP[HALL_C][LEFT] = WALL;
    // SHIP[HALL_C][RIGHT] = ROOM_ADMIN;

    // // Hall D =
    // SHIP[HALL_D][UP] = ROOM_ELECTRICAL;
    // SHIP[HALL_D][DOWN] = WALL;
    // SHIP[HALL_D][LEFT] = ROOM_LOWER_ENGINE;
    // SHIP[HALL_D][RIGHT] = ROOM_STORAGE;

    // // Hall E =
    // SHIP[HALL_E][UP] = WALL;
    // SHIP[HALL_E][DOWN] = ROOM_MEDBAY;
    // SHIP[HALL_E][LEFT] = ROOM_UPPER_ENGINE;
    // SHIP[HALL_E][RIGHT] = ROOM_CAFETERIA;

    // // Cafeteria =
    // SHIP[ROOM_CAFETERIA][UP] = WALL;
    // SHIP[ROOM_CAFETERIA][DOWN] = HALL_C;
    // SHIP[ROOM_CAFETERIA][LEFT] = HALL_E;
    // SHIP[ROOM_CAFETERIA][RIGHT] = ROOM_WEAPONS;

    // // Weapons =
    // SHIP[ROOM_WEAPONS][UP] = WALL;
    // SHIP[ROOM_WEAPONS][DOWN] = HALL_A;
    // SHIP[ROOM_WEAPONS][LEFT] = ROOM_CAFETERIA;
    // SHIP[ROOM_WEAPONS][RIGHT] = WALL;

    // // Navigation =
    // SHIP[ROOM_NAVIGATION][UP] = WALL;
    // SHIP[ROOM_NAVIGATION][DOWN] = WALL;
    // SHIP[ROOM_NAVIGATION][LEFT] = HALL_A;
    // SHIP[ROOM_NAVIGATION][RIGHT] = WALL;

    // // O2 =
    // SHIP[ROOM_O2][UP] = WALL;
    // SHIP[ROOM_O2][DOWN] = WALL;
    // SHIP[ROOM_O2][LEFT] = WALL;
    // SHIP[ROOM_O2][RIGHT] = HALL_A;

    // // Shields =
    // SHIP[ROOM_SHIELDS][UP] = HALL_A;
    // SHIP[ROOM_SHIELDS][DOWN] = WALL;
    // SHIP[ROOM_SHIELDS][LEFT] = HALL_B;
    // SHIP[ROOM_SHIELDS][RIGHT] = WALL;

    // // Communication =
    // SHIP[ROOM_COMUNICATION][UP] = HALL_B;
    // SHIP[ROOM_COMUNICATION][DOWN] = WALL;
    // SHIP[ROOM_COMUNICATION][LEFT] = WALL;
    // SHIP[ROOM_COMUNICATION][RIGHT] = WALL;

    // // Storage =
    // SHIP[ROOM_STORAGE][UP] = HALL_C;
    // SHIP[ROOM_STORAGE][DOWN] = WALL;
    // SHIP[ROOM_STORAGE][LEFT] = HALL_D;
    // SHIP[ROOM_STORAGE][RIGHT] = HALL_B;

    // // Admin =
    // SHIP[ROOM_ADMIN][UP] = WALL;
    // SHIP[ROOM_ADMIN][DOWN] = WALL;
    // SHIP[ROOM_ADMIN][LEFT] = HALL_C;
    // SHIP[ROOM_ADMIN][RIGHT] = WALL;

    // // Electrical =
    // SHIP[ROOM_ELECTRICAL][UP] = WALL;
    // SHIP[ROOM_ELECTRICAL][DOWN] = HALL_D;
    // SHIP[ROOM_ELECTRICAL][LEFT] = WALL;
    // SHIP[ROOM_ELECTRICAL][RIGHT] = WALL;

  }

  public static int getShipPosition(int room, int orientation) {
    return SHIP[room][orientation];
  }

  public static int[] getRowShipPosition(int room) {
    return new int[] { SHIP[room][UP], SHIP[room][DOWN], SHIP[room][LEFT], SHIP[room][RIGHT] };
  }

  /**
   * Possible values for search output:
   * 
   * - WHITHOUT_TREE
   * - XML_TREE
   * - PDF_TREE
   * - GRAPHICAL_TREE
   * - GRAPHVIZ_TREE
   * - EFAIA_TREE
   * 
   */
  public static final int VISIBLE_TREE = Search.XML_TREE;

  /**
   * Possible search strategies:
   * 
   * - BreathFirstSearch
   * - DepthFirstSearch
   * - UniformCostSearch
   * 
   */
  public static Strategy getSearchStrategy() {
    return new BreathFirstSearch();
  }
}
