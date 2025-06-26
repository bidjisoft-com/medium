/*
 * Sample code for Medium articles.
 * Copyright 2025, Bidjisoft.
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.bidjisoft.medium.designpatterns.base1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class Board {

	private static final Logger LOGGER = Logger.getLogger(Board.class.getName());

	// region static
	public static final int DIMENSION = 10;
	public static final List<String> LINES = Arrays.asList(new String[]{"A","B","C","D","E","F","G","H","I","J"});
	
	public static Optional<Integer> indexOf(String lineName) {
		if (lineName.length() != 1) {
			return Optional.empty();
		} else if (!LINES.contains(lineName)) {	
			return Optional.empty();
		}
		return Optional.of(LINES.indexOf(lineName));
	}
	// endregion
	
	// region variables
	private final Cell[][] grid = new Cell[DIMENSION][DIMENSION];	
	private final List<Ship> fleet = new ArrayList<>();
	// endregion
	
	// region constructors
	public Board() {
		for (int y=0;y<DIMENSION;y++) {
			for (int x=0;x<DIMENSION;x++) {
				grid[y][x] = new BoardCell(x,y);
			}
		}
	}
	// endregion
	
	// region methods
	private Optional<Cell> getCell(String position) {
		return Cell.getCoordinates(position)
                .map(coords -> grid[coords[1]][coords[0]]);
	}
	
	public Optional<Cell[]> getCells(String[] positions) {
		Cell[] res = new Cell[positions.length];
		for (int i=0;i<positions.length;i++) {
			Optional<Cell> optC = getCell(positions[i]);
			if (optC.isEmpty()) {
				LOGGER.warning("[ERROR] invalid position " + positions[i]);
                return Optional.empty();
			}
			res[i] = optC.get();
		}
		return Optional.of(res);
	}
	
	public List<Ship> getShips() {
		return new ArrayList<Ship>(this.fleet);
	}
	
	public void addShip(Ship ship) { 
		this.fleet.add(ship); 
	}
	
	private boolean hasShipOnPosition(Cell c) {
		return getShipOnPosition(c).isPresent();
	}
	
	private Optional<Ship> getShipOnPosition(Cell c) {
		return fleet.stream().filter(ship -> ship.isOnPosition(c)).findFirst();
	}
	
	private Optional<ShipPart> getShipPartOnPosition(Cell c) {
		return getShipOnPosition(c).flatMap(ship -> ship.getPartOnPosition(c));		
	}
	// endregion
	
	// region rendering
	public String render() {
		StringBuilder res = new StringBuilder();
		res.append("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|\n");
		for (int y=0;y<Board.DIMENSION;y++) {
			res.append(LINES.get(y) + " ");
			for (int x=0;x<Board.DIMENSION;x++) {
				res.append("|");
				Cell cell = grid[y][x];
				res.append(getShipPartOnPosition(cell).map(Object::toString).orElse(cell.toString()));				
			}
			res.append("|\n");
		}
		return res.toString();
	}
	// endregion
}
