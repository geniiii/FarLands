package site.geni.FarLands.gui;

import com.google.common.base.Predicate;
import com.google.common.primitives.Doubles;
import com.sun.istack.internal.Nullable;
import io.netty.util.internal.AppendableCharSequence;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.resource.language.I18n;
import site.geni.FarLands.utils.Config;
import site.geni.FarLands.utils.Config.ConfigSpec;

@Environment(EnvType.CLIENT)
public class CustomizeFarLandsScreen extends Screen {
	private MinecraftClient client = MinecraftClient.getInstance();
	private ConfigSpec tempConfig = Config.getConfig();
	private final Screen parent;
	private TextFieldWidget coordinateScale;
	private TextFieldWidget heightScale;
	private TextFieldWidget coordinateScaleMultiplier;
	private TextFieldWidget heightScaleMultiplier;
	private ButtonWidget killFallingBlockEntitiesInFarLands;
	private ButtonWidget farLandsEnabled;


	public CustomizeFarLandsScreen(Screen screen_1) {
		this.parent = screen_1;
	}

	public boolean charTyped(char char_1, int int_1) {
		if (this.coordinateScale.isFocused()) {
			if (Doubles.tryParse(this.coordinateScale.getText() + char_1) != null) {
				this.coordinateScale.charTyped(char_1, int_1);
				this.tempConfig.coordinateScale = Double.parseDouble(this.coordinateScale.getText());
				return true;
			}
			return false;
		} else if (this.coordinateScaleMultiplier.isFocused()) {
			if (Doubles.tryParse(this.coordinateScaleMultiplier.getText() + char_1) != null) {
				this.coordinateScaleMultiplier.charTyped(char_1, int_1);
				this.tempConfig.coordinateScaleMultiplier = Double.parseDouble(this.coordinateScaleMultiplier.getText());
				return true;
			}
			return false;
		} else {
			return super.charTyped(char_1, int_1);
		}
	}

	@Override
	protected void onInitialized() {
		this.client.keyboard.enableRepeatEvents(true);

		this.farLandsEnabled = this.addButton(new ButtonWidget(this.width / 2 - 155, 55, 100, 20, "") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled = !CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.addButton(new ButtonWidget(this.width / 2, this.height - 28, 150, 20, "Cancel") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.client.openScreen(CustomizeFarLandsScreen.this.parent);
			}
		});

		this.addButton(new ButtonWidget(this.width / 2 - 155, this.height - 28, 150, 20, "Done") {
			public void onPressed(double double_1, double double_2) {
				Config.setConfig(CustomizeFarLandsScreen.this.tempConfig);
				Config.saveConfig();
				CustomizeFarLandsScreen.this.client.openScreen(CustomizeFarLandsScreen.this.parent);
			}
		});

		this.killFallingBlockEntitiesInFarLands = this.addButton(new ButtonWidget(this.width / 2 - 155, 20, 310, 20, "") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands = !CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.coordinateScale = new TextFieldWidget(this.fontRenderer, this.width / 2 - 50, 55, 204, 18);
		this.coordinateScale.setText(String.valueOf(this.tempConfig.coordinateScale));
		this.coordinateScale.setFocused(true);
		this.listeners.add(this.coordinateScale);

		this.coordinateScaleMultiplier = new TextFieldWidget(this.fontRenderer, this.width / 2 - 50, 90, 204, 18);
		this.coordinateScaleMultiplier.setText(String.valueOf(this.tempConfig.coordinateScaleMultiplier));
		this.listeners.add(this.coordinateScaleMultiplier);

		this.updateButtons();
	}


	public void draw(int int_1, int int_2, float float_1) {
		this.drawBackground();

		this.drawStringCentered(this.fontRenderer, "FarLands configuration", this.width / 2, 5, 16777215);
		this.drawString(this.fontRenderer, "Coordinate scale", this.width / 2 - 50, 45, -6250336);
		this.drawString(this.fontRenderer, "Coordinate scale multiplier", this.width / 2 - 50, 80, -6250336);


		this.coordinateScale.draw(int_1, int_2, float_1);
		this.coordinateScaleMultiplier.draw(int_1, int_2, float_1);

		super.draw(int_1, int_2, float_1);
	}

	private void updateButtons() {
		CustomizeFarLandsScreen.this.killFallingBlockEntitiesInFarLands.enabled = CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled;

		this.farLandsEnabled.setText("Far Lands: " + I18n.translate(tempConfig.farLandsEnabled ? "options.on" : "options.off"));
		this.killFallingBlockEntitiesInFarLands.setText("Kill falling block entities in Far Lands: " + I18n.translate(tempConfig.killFallingBlockEntitiesInFarLands ? "options.on" : "options.off"));
	}

}
