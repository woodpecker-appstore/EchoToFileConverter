package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.plugin.payload.LinuxEchoTextConverter;
import me.gv7.woodpecker.plugin.payload.WindowsEchoTextConverter;

import java.util.ArrayList;
import java.util.List;

public class EchoTextConverter implements IPlugin {
    public static IExtenderCallbacks callbacks;
    public static IPluginHelper pluginHelper;

    public void PluginMain(IExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.pluginHelper = callbacks.getPluginHelper();
        callbacks.setPluginName("Echo Text Converter");
        callbacks.setPluginVersion("0.1.0");
        callbacks.setPluginAutor("c0ny1");
        List<IPayloadGenerator> payloadGeneratorList = new ArrayList<IPayloadGenerator>();
        payloadGeneratorList.add(new WindowsEchoTextConverter());
        payloadGeneratorList.add(new LinuxEchoTextConverter());
        callbacks.registerPayloadGenerator(payloadGeneratorList);
    }
}
