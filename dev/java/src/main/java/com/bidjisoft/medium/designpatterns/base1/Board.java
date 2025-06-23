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

public class Board {

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
	private Cell[][] grid = new Cell[DIMENSION][DIMENSION];	
	List<Ship> fleet = new ArrayList<>();
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
		if (!Cell.isValid(position)) {					
			return Optional.empty();
		}		
		String strY = position.substring(0,1);
		int y = Board.indexOf(strY).get(); // already checked in Cell.isValid
		String strX = position.substring(1);		
		int x = Integer.parseInt(strX) - 1;
		return Optional.of(grid[y][x]);
	}
	
	public Cell[] getCells(String[] positions) {
		Cell[] res = new Cell[positions.length];
		for (int i=0;i<positions.length;i++) {
			Optional<Cell> optC = getCell(positions[i]);
			if (optC.isPresent()) {
				res[i] = optC.get();
			} else {
				System.err.println("[ERROR] invalid position " + positions[i]);
				return new Cell[0];
			}
		}
		return res;
	}
	
	private boolean hasShipOnPosition(Cell c) {
		return getShipOnPosition(c).isPresent();
	}
	
	private Optional<Ship> getShipOnPosition(Cell c) {
		return fleet.stream().filter(ship -> ship.isOnPosition(c)).findFirst();
	}
	
	private Optional<ShipPart> getShipPartOnPosition(Cell c) {
		Optional<Ship> optShip = getShipOnPosition(c);
		if (optShip.isEmpty()) {
			return Optional.empty();
		}
		return optShip.get().getPartOnPosition(c);		
	}
	// endregion
	
	// region overriden
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|\n");
		for (int y=0;y<Board.DIMENSION;y++) {
			res.append(LINES.get(y) + " ");
			for (int x=0;x<Board.DIMENSION;x++) {
				res.append("|");
				Cell cell = grid[y][x];
				Optional<ShipPart> optShipPart = getShipPartOnPosition(cell);
				if (optShipPart.isPresent()) {
					res.append(optShipPart.get().toString());
				} else {
					res.append(cell.toString());
				}				
			}
			res.append("|\n");
		}
		return res.toString();
	}
	// endregion
}
