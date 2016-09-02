package com.gmail.danielvandenberg95.runonstartup;

import java.io.File;

import com.gmail.danielvandenberg95.runonstartup.OperatingSystem;

public class RunOnStartup {
	private final static OperatingSystem operatingSystem;

	static {
		final String operatingSystemName = System.getProperty("os.name")
				.toLowerCase();
		if (operatingSystemName.startsWith("windows")) {
			operatingSystem = new Windows();
		} else {
			throw new RuntimeException("Operatingsystem " + operatingSystemName
					+ " not known.");
		}
	}

	private final File fileToRun;

	public RunOnStartup(File fileToRun) {
		this.fileToRun = fileToRun;
	}

	public boolean getRunOnStartup() {
		return operatingSystem.getRunOnStartup(fileToRun);
	}

	public void setRunOnStartup(boolean run) {
		operatingSystem.setRunOnStartup(run, fileToRun);
	}
}