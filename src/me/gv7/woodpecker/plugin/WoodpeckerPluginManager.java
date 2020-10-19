package me.gv7.woodpecker.plugin;

public class WoodpeckerPluginManager implements IPluginManager {
    public void registerPluginManagerCallbacks(IPluginManagerCallbacks iPluginManagerCallbacks) {
        EchoTextConverter echoTextConverter = new EchoTextConverter();
        iPluginManagerCallbacks.registerHelperPlugin(echoTextConverter);
    }
}
