/**
 * 
 */
package com.statistics.statisticsgenerator.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for <Util> class
 * @author Teena
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {
	
	/**
	 * Test for isWithinLastSixtySeconds() method - for a positive scenario
	 */
	@Test
	public void testIsWithinLastSixtySeconds() {
		
		boolean flag=Util.isWithinLastSixtySeconds(System.currentTimeMillis());
		assertEquals(true,flag);
	}
	/**
	 * Test for isWithinLastSixtySeconds() method - for a negative scenario
	 */
	@Test
	public void testIsWithinLastSixtySecondsForFalse() {
		
		boolean flag=Util.isWithinLastSixtySeconds(System.currentTimeMillis()+600000);
		assertEquals(false,flag);
	}
	
}
