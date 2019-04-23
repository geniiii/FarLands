package site.geni.FarLands.gui.entries;

import me.shedaniel.cloth.gui.entries.StringListEntry;

import static org.lwjgl.glfw.GLFW.*;

public class EstimateEntry extends StringListEntry {

	public EstimateEntry(String fieldName, String value) {
		super(fieldName, value, null);
	}

	@Override
	public boolean charTyped(char character, int charCode) {
		return false;
	}

	@Override
	public boolean keyPressed(int charCode, int int_1, int int_2) {
		if (charCode != GLFW_KEY_RIGHT && charCode != GLFW_KEY_LEFT && charCode != GLFW_KEY_UP && charCode != GLFW_KEY_DOWN) {
			return false;
		}

		return super.keyPressed(charCode, int_1, int_2);
	}
}
