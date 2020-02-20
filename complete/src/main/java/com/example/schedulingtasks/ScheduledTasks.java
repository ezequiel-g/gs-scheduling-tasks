/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	/**
	 * initial delay 3 seconds in milliseconds.
	 */
	private static final int INITIAL_DELAY = 3 * 1000;

	/**
	 * fixed rate 5 seconds in milliseconds.
	 */
	private static final int FIXED_RATE = 5 * 1000;

	@Scheduled(initialDelay = INITIAL_DELAY, fixedRate = FIXED_RATE)
	public void reportCurrentTime() {
		final String timeAsString = new SimpleDateFormat("HH:mm:ss").format(new Date());
		log.info("#reportCurrentTime()# The time is now {}", timeAsString);
	}

}
