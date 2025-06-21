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

import java.util.Scanner;

public class ConsoleReader {
	private static Scanner scanner = new Scanner(System.in);

	public static String[] readPosition(int length) {
		int i = 0;
		String[] res = new String[length];
		while (i < length) {
			System.out.print("Enter position (" + (i+1) + "/" + length + "): ");
			String pos = scanner.next();
			if (!Cell.isValid(pos)) {					
				continue;
			}
			res[i] = pos;			
			i++;
		}
		return res;
	}	
	
	public static String readDirection(String lastDirection) {
		String res = null;
		while (res == null) {
			System.out.print("Enter direction" + (lastDirection != null ? " (" + lastDirection + "): " : ":" ));
			String pos = scanner.next();
			if ((pos == null || pos.isBlank()) && (lastDirection != null && !lastDirection.isBlank())) {
				return lastDirection;
			} 
			res = pos;			
		}
		return res;
	}
}
