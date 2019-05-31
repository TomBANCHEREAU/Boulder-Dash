package com.TomBAN.BoulderDash.Game.Model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringMapTest {
	static int CORRECT_WIDTH, CORRECT_HEIGHT, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT, CORRECT_WORLD,
			CORRECT_LEVEL, CORRECT_TIME, CORRECT_ID;
	static String CORRCT_MAP;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CORRECT_WIDTH = 10;
		CORRECT_HEIGHT = 10;
		CORRECT_DIAMOND_NEEDED = 2;
		CORRECT_PLAYER_COUNT = 1;
		CORRCT_MAP = "!!!!!!!!!!\n!   A    !\n! V V    !\n!  $     !\n!        !\n!        !\n!        !\n!        !\n!        !\n!!!!!!!!!!";
		CORRECT_WORLD = 1;
		CORRECT_LEVEL = 1;
		CORRECT_TIME = 60;
		CORRECT_ID = 0;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//		new StringMap(CORRECT_WIDTH, CORRECT_HEIGHT, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT,
//				CORRCT_MAP,
//				CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
	
	
	
	@Test
	public void testWithoutError() {
		try {
			new StringMap(CORRECT_WIDTH, CORRECT_HEIGHT, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT, CORRCT_MAP,
					CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testWrongPlayerCount() {
		try {
			new StringMap(CORRECT_WIDTH, CORRECT_HEIGHT, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT+1, CORRCT_MAP,
					CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
			fail();
		} catch (Exception e) {
		}
		try {
			new StringMap(CORRECT_WIDTH, CORRECT_HEIGHT, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT-1, CORRCT_MAP,
					CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
			fail();
		} catch (Exception e) {
		}
	}
	
	
	@Test
	public void testWrongWidth() {
		try {
			new StringMap(CORRECT_WIDTH - 1, CORRECT_HEIGHT, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT, CORRCT_MAP,
					CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
			fail();
		} catch (Exception e) {
		}
		try {
			new StringMap(CORRECT_WIDTH + 1, CORRECT_HEIGHT, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT, CORRCT_MAP,
					CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testWrongHeight() {
		try {
			new StringMap(CORRECT_WIDTH, CORRECT_HEIGHT - 1, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT, CORRCT_MAP,
					CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
			fail();
		} catch (Exception e) {
		}
		try {
			new StringMap(CORRECT_WIDTH, CORRECT_HEIGHT + 1, CORRECT_DIAMOND_NEEDED, CORRECT_PLAYER_COUNT, CORRCT_MAP,
					CORRECT_WORLD, CORRECT_LEVEL, null, CORRECT_TIME, CORRECT_ID);
			fail();
		} catch (Exception e) {
		}
	}

}
