package com.test.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

    /**
     * 输出数据
     */
    public static void writeData(HttpServletResponse response, String message){
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=null;
        try{
            out=response.getWriter();
            out.print(message);
            out.flush();
        }catch(IOException e){
        }finally{
            if(out!=null){
                out.close();
                out=null;
            }
        }
    }
}
