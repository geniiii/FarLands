package site.geni.FarLands.gui;

import com.google.common.primitives.Doubles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;
import site.geni.FarLands.utils.Config;
import site.geni.FarLands.utils.Config.ConfigSpec;

@Environment(EnvType.CLIENT)
public class CustomizeFarLandsScreen extends Screen {
	private final Screen parent;

	private MinecraftClient client = MinecraftClient.getInstance();

	private ConfigSpec tempConfig = Config.getConfig();

	private TextFieldWidget coordinateScale;
	private TextFieldWidget heightScale;
	private TextFieldWidget coordinateScaleMultiplier;
	private TextFieldWidget heightScaleMultiplier;

	private ButtonWidget killFallingBlockEntitiesInFarLands;
	private ButtonWidget farLandsEnabled;
	private ButtonWidget fixOreGeneration;
	private ButtonWidget fixParticles;
	private ButtonWidget fixLighting;

	private int farLandsLocation = (int) (Integer.MAX_VALUE / ((tempConfig.coordinateScale * tempConfig.coordinateScaleMultiplier) / 4));


	CustomizeFarLandsScreen(Screen parent) {
		super();

		this.parent = parent;
	}

	@Override
	public void update() {
		this.coordinateScale.tick();
		this.heightScale.tick();
		this.coordinateScaleMultiplier.tick();
		this.heightScaleMultiplier.tick();
	}

	/**
	 * Behaviour for when a character has been entered in a text field
	 *
	 * @param charTyped Character that's been typed
	 * @param keyCode   Key code of the typed character
	 * @return If the character should be appended to the text field
	 * @author geni
	 */
	@Override
	public boolean charTyped(char charTyped, int keyCode) {
		if (Character.toLowerCase(charTyped) == 'd' || Character.toLowerCase(charTyped) == 'f') {
			return false;
		}

		try {
			if (this.coordinateScale.isFocused()) {
				this.tempConfig.coordinateScale = Double.parseDouble(this.coordinateScale.getText() + charTyped);

				this.coordinateScale.charTyped(charTyped, keyCode);
			} else if (this.coordinateScaleMultiplier.isFocused()) {
				this.tempConfig.coordinateScaleMultiplier = Double.parseDouble(this.coordinateScaleMultiplier.getText() + charTyped);

				this.coordinateScaleMultiplier.charTyped(charTyped, keyCode);
			} else if (this.heightScale.isFocused()) {
				this.tempConfig.heightScale = Double.parseDouble(this.heightScale.getText() + charTyped);

				this.heightScale.charTyped(charTyped, keyCode);
			} else if (this.heightScaleMultiplier.isFocused()) {
				this.tempConfig.heightScaleMultiplier = Double.parseDouble(this.heightScaleMultiplier.getText() + charTyped);

				this.heightScaleMultiplier.charTyped(charTyped, keyCode);
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * Behaviour for when a key is pressed
	 *
	 * @param keyCode Key code of the pressed key
	 * @param int_2   ?
	 * @param int_3   ?
	 * @return If they key press should be accepted
	 * @author geni
	 */
	@Override
	public boolean keyPressed(int keyCode, int int_2, int int_3) {
		try {
			if (this.coordinateScale.isFocused()) {
				this.coordinateScale.keyPressed(keyCode, int_2, int_3);

				this.tempConfig.coordinateScale = Double.parseDouble(this.coordinateScale.getText());
			} else if (this.coordinateScaleMultiplier.isFocused()) {
				this.coordinateScaleMultiplier.keyPressed(keyCode, int_2, int_3);

				this.tempConfig.coordinateScaleMultiplier = Double.parseDouble(this.coordinateScaleMultiplier.getText());
			} else if (this.heightScale.isFocused()) {
				this.heightScale.keyPressed(keyCode, int_2, int_3);

				this.tempConfig.heightScale = Double.parseDouble(this.heightScale.getText());
			} else if (this.heightScaleMultiplier.isFocused()) {
				this.heightScaleMultiplier.keyPressed(keyCode, int_2, int_3);

				this.tempConfig.heightScaleMultiplier = Double.parseDouble(this.heightScaleMultiplier.getText());
			} else {
				super.keyPressed(keyCode, int_2, int_3);
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * On initialization of the screen: enable keyboard repeat events, add buttons, add text fields, update buttons...
	 *
	 * @author geni
	 */
	@Override
	protected void onInitialized() {
		this.client.keyboard.enableRepeatEvents(true);

		this.killFallingBlockEntitiesInFarLands = this.addButton(new ButtonWidget(this.screenWidth / 2 - 70, 20, 226, 20, "") {
			public void onPressed() {
				CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands = !CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.farLandsEnabled = this.addButton(new ButtonWidget(this.screenWidth / 2 - 155, 20, 82, 20, "") {
			public void onPressed() {
				CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled = !CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.fixOreGeneration = this.addButton(new ButtonWidget(this.screenWidth / 2 - 155, 50, 92, 20, "") {
			public void onPressed() {
				CustomizeFarLandsScreen.this.tempConfig.fixOreGeneration = !CustomizeFarLandsScreen.this.tempConfig.fixOreGeneration;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.fixLighting = this.addButton(new ButtonWidget(this.screenWidth / 2 - 58, 50, 90, 20, "") {
			public void onPressed() {
				CustomizeFarLandsScreen.this.tempConfig.fixLighting = !CustomizeFarLandsScreen.this.tempConfig.fixLighting;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.fixParticles = this.addButton(new ButtonWidget(this.screenWidth / 2 + 38, 50, 118, 20, "") {
			public void onPressed() {
				CustomizeFarLandsScreen.this.tempConfig.fixParticles = !CustomizeFarLandsScreen.this.tempConfig.fixParticles;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.addButton(new ButtonWidget(this.screenWidth / 2 + 5, this.screenHeight - 28, 150, 20, "Cancel") {
			public void onPressed() {
				CustomizeFarLandsScreen.this.client.openScreen(CustomizeFarLandsScreen.this.parent);
			}
		});

		this.addButton(new ButtonWidget(this.screenWidth / 2 - 155, this.screenHeight - 28, 150, 20, "Done") {
			public void onPressed() {
				CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands = CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands && CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled;

				Config.setConfig(CustomizeFarLandsScreen.this.tempConfig);
				Config.saveConfig();

				CustomizeFarLandsScreen.this.client.openScreen(CustomizeFarLandsScreen.this.parent);
			}
		});

		this.coordinateScale = new TextFieldWidget(this.fontRenderer, this.screenWidth / 2 - 155, 85, 310, 18);
		this.coordinateScale.setText(String.valueOf(this.tempConfig.coordinateScale));
		this.listeners.add(this.coordinateScale);

		this.coordinateScaleMultiplier = new TextFieldWidget(this.fontRenderer, this.screenWidth / 2 - 155, 120, 310, 18);
		this.coordinateScaleMultiplier.setText(String.valueOf(this.tempConfig.coordinateScaleMultiplier));
		this.listeners.add(this.coordinateScaleMultiplier);

		this.heightScale = new TextFieldWidget(this.fontRenderer, this.screenWidth / 2 - 155, 155, 310, 18);
		this.heightScale.setText(String.valueOf(this.tempConfig.heightScale));
		this.listeners.add(this.heightScale);

		this.heightScaleMultiplier = new TextFieldWidget(this.fontRenderer, this.screenWidth / 2 - 155, 190, 310, 18);
		this.heightScaleMultiplier.setText(String.valueOf(this.tempConfig.heightScaleMultiplier));
		this.listeners.add(this.heightScaleMultiplier);

		this.focusOn(this.coordinateScale);

		this.updateButtons();
	}


	/**
	 * @param mouseX       X position of the mouse cursor
	 * @param mouseY       Y position of the mouse cursor
	 * @param partialTicks Partial ticks
	 * @author geni
	 */
	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground();

		this.drawStringCentered(this.fontRenderer, I18n.translate("config.farlands.title"), this.screenWidth / 2, 5, 16777215);

		this.drawString(this.fontRenderer, I18n.translate("config.farlands.coordinateScale"), this.screenWidth / 2 - 155, 75, -6250336);
		this.drawString(this.fontRenderer, I18n.translate("config.farlands.coordinateScaleMultiplier"), this.screenWidth / 2 - 155, 110, -6250336);

		this.drawString(this.fontRenderer, I18n.translate("config.farlands.heightScale"), this.screenWidth / 2 - 155, 145, -6250336);
		this.drawString(this.fontRenderer, I18n.translate("config.farlands.heightScaleMultiplier"), this.screenWidth / 2 - 155, 180, -6250336);


		if (this.tempConfig.farLandsEnabled && this.updateLocation() && (Doubles.tryParse(this.coordinateScale.getText()) != null && Doubles.tryParse(this.coordinateScaleMultiplier.getText()) != null)) {
			String estimatedPosition = I18n.translate("config.farlands.estimatedPosition", this.farLandsLocation);

			this.drawString(this.fontRenderer, estimatedPosition, this.screenWidth / 2 - estimatedPosition.length(), 75, -6250336);
		}

		this.coordinateScale.draw(mouseX, mouseY, partialTicks);
		this.coordinateScaleMultiplier.draw(mouseX, mouseY, partialTicks);
		this.heightScale.draw(mouseX, mouseY, partialTicks);
		this.heightScaleMultiplier.draw(mouseX, mouseY, partialTicks);

		super.draw(mouseX, mouseY, partialTicks);
	}

	/**
	 * Updates button text and enabled status
	 *
	 * @author geni
	 */
	private void updateButtons() {
		this.killFallingBlockEntitiesInFarLands.enabled = this.tempConfig.farLandsEnabled;

		this.farLandsEnabled.setText(I18n.translate("config.farlands.farLandsEnabled", I18n.translate(this.tempConfig.farLandsEnabled ? "options.on" : "options.off")));
		this.killFallingBlockEntitiesInFarLands.setText(I18n.translate("config.farlands.killEntities", I18n.translate(this.tempConfig.killFallingBlockEntitiesInFarLands && this.tempConfig.farLandsEnabled ? "options.on" : "options.off")));

		this.fixOreGeneration.setText(I18n.translate("config.farlands.fixOreGeneration", I18n.translate(this.tempConfig.fixOreGeneration ? "options.on" : "options.off")));
		this.fixLighting.setText(I18n.translate("config.farlands.fixLighting", I18n.translate(this.tempConfig.fixLighting ? "options.on" : "options.off")));
		this.fixParticles.setText(I18n.translate("config.farlands.fixParticles", I18n.translate(this.tempConfig.fixParticles ? "options.on" : "options.off")));
	}

	/**
	 * Updates {@link #farLandsLocation}
	 *
	 * @return If the location is valid and should be drawn or not
	 * @author geni
	 */
	private boolean updateLocation() {
		try {
			this.farLandsLocation = (int) (Integer.MAX_VALUE / ((tempConfig.coordinateScale * tempConfig.coordinateScaleMultiplier) / 4));

			return farLandsLocation != Integer.MAX_VALUE;
		} catch (ArithmeticException ignore) {
			return false;
		}
	}

}
