package site.geni.farlands.gui.entries;

import me.shedaniel.clothconfig2.gui.entries.StringListEntry;

import static org.lwjgl.glfw.GLFW.*;

public class EstimateListEntry extends StringListEntry {
	@Deprecated
	public EstimateListEntry(String fieldName, String value) {
		super(fieldName, value, null);
	}

	private static boolean handlePress(int charCode) {
		switch (charCode) {
			case GLFW_KEY_RIGHT:
			case GLFW_KEY_LEFT:
			case GLFW_KEY_UP:
			case GLFW_KEY_DOWN:
				return true;
			default:
				return false;
		}
	}

	void update(final String text) {
		this.textFieldWidget.setText(text);
		this.textFieldWidget.setCursorToStart();
	}

	@Override
	public boolean charTyped(char character, int charCode) {
		return EstimateListEntry.handlePress(charCode) || super.charTyped(character, charCode);
	}

	@Override
	public boolean keyPressed(int charCode, int int_1, int int_2) {
		return EstimateListEntry.handlePress(charCode) || super.keyPressed(charCode, int_1, int_2);
	}
}
