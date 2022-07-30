package com.hlb.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.sf.json.JSONArray; //不可导错
import net.sf.json.JSONObject;

import java.io.OutputStream;

/**
 * 处理/server路径请求的处理器类
 */
public class JsonHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        try {

            StringBuilder responseText = new StringBuilder();
            String parameter = "{ \"success\" : 1,  " +
                    "  \"orderId\" : \"A0101001\",  " +
                    "  \"custRelation\" : {" +
                    "  \"total\":\"2\",  " +
                    "   \"data\" : [ {  " +
                    "    \"relationName\" : \"A镇1厂\",  " +
                    "    \"amt\" : \"12000\",  " +
                    "    \"createDate\" : \"20220101\"" +
                    "   },\n" +
                    "   {\"relationName\" : \"A镇2厂\",  " +
                    "    \"amt\" : \"22000\",  \n" +
                    "    \"createDate\" : \"20201201\"" +
                    "   }]}" +
                    "}";
            responseText.append(parameter);
            handleResponseJs(httpExchange, responseText.toString());
            //后台输出日志
            logJson(parameter);
            //删除等
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 处理响应
     * @param httpExchange
     * @param responsetext
     * @throws Exception
     */

    private void handleResponseJs(HttpExchange httpExchange, String responsetext) throws Exception {
        //生成html
        StringBuilder responseContent = new StringBuilder();
        responseContent.append(responsetext);
        String responseContentStr = responseContent.toString();
        byte[] responseContentByte = responseContentStr.getBytes("utf-8");

        //设置响应头，必须在sendResponseHeaders方法之前设置！
        //httpExchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");

        //设置响应码和响应体长度，必须在getResponseBody方法之前调用！
        httpExchange.sendResponseHeaders(88, responseContentByte.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }


    public void logJson(String aa){
        ///输出日志
        JSONObject jsonObject =JSONObject.fromObject(aa);
        System.out.println("success:"+jsonObject.get("success"));
        System.out.println("orderId:"+jsonObject.get("orderId"));
        System.out.println("Total custRelation:"+jsonObject.getJSONObject("custRelation").get("total"));
        JSONObject custRelation = jsonObject.getJSONObject("custRelation");
        JSONArray jsonArray = custRelation.getJSONArray("data");
        JSONObject row = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            row = jsonArray.getJSONObject(i);
            System.out.println("relationName:"+row.get("relationName"));
            System.out.println("amt:"+row.get("amt"));
            System.out.println("createDate:"+row.get("createDate"));
        }

    }
}
