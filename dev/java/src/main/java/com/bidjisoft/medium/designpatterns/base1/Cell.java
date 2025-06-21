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

import java.util.Optional;

public abstract class Cell {

	// region static
	public static boolean isValid(String position) {		
		if (position.length() < 2) {
			System.err.println("bad position: " + position);
			return false;
		}
		String strY = position.substring(0,1);
		Optional<Integer> optY = Board.indexOf(strY);
		if (optY.isEmpty()) {
			return false;
		}
		int y = optY.get();
		String strX = position.substring(1);		
		int x = Integer.parseInt(strX) - 1;
		if (x < 0 || x >= Board.DIMENSION) {
			System.err.println("bad position: " + position);
			return false;
		} else if (y < 0 || y >= Board.DIMENSION) {
			System.err.println("bad position: " + position);
			return false;
		}
		return true;
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


