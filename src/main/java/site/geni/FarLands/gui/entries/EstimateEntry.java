package site.geni.FarLands.gui.entries;

import me.shedaniel.cloth.gui.entries.StringListEntry;

import java.util.function.Function;

import static org.lwjgl.glfw.GLFW.*;

public class EstimateEntry extends StringListEntry {

	public EstimateEntry(String fieldName, String value) {
		super(fieldName, value, null);
	}

	@Override
	public boolean charTyped(char character, int charCode) {
		if (EstimateEntry.handlePress(charCode)) {
			return true;
		}

		return super.charTyped(character, charCode);
	}

	@Override
	public boolean keyPressed(int charCode, int int_1, int int_2) {
		if (EstimateEntry.handlePress(charCode)) {
			return true;
		}

		return super.keyPressed(charCode, int_1, int_2);
	}

	private static boolean handlePress(int charCode) {
		switch (charCode) {
			case GLFW_KEY_RIGHT:
			case GLFW_KEY_LEFT:
			case GLFW_KEY_UP:
			case GLFW_KEY_DOWN:
				return true;
		}

		return false;
	}
}
