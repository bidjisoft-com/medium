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

public class ConsoleWriter {

	public static void clear() {
		// ANSI escape codes: 
	    // H : move to top of the screen
		// 2J: clear entire screen
		System.out.print("\033[H\033[2J");
	}
	
	public static void clearAndPrintln(String content) {
		ConsoleWriter.clear();
		System.out.println(content);
	}
	
	public static void println(String content) {
		System.out.println(content);
	}
	
}
