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

import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.TEN_SECONDS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atMost;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class ScheduledTasksTest {

	private static final Duration TEST_DURATION = TEN_SECONDS;
	
	private static final Duration TEST_INITIAL_DELAY = Duration.ofMillis(3 * 1000);

	/**
	 * Subject Under Test
	 */
	@SpyBean
	private ScheduledTasks tasks;

	@Test
	public void reportCurrentTime() {
		// arrange
		// FIXED_RATE_MILLIS 5 seconds.
		final int fixedRateSeconds = 5;
		
		// this allow us to calculate the number of invocations
		final int validationDurationInSeconds = (int) (TEST_DURATION.getSeconds() - TEST_INITIAL_DELAY.getSeconds());
		
		final int minNumberOfInvocations = Math.round(validationDurationInSeconds / fixedRateSeconds);
		final int maxNumberOfInvocations = minNumberOfInvocations + 1;
		
		// act - is launch by scheduler
		
		// assert
		await()
			// INITIAL DELAY
			.atLeast(TEST_INITIAL_DELAY)
			// MAX TEST TIME
			.atMost(TEST_DURATION)
			// VERIFICATION
			.untilAsserted(() -> 
				// EXPECTED MIN INVOCATIONS
				verify(tasks, atLeast(minNumberOfInvocations)).reportCurrentTime()
			);
		
		// EXPECTED MAX INVOCATIONS DURING TEST.
		verify(tasks, atMost(maxNumberOfInvocations)).reportCurrentTime();
	}

}
