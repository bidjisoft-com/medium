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

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public abstract class Cell {

	// region static
	public static boolean isValid(String position) {		
		return getCoordinates(position).isPresent();
	}
	
	public static boolean areJuxtaposed(String currentPosition, String nextPosition) {
		Optional<int[]> optCoordinates1 = getCoordinates(currentPosition);
		Optional<int[]> optCoordinates2 = getCoordinates(nextPosition);
		if (optCoordinates1.isEmpty()) {
			return false;
		} else if (optCoordinates2.isEmpty()) {
			return false;
		}
		int x1 = optCoordinates1.get()[0];
		int x2 = optCoordinates2.get()[0];
		int y1 = optCoordinates1.get()[1];
		int y2 = optCoordinates2.get()[1];
 		if (Math.abs(x2 - x1) <= 1 && Math.abs(y2 - y1) <= 1) {
 			return true;
 		} else if (Math.abs(((x2+1)%Board.DIMENSION) - ((x1+1)%Board.DIMENSION)) <= 1 && Math.abs(((y2+1)%Board.DIMENSION) - ((y1+1)%Board.DIMENSION)) <= 1) {
 			return true;
 		}
 		return false;
	}
	
	public static boolean areAligned(String[] positions) {
		if (positions == null || positions.length == 0) {
			throw new IllegalArgumentException("undefined positions");
		}
		Optional<int[]> optCoordinates0 = getCoordinates(positions[0]);
		if (optCoordinates0.isEmpty()) {
			throw new IllegalArgumentException("wrong coordinates at position[0]: " + positions[0]);
		}
		int x0 = optCoordinates0.get()[0];
		int y0 = optCoordinates0.get()[1];
		for (int i = 1; i < positions.length; i++) {
			Optional<int[]> optCoordinatesi = getCoordinates(positions[i-1]);
			if (optCoordinatesi.isEmpty()) {
				throw new IllegalArgumentException("wrong coordinates at position[" + i + "]: " + positions[i]);
			} 
			int xi = optCoordinatesi.get()[0];
			int yi = optCoordinatesi.get()[1];
			if (x0 != xi && y0 != yi) {
				return false;
			}
		}		
		return true;
	}
	
	private static Optional<int[]> getCoordinates(String position) {
		int[] res = new int[2];
		if (position.length() < 2) {
			System.err.println("bad position: " + position);
			return Optional.empty();
		}
		String strY = position.substring(0,1);
		Optional<Integer> optY = Board.indexOf(strY);
		if (optY.isEmpty()) {
			return Optional.empty();
		}
		int y = optY.get();
		String strX = position.substring(1);		
		int x = Integer.parseInt(strX) - 1;
		if (x < 0 || x >= Board.DIMENSION) {
			System.err.println("bad position: " + position);
			return Optional.empty();
		} else if (y < 0 || y >= Board.DIMENSION) {
			System.err.println("bad position: " + position);
			return Optional.empty();
		}
		res[0] = x;
		res[1] = y;
		return Optional.of(res);
	}
	// endregion
	
	// region variables	
	protected final Integer x;
	protected final Integer y;
	// endregion
	
	// region constructors
	protected Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	// endregion
	
	// region getters and setters	
	public final int getX() {
		return this.x;
	}
	
	public final int getY() {
		return this.y;
	}
	
	public String getPosition() {
		return Board.LINES.get(y) + (x+1);
	}
	// endregion
	
	// region overriden
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cell)) {
			return false;
		}
		Cell c = (Cell)obj;		
		return x.equals(c.getX()) && y.equals(c.getY());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
	}
	// endregion
}


