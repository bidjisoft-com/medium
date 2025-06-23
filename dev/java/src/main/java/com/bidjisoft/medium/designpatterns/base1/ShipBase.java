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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//	CARRIER, 6
//	BATTLESHIP, 5
//	CRUISER, 4
//	DESTROYER, 3
//	SUBMARINE, 2
public abstract class ShipBase implements Ship {

	// region static
	public static int IDS = 0;
	// endregion
	
	
	// region variables
	private int id;	
	private String name;
	private Integer length;
	private Set<ShipPart> parts = new HashSet<ShipPart>();
	// endregion
	
	// region constructors
	protected ShipBase(int length, String name) {
		IDS++;
		this.id = IDS;
		this.name = name;
		this.length = length;
	}
	// endregion
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	// region getters and setters
	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public int getLength() {
		return this.length;
	}
	
	public void setPosition(Cell[] positions) { // A1, ...
		for (Cell cell : positions) {
			parts.add(new ShipPart(cell, this));
		}
	}
	
	public boolean isOnPosition(Cell c) {
		return parts.contains(c); 
	}
	
	public Optional<ShipPart> getPartOnPosition(Cell c) {
		return parts.stream().filter(p -> p.equals(c)).findFirst();
	}
	// endregion
	
	// region overriden
	@Override
	public int compareTo(Ship s) {
		if (this.length < s.getLength()) {
			return -1;
		} else if (this.length > s.getLength()) {
			return 1;
		}
		return this.name.compareTo(s.getName());
	}
	
	@Override
	public String toString() {
		return String.format("%s#%s", name, id);
	}
	// endregion
}

class Submarine extends ShipBase {
	public static final int LENGTH = 2;

	public Submarine(String name) {
		super(LENGTH, name);
	}
	
	public void dive() {
		
	}
}

class Destroyer extends ShipBase {
	public static final int LENGTH = 3;

	public Destroyer(String name) {
		super(LENGTH, name);
	}
}
