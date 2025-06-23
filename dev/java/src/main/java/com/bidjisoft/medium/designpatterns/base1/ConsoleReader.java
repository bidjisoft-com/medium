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
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class ConsoleReader {
	private static Scanner scanner = new Scanner(System.in);

	public static String[] readPosition(int length) {
		BiFunction<String[], String, String[]> copy = (array, newValue) -> {
			Stream<String> s = Arrays.stream(array).filter(Objects::nonNull).flatMap(e -> Stream.of(e,newValue));
			return s.toArray(String[]::new);
		};
		
		int i = 0;
		String[] res = new String[length];
		while (i < length) {
			System.out.print(String.format("Enter position (%s/%s): ", (i+1), length));
			String pos = scanner.next();
			if (!Cell.isValid(pos)) {					
				continue;
			} else if (i > 0 && !Cell.areJuxtaposed(res[i-1], pos)) {					
				continue;
			} else if (i > 0 && !Cell.areAligned(copy.apply(res, pos))) { 				
				continue;
			}
			res[i] = pos;			
			i++;
		}
		return res;
	}	
}
