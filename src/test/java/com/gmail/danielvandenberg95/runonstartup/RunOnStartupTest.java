package com.gmail.danielvandenberg95.runonstartup;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gmail.danielvandenberg95.runonstartup.RunOnStartup;

public class RunOnStartupTest {
	private File[] f = new File[5];

	@Before
	public void setUp() throws IOException {
		for (int i = 0; i < 5; i++) {
			f[i] = new File("runOnStartupTestFile" + i);
			if (!f[i].exists()) {
				f[i].createNewFile();
			}
		}
	}

	@After
	public void tearDown() {
		for (int i = 0; i < 5; i++) {
			f[i] = new File("runOnStartupTestFile" + i);
			if (f[i].exists()) {
				f[i].delete();
			}
		}
	}

	@Test
	public void test() { // Test that should always complete, to make sure the
							// system is set up properly.
		assertEquals(true, true);
		assertNotEquals("Test", "Testing");
	}

	@Test
	public void makeLink() {
		for (int i = 0; i < 5; i++) {
			File file = new File("runOnStartupTestFile" + i);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			RunOnStartup testRun = new RunOnStartup(file);
			testRun.setRunOnStartup(false);
			assertEquals(false, testRun.getRunOnStartup());
			testRun.setRunOnStartup(true);
			assertEquals(true, testRun.getRunOnStartup());
			testRun.setRunOnStartup(false);
			assertEquals(false, testRun.getRunOnStartup());
		}
	}
}
