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
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Game {

	// region variables	
	private Board board; 	
	// endregion
	
	// region constructors	
	public Game() {
		this.board = new Board();
	}
	// endregion

	// region getters and setters
	public Board getBoard() {
		return this.board;
	}
	// endregion

	// region private methods
	private boolean init() {
		ConsoleWriter.clearAndPrintln(this.board.toString());			
		
		board.fleet.add(new Submarine("Le Triomphant"));
		board.fleet.add(new Destroyer("Le Fantasque"));
		
		Consumer<Ship> initHandler = ship -> {
			ConsoleWriter.println("===== " + ship.getType() + " '" + ship.getName() + "' =====");
			String[] position = ConsoleReader.readPosition(ship.getLength());
			Cell[] cells = this.board.getCells(position);
			ship.setPosition(cells);
			ConsoleWriter.clearAndPrintln(this.board.toString());
		};

		board.fleet.stream()
		    .sorted()
		    .forEach(initHandler);
		return true;
	}
	// endregion
	
	// region main
	public static void main(String[] args) {
		try {	
			// init
			Game game = new Game();		
			if (!game.init()) {
				System.err.println("[ERROR] an error occured. Please contact the administrator.");
				return;
			}	
			// run ...
		} catch (Exception e) {
			System.err.println("[FATAL] " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConsoleWriter.println("Thank you for playing Battleship");
		}
	}
	// endregion
	
	
}
