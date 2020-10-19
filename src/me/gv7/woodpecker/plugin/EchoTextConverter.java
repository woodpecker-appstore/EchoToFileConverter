package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.plugin.payload.LinuxEchoTextConverter;
import me.gv7.woodpecker.plugin.payload.WindowsEchoTextConverter;

import java.util.ArrayList;
import java.util.List;

public class EchoTextConverter implements IHelperPlugin {
    public static IHelperPluginCallbacks callbacks;
    public static IPluginHelper pluginHelper;

    @Override
    public void HelperPluginMain(IHelperPluginCallbacks iHelperPluginCallbacks) {
        this.callbacks = iHelperPluginCallbacks;
        this.pluginHelper = callbacks.getPluginHelper();
        callbacks.setHelperPluginName("Echo Text Converter");
        callbacks.setHelperPluginVersion("0.1.0");
        callbacks.setHelperPluginVersion("c0ny1");
        List<IHelper> helperPluginList = new ArrayList<IHelper>();
        helperPluginList.add(new WindowsEchoTextConverter());
        helperPluginList.add(new LinuxEchoTextConverter());
        callbacks.registerHelper(helperPluginList);
    }
}
