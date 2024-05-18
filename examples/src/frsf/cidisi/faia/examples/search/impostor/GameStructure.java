package frsf.cidisi.faia.examples.search.impostor;

import java.util.HashMap;

import frsf.cidisi.faia.solver.search.*;

public class GameStructure {

  // Rooms codification
  public static final int WALL = -1;

  public static final int ROOM_UPPER_ENGINE = 0;
  public static final int ROOM_SECURITY = 1;
  public static final int ROOM_LOWER_ENGINE = 2;
  public static final int ROOM_REACTOR = 3;
  public static final int HALL_F = 4;
  public static final int HALL_E = 5;
  public static final int ROOM_MEDBAY = 6;
  public static final int ROOM_CAFETERIA = 7;

  // public static final int ROOM_WEAPONS = 5;
  // public static final int ROOM_NAVIGATION = 6;
  // public static final int ROOM_O2 = 7;
  // public static final int ROOM_ADMIN = 8;
  // public static final int ROOM_SHIELDS = 9;
  // public static final int ROOM_COMUNICATION = 10;
  // public static final int ROOM_STORAGE = 11;
  // public static final int ROOM_ELECTRICAL = 12;

  // public static final int HALL_A = 14;
  // public static final int HALL_B = 15;
  // public static final int HALL_C = 16;
  // public static final int HALL_D = 17;

  public static final HashMap<Integer, String> ROOMS = new HashMap<>();
  static {
    ROOMS.put(WALL, "Wall");

    ROOMS.put(ROOM_UPPER_ENGINE, "Upper Engine");
    ROOMS.put(ROOM_SECURITY, "Security");
    ROOMS.put(ROOM_LOWER_ENGINE, "Lower Engine");
    ROOMS.put(ROOM_REACTOR, "Reactor");
    ROOMS.put(ROOM_CAFETERIA, "Cafeteria");
    // ROOMS.put(ROOM_WEAPONS, "Weapons");
    // ROOMS.put(ROOM_NAVIGATION, "Navigation");
    // ROOMS.put(ROOM_O2, "O2");
    // ROOMS.put(ROOM_ADMIN, "Admin");
    // ROOMS.put(ROOM_SHIELDS, "Shields");
    // ROOMS.put(ROOM_COMUNICATION, "Comunications");
    // ROOMS.put(ROOM_STORAGE, "Storage");
    // ROOMS.put(ROOM_ELECTRICAL, "Electical");
    ROOMS.put(ROOM_MEDBAY, "Medbay");
    // ROOMS.put(HALL_A, "Hall A");
    // ROOMS.put(HALL_B, "Hall B");
    // ROOMS.put(HALL_C, "Hall C");
    // ROOMS.put(HALL_D, "Hall D");
    ROOMS.put(HALL_E, "Hall E");
    ROOMS.put(HALL_F, "Hall F");
  }

  // Orientation codification
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;

  // Game data structure
  public static final int TOTAL_ROOMS = 6;
  public static final int[][] SHIP = new int[TOTAL_ROOMS][4];
  public static final int[] AGENT_ORIENTATION = new int[4];
  public static final int[] CREW_PER_ROOM = new int[TOTAL_ROOMS];

  // Initial agent values
  public static final int INITIAL_AGENT_ENERGY = 100;
  public static final int INITIAL_AGENT_POSITION = ROOM_SECURITY;

  // Initial room values
  public static final int[] INITIAL_SABOTAGE_ROOMS = new int[] { ROOM_REACTOR, ROOM_LOWER_ENGINE };

  // Initial crew values
  public static final int INITIAL_TOTAL_CREW = 5;
  public static final int[] INITIAL_CREW_POSITION = new int[TOTAL_ROOMS];

  public static final HashMap<Integer, Integer> CREW = new HashMap<>();
  static {
    CREW.put(ROOM_UPPER_ENGINE, 1);
    CREW.put(ROOM_SECURITY, 1);
    CREW.put(ROOM_LOWER_ENGINE, 1);
    CREW.put(ROOM_REACTOR, 1);
    CREW.put(HALL_F, 1);
    CREW.put(HALL_E, 0);
    // CREW.put(ROOM_MEDBAY, 0);
    // CREW.put(ROOM_CAFETERIA, 0);
    // CREW.put(ROOM_WEAPONS, 0);
    // CREW.put(ROOM_NAVIGATION, 0);
    // CREW.put(ROOM_O2, 0);
    // CREW.put(ROOM_ADMIN, 0);
    // CREW.put(ROOM_SHIELDS, 0);
    // CREW.put(ROOM_COMUNICATION, 0);
    // CREW.put(ROOM_STORAGE, 0);
    // CREW.put(ROOM_ELECTRICAL, 0);
    // CREW.put(HALL_A, 0);
    // CREW.put(HALL_B, 0);
    // CREW.put(HALL_C, 0);
    // CREW.put(HALL_D, 0);
  }

  // Initial action values
  public static final int Q_CONSUME_ENERGY = 1;
  public static final int ACTION_ELIMINATE_COST = 1;
  public static final int ACTION_SABOTAGE_COST = 1;
  public static final int ACTION_MOVE_COST = 10;

  public GameStructure() {
  }

  public void init() {

    for (int i = 0; i < TOTAL_ROOMS; i++) {
      INITIAL_CREW_POSITION[i] = CREW.get(i);
    }

    // Upper engine = 0
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

    // // Cafeteria = 4
    // SHIP[ROOM_CAFETERIA][UP] = WALL;
    // SHIP[ROOM_CAFETERIA][DOWN] = HALL_C;
    // SHIP[ROOM_CAFETERIA][LEFT] = HALL_E;
    // SHIP[ROOM_CAFETERIA][RIGHT] = ROOM_WEAPONS;

    // Cafeteria = 7
    // SHIP[ROOM_CAFETERIA][UP] = WALL;
    // SHIP[ROOM_CAFETERIA][DOWN] = WALL;
    // SHIP[ROOM_CAFETERIA][LEFT] = HALL_E;
    // SHIP[ROOM_CAFETERIA][RIGHT] = WALL;

    // // Weapons = 5
    // SHIP[ROOM_WEAPONS][UP] = WALL;
    // SHIP[ROOM_WEAPONS][DOWN] = HALL_A;
    // SHIP[ROOM_WEAPONS][LEFT] = ROOM_CAFETERIA;
    // SHIP[ROOM_WEAPONS][RIGHT] = WALL;

    // // Navigation = 6
    // SHIP[ROOM_NAVIGATION][UP] = WALL;
    // SHIP[ROOM_NAVIGATION][DOWN] = WALL;
    // SHIP[ROOM_NAVIGATION][LEFT] = HALL_A;
    // SHIP[ROOM_NAVIGATION][RIGHT] = WALL;

    // // O2 = 7
    // SHIP[ROOM_O2][UP] = WALL;
    // SHIP[ROOM_O2][DOWN] = WALL;
    // SHIP[ROOM_O2][LEFT] = WALL;
    // SHIP[ROOM_O2][RIGHT] = HALL_A;

    // // Shields = 8
    // SHIP[ROOM_SHIELDS][UP] = HALL_A;
    // SHIP[ROOM_SHIELDS][DOWN] = WALL;
    // SHIP[ROOM_SHIELDS][LEFT] = HALL_B;
    // SHIP[ROOM_SHIELDS][RIGHT] = WALL;

    // // Communication = 9
    // SHIP[ROOM_COMUNICATION][UP] = HALL_B;
    // SHIP[ROOM_COMUNICATION][DOWN] = WALL;
    // SHIP[ROOM_COMUNICATION][LEFT] = WALL;
    // SHIP[ROOM_COMUNICATION][RIGHT] = WALL;

    // // Storage = 10
    // SHIP[ROOM_STORAGE][UP] = HALL_C;
    // SHIP[ROOM_STORAGE][DOWN] = WALL;
    // SHIP[ROOM_STORAGE][LEFT] = HALL_D;
    // SHIP[ROOM_STORAGE][RIGHT] = HALL_B;

    // // Admin = 11
    // SHIP[ROOM_ADMIN][UP] = WALL;
    // SHIP[ROOM_ADMIN][DOWN] = WALL;
    // SHIP[ROOM_ADMIN][LEFT] = HALL_C;
    // SHIP[ROOM_ADMIN][RIGHT] = WALL;

    // // Electrical = 12
    // SHIP[ROOM_ELECTRICAL][UP] = WALL;
    // SHIP[ROOM_ELECTRICAL][DOWN] = HALL_D;
    // SHIP[ROOM_ELECTRICAL][LEFT] = WALL;
    // SHIP[ROOM_ELECTRICAL][RIGHT] = WALL;

    // Medbay = 6
    // SHIP[ROOM_MEDBAY][UP] = HALL_E;
    // SHIP[ROOM_MEDBAY][DOWN] = WALL;
    // SHIP[ROOM_MEDBAY][LEFT] = WALL;
    // SHIP[ROOM_MEDBAY][RIGHT] = WALL;

    // // Hall A = 14
    // SHIP[HALL_A][UP] = ROOM_WEAPONS;
    // SHIP[HALL_A][DOWN] = ROOM_SHIELDS;
    // SHIP[HALL_A][LEFT] = ROOM_O2;
    // SHIP[HALL_A][RIGHT] = ROOM_NAVIGATION;

    // // Hall B = 15
    // SHIP[HALL_B][UP] = WALL;
    // SHIP[HALL_B][DOWN] = ROOM_COMUNICATION;
    // SHIP[HALL_B][LEFT] = ROOM_STORAGE;
    // SHIP[HALL_B][RIGHT] = ROOM_SHIELDS;

    // // Hall C = 16
    // SHIP[HALL_C][UP] = ROOM_CAFETERIA;
    // SHIP[HALL_C][DOWN] = ROOM_STORAGE;
    // SHIP[HALL_C][LEFT] = WALL;
    // SHIP[HALL_C][RIGHT] = ROOM_ADMIN;

    // // Hall D = 17
    // SHIP[HALL_D][UP] = ROOM_ELECTRICAL;
    // SHIP[HALL_D][DOWN] = WALL;
    // SHIP[HALL_D][LEFT] = ROOM_LOWER_ENGINE;
    // SHIP[HALL_D][RIGHT] = ROOM_STORAGE;

    // Hall E = 5
    // SHIP[HALL_E][UP] = WALL;
    // SHIP[HALL_E][DOWN] = ROOM_MEDBAY;
    // SHIP[HALL_E][LEFT] = ROOM_UPPER_ENGINE;
    // SHIP[HALL_E][RIGHT] = ROOM_CAFETERIA;

    SHIP[HALL_E][UP] = WALL;
    SHIP[HALL_E][DOWN] = WALL;
    SHIP[HALL_E][LEFT] = ROOM_UPPER_ENGINE;
    SHIP[HALL_E][RIGHT] = WALL;

    // Hall F = 4
    SHIP[HALL_F][UP] = ROOM_UPPER_ENGINE;
    SHIP[HALL_F][DOWN] = ROOM_LOWER_ENGINE;
    SHIP[HALL_F][LEFT] = ROOM_REACTOR;
    SHIP[HALL_F][RIGHT] = ROOM_SECURITY;

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
    return new DepthFirstSearch();
  }
}
