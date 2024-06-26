/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa,
 * Luis Ignacio Larrateguy y Milton Pividori.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package frsf.cidisi.faia.examples.search.pacman.actions;

import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoDown extends SearchAction {

    /**
     * See comments in the Eat class.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        PacmanAgentState pacmanState = (PacmanAgentState) s;

        // Increase the action cost count
        pacmanState.increaseVisitedCellsCount();

        int row = pacmanState.getRowPosition();
        int col = pacmanState.getColumnPosition();

        // Check the limits of the world
        if (row == 3) {
            row = 0;
        } else {
            row = row + 1;
        }

        pacmanState.setRowPosition(row);

        /* The agent can always go down */
        if (pacmanState.getWorldPosition(row, col) ==
                PacmanPerception.UNKNOWN_PERCEPTION) {

            pacmanState.setWorldPosition(row, col,
                    PacmanPerception.EMPTY_PERCEPTION);
        }

        return pacmanState;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        PacmanEnvironmentState environmentState = (PacmanEnvironmentState) est;
        PacmanAgentState pacmanState = ((PacmanAgentState) ast);

        pacmanState.increaseVisitedCellsCount();

        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        if (row == 3) {
            row = 0;
        } else {
            row = row + 1;
        }

        pacmanState.setRowPosition(row);

        environmentState.setAgentPosition(new int[] {row, col});
        
        return environmentState;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public String toString() {
        return "GoDown";
    }
}
