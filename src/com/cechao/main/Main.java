package com.cechao.main;

import java.io.*;

/**
 * @author Kent
 * @date 2018-03-16.
 */
public class Main {
    private static int lineLength = 119;
    private static StringBuffer replaceStr = new StringBuffer("");


    static {
        for (int i = 0; i < 20; i++) {
            replaceStr = replaceStr.append("0");
        }
        //System.out.println("替换位为:" + replaceStr.length());
    }

    /**
     * 参数说明,源文件，目标文件
     * 错误返回0，正确返回1
     * @param args ["srcFile","desFile"]
     */
    public static void main(String[] args) {
        /*args = new String[2];
        args[0] = "1";
        args[1] = "2";*/
        String status = "1";
        if (args == null) {
            System.out.println("命令错误，正确命令为:java -Dfile.encoding=utf-8 -jar easyText.jar srcFile desFile");
            System.exit(0);
        }
        if (args.length < 2) {
            System.out.println("命令错误，正确命令为:java -Dfile.encoding=utf-8 -jar easyText.jar srcFile desFile");
            System.exit(0);
        }
        //源路径
        String srcFile = args[0];
        srcFile.replace("/", File.separator).replace("\\", File.separator);
        //srcFile = "E:\\workspace\\easyText\\Text\\test.all";
        //目标路径
        String desFile = args[1];
        desFile.replace("/", File.separator).replace("\\", File.separator);
        //desFile = "E:\\workspace\\easyText\\Text\\new.all";
        File file = new File(srcFile);
        if (!file.exists()) {
            System.out.println("文件不存在");
            System.exit(0);
        }
        File newFile = new File(desFile);
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(file));
            bw = new BufferedWriter(new FileWriter(newFile));
            String tempString = "";
            //int line = 1;
            while ((tempString = br.readLine()) != null) {
                //System.out.println(tempString.charAt(118));
                if (tempString.length() < lineLength) {
                    String newLine = tempString + "0" + br.readLine();
                    //System.out.println("替换前：" + newLine);
                    String prefix = newLine.substring(0, 24);
                    String suffix = newLine.substring(44);
                    //System.out.println("前缀:" + prefix + ",中间:" + replaceStr + ",后缀:" + suffix);
                    newLine = prefix + replaceStr + suffix;
                    //System.out.println(newLine.substring(23, 45).length());
                    bw.write(newLine + "\n");
                    //System.out.println("替换后：" + newLine + "\t" + newLine.length());
                } else {
                    String prefix = tempString.substring(0, 24);
                    String suffix = tempString.substring(44);
                    String newLine = prefix + replaceStr + suffix;
                    bw.write(newLine + "\n");
                    //System.out.println("替换后：" + newLine + "\t" + newLine.length());
                }
            }
            bw.flush();
            br.close();
            bw.close();
            //status = 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(status);
        }
    }

}
