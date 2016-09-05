package com.gmail.danielvandenberg95.runonstartup;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

class Windows implements OperatingSystem {

	@Override
	public boolean getRunOnStartup(File fileToRun) {
		try {
			String value = getKeyValue(fileToRun);
			String registry = WinRegistry.readString(
					WinRegistry.HKEY_CURRENT_USER,
					"Software\\Microsoft\\Windows\\CurrentVersion\\Run",
					fileToRun.getName() + " autorun key");
			return value.equalsIgnoreCase(registry);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setRunOnStartup(boolean run, File fileToRun) {
		if (run == getRunOnStartup(fileToRun)) {
			return;
		}
		if (run) {
			String value = getKeyValue(fileToRun);
			try {
				WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,
						"Software\\Microsoft\\Windows\\CurrentVersion\\Run",
						fileToRun.getName() + " autorun key", value);
			} catch (IllegalArgumentException | IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			try {
				WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER,
						"Software\\Microsoft\\Windows\\CurrentVersion\\Run",
						fileToRun.getName() + " autorun key");
			} catch (IllegalArgumentException | IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private String getKeyValue(File fileToRun) {
		return "\"" + fileToRun.getAbsolutePath() + "\"";
	}

}
