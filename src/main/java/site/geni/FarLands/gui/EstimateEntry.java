package site.geni.FarLands.gui;

import me.shedaniel.cloth.gui.entries.StringListEntry;

class EstimateEntry extends StringListEntry {
	EstimateEntry(String fieldName, String value) {
		super(fieldName, value, null);
	}

	@Override
	public boolean charTyped(char character, int charCode) {
		return false;
	}

	@Override
	public boolean keyPressed(int charCode, int int_1, int int_2) {
		if (charCode != 262 && charCode != 263 && charCode != 264 && charCode != 265) {
			return false;
		}

		return super.keyPressed(charCode, int_1, int_2);
	}
}
