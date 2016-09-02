package com.gmail.danielvandenberg95.runonstartup;

import java.io.File;

interface OperatingSystem {

	boolean getRunOnStartup(File fileToRun);

	void setRunOnStartup(boolean run, File fileToRun);
	
}
