package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.plugin.payload.LinuxBase64EchoTextConverter;
import me.gv7.woodpecker.plugin.payload.LinuxEchoTextConverter;
import me.gv7.woodpecker.plugin.payload.LinuxXXDEchoTextConverter;
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
        callbacks.setHelperPluginAutor("woodpecker-org");
        callbacks.setHelperPluginVersion("0.3.0");
        List<IHelper> helperPluginList = new ArrayList<IHelper>();
        helperPluginList.add(new WindowsEchoTextConverter());
        helperPluginList.add(new LinuxEchoTextConverter());
        helperPluginList.add(new LinuxBase64EchoTextConverter());
        helperPluginList.add(new LinuxXXDEchoTextConverter());
        callbacks.registerHelper(helperPluginList);
    }
}
