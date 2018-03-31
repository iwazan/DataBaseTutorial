package databasetutorial.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onEnable() {
		System.out.println("DataBaseTutorial HelloWorld!");
		try {
			UseJdbc.Main();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new PlayerListener(this);
	}

}
