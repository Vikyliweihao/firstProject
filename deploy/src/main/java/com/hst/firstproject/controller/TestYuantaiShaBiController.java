package com.hst.firstproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/YTSB")
public class TestYuantaiShaBiController {

    @GetMapping("/SBYT")
    public void csbXYT() {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        try {
            // The path of target file must be existed, target file can not be existed
            File testFile = new File("YTCSB.txt");
            System.out.println("This is a sample to write file");
            fileWriter = new FileWriter(testFile);
            bw = new BufferedWriter(fileWriter);
            String contentStr = "Hello World!";
            bw.write(contentStr);
            // Get new line
            bw.append(System.getProperty("谢元泰ni shi臭傻逼"));
            bw.append("我叫谢元泰，我有点呆，但是我不傻，他们都叫我tiger");
            // Need to add this statement
            bw.flush();
        } catch (Exception e) {
            e.toString();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    e.toString();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e) {
                    e.toString();
                }
            }
        }
    }

    @GetMapping("/SBYTV2")
    public void csbXYTV2() {
        try {
            String encoding = "utf-8";
            File file = new File("YTCSB.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();

        }

    }
}
