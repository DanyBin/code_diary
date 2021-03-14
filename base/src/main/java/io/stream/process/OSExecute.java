package io.stream.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ClassName OSExecute
 * @Author bin
 * @Date 2020/7/27 下午4:00
 * @Decr TODO
 * @Link TODO
 **/
public class OSExecute {
    public static void command(String command) {
        boolean err = false;

        try {
            Process process = new  ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String s;
            while ((s = results.readLine()) != null){
                System.out.println(s);
            }

            BufferedReader errors = new BufferedReader(
                    new InputStreamReader(process.getErrorStream())
            );
            while ((s = errors.readLine()) != null){
                System.out.println(s);
                err = true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(err){
            throw  new OSExecuteException("erros executing : " + command);
        }
    }
}
