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
public class ScheduledTasksCronTest {

	private static final Duration TEST_DURATION = TEN_SECONDS;
	
	@SpyBean
	private ScheduledTasksCron tasks;
	
	@Test
	public void cronExample() {
		// arrange
		// CRON CONFIGURATION */5.
		final int cronSetupEveryFiveSeconds = 5;
		
		// this allow us to calculate the number of invocations
		final int validationDurationInSeconds = (int) TEST_DURATION.getSeconds();

		final int minNumberOfInvocations = Math.round(validationDurationInSeconds / cronSetupEveryFiveSeconds);
		final int maxNumberOfInvocations = minNumberOfInvocations + 1;
		
		// act - is launch by scheduler
		
		// assert
		await()
			// MAX TEST TIME
			.atMost(TEST_DURATION)
			// VERIFICATION
			.untilAsserted(() -> 
				// EXPECTED MIN INVOCATIONS
				verify(tasks, atLeast(minNumberOfInvocations)).cronExample()
			);
		
		// EXPECTED MAX INVOCATIONS DURING TEST.
		verify(tasks, atMost(maxNumberOfInvocations)).cronExample();
	}
}
