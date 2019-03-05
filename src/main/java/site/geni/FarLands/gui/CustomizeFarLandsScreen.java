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


	public CustomizeFarLandsScreen(Screen screen_1) {
		super();

		this.parent = screen_1;
	}

	@Override
	public void update() {
		this.coordinateScale.tick();
		this.heightScale.tick();
		this.coordinateScaleMultiplier.tick();
		this.heightScaleMultiplier.tick();
	}

	@Override
	public boolean charTyped(char charTyped, int keyCode) {
		if (Character.toLowerCase(charTyped) == 'd' || Character.toLowerCase(charTyped) == 'f') {
			return false;
		} else if (this.coordinateScale.isFocused()) {
			if (Doubles.tryParse(this.coordinateScale.getText() + charTyped) != null) {
				this.coordinateScale.charTyped(charTyped, keyCode);
				this.tempConfig.coordinateScale = Double.parseDouble(this.coordinateScale.getText());
				return true;
			}
			return false;
		} else if (this.coordinateScaleMultiplier.isFocused()) {
			if (Doubles.tryParse(this.coordinateScaleMultiplier.getText() + charTyped) != null) {
				this.coordinateScaleMultiplier.charTyped(charTyped, keyCode);
				this.tempConfig.coordinateScaleMultiplier = Double.parseDouble(this.coordinateScaleMultiplier.getText());
				return true;
			}
			return false;
		} else if (this.heightScale.isFocused()) {
			if (Doubles.tryParse(this.heightScale.getText() + charTyped) != null) {
				this.heightScale.charTyped(charTyped, keyCode);
				this.tempConfig.heightScale = Double.parseDouble(this.heightScale.getText());
				return true;
			}
			return false;
		} else if (this.heightScaleMultiplier.isFocused()) {
			if (Doubles.tryParse(this.heightScaleMultiplier.getText() + charTyped) != null) {
				this.heightScaleMultiplier.charTyped(charTyped, keyCode);
				this.tempConfig.heightScaleMultiplier = Double.parseDouble(this.heightScaleMultiplier.getText());
				return true;
			}
			return false;
		} else {
			return super.charTyped(charTyped, keyCode);
		}
	}

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

	@Override
	protected void onInitialized() {
		this.client.keyboard.enableRepeatEvents(true);

		this.killFallingBlockEntitiesInFarLands = this.addButton(new ButtonWidget(this.screenWidth / 2 - 155, 20, 310, 20, "") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands = !CustomizeFarLandsScreen.this.tempConfig.killFallingBlockEntitiesInFarLands;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.farLandsEnabled = this.addButton(new ButtonWidget(this.screenWidth / 2 - 155, 50, 85, 20, "") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled = !CustomizeFarLandsScreen.this.tempConfig.farLandsEnabled;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.fixOreGeneration = this.addButton(new ButtonWidget(this.screenWidth / 2 - 66, 50, 125, 20, "") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.tempConfig.fixOreGeneration = !CustomizeFarLandsScreen.this.tempConfig.fixOreGeneration;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.fixParticles = this.addButton(new ButtonWidget(this.screenWidth / 2 + 63, 50, 92, 20, "") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.tempConfig.fixParticles = !CustomizeFarLandsScreen.this.tempConfig.fixParticles;
				CustomizeFarLandsScreen.this.updateButtons();
			}
		});

		this.addButton(new ButtonWidget(this.screenWidth / 2 + 5, this.screenHeight - 28, 150, 20, "Cancel") {
			public void onPressed(double double_1, double double_2) {
				CustomizeFarLandsScreen.this.client.openScreen(CustomizeFarLandsScreen.this.parent);
			}
		});

		this.addButton(new ButtonWidget(this.screenWidth / 2 - 155, this.screenHeight - 28, 150, 20, "Done") {
			public void onPressed(double double_1, double double_2) {
				Config.setConfig(CustomizeFarLandsScreen.this.tempConfig);
				Config.saveConfig();

				CustomizeFarLandsScreen.this.client.openScreen(CustomizeFarLandsScreen.this.parent);
			}
		});

		this.coordinateScale = new TextFieldWidget(this.fontRenderer, this.screenWidth / 2 - 155, 85, 310, 18);
		this.coordinateScale.setText(String.valueOf(this.tempConfig.coordinateScale));
		this.coordinateScale.setFocused(true);
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

		this.updateButtons();
	}


	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground();

		this.drawStringCentered(this.fontRenderer, I18n.translate("config.farlands.title"), this.screenWidth / 2, 5, 16777215);
		this.drawString(this.fontRenderer, I18n.translate("config.farlands.coordinateScale"), this.screenWidth / 2 - 155, 75, -6250336);
		this.drawString(this.fontRenderer, I18n.translate("config.farlands.coordinateScaleMultiplier"), this.screenWidth / 2 - 155, 110, -6250336);
		this.drawString(this.fontRenderer, I18n.translate("config.farlands.heightScale"), this.screenWidth / 2 - 155, 145, -6250336);
		this.drawString(this.fontRenderer, I18n.translate("config.farlands.heightScaleMultiplier"), this.screenWidth / 2 - 155, 180, -6250336);


		this.coordinateScale.draw(mouseX, mouseY, partialTicks);
		this.coordinateScaleMultiplier.draw(mouseX, mouseY, partialTicks);
		this.heightScale.draw(mouseX, mouseY, partialTicks);
		this.heightScaleMultiplier.draw(mouseX, mouseY, partialTicks);

		super.draw(mouseX, mouseY, partialTicks);
	}

	private void updateButtons() {
		this.killFallingBlockEntitiesInFarLands.enabled = this.tempConfig.farLandsEnabled;
		this.fixOreGeneration.enabled = this.tempConfig.farLandsEnabled;
		this.fixParticles.enabled = this.tempConfig.farLandsEnabled;

		this.killFallingBlockEntitiesInFarLands.setText(I18n.translate("config.farlands.killEntities", I18n.translate(this.tempConfig.killFallingBlockEntitiesInFarLands ? "options.on" : "options.off")));
		this.farLandsEnabled.setText(I18n.translate("config.farlands.farLandsEnabled", I18n.translate(this.tempConfig.farLandsEnabled ? "options.on" : "options.off")));
		this.fixOreGeneration.setText(I18n.translate("config.farlands.fixOreGeneration", I18n.translate(this.tempConfig.fixOreGeneration ? "options.on" : "options.off")));
		this.fixParticles.setText(I18n.translate("config.farlands.fixParticles", I18n.translate(this.tempConfig.fixParticles ? "options.on" : "options.off")));
	}

}
