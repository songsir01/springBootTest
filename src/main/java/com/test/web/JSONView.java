package com.test.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.test.util.Constants;
import com.test.util.JSONUtil;
import com.test.util.ResponseUtil;
import com.test.util.vo.Result;




public class JSONView extends AbstractView implements Constants {

    private static final String JSON_TYPE="application/json; charset=UTF-8";

    /**
     * 该View对应的输出类型
     */
    public String getContentType(){
        return JSON_TYPE;
    }

    @SuppressWarnings("rawtypes")
	@Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        Object res=model.get(JSON_ROOT);

        if(res instanceof Result){
            Result responseBean=(Result)res;
            writeResponse(responseBean, response);
            return;
        }
        String jsonStr=JSONUtil.getJSON(res);
        response.setContentType(getContentType());
        ResponseUtil.writeData(response, jsonStr);
    }

    public static void writeResponse(Object responseBean, HttpServletResponse response) throws Exception{
        StringBuilder buf=new StringBuilder();

        String jsonStr=JSONUtil.getJSON(responseBean);
        buf.append(jsonStr);
        response.setContentType(JSON_TYPE);
        ResponseUtil.writeData(response, buf.toString());
    }
}
