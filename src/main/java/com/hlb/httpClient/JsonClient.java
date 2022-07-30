package com.hlb.httpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonClient {

    /**
     * 调用对方接口方法
     *
     * @param path 对方或第三方提供的路径
     * @param data
     */
    public void getUrl(String path, String data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");//默认GET
            // 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
            conn.connect();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            String jsonStr = "";
            while ((str = br.readLine()) != null) {
                str = new String(str.getBytes(), "UTF-8");
                jsonStr = jsonStr + str;
            }
            //jsonStr = jsonStr.replace("<html>", "").replace("<body>","").replace("</html>","").replace("</body>","");
            System.out.println(jsonStr);
            logJson(jsonStr);
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("====");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logJson(String aa) {
        ///输出日志
        JSONObject jsonObject = JSONObject.fromObject(aa);
        System.out.println("success:" + jsonObject.get("success"));
        System.out.println("orderId:" + jsonObject.get("orderId"));
        System.out.println("Total custRelation:" + jsonObject.getJSONObject("custRelation").get("total"));
        JSONObject custRelation = jsonObject.getJSONObject("custRelation");
        JSONArray jsonArray = custRelation.getJSONArray("data");
        JSONObject row = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            row = jsonArray.getJSONObject(i);
            System.out.println("relationName:" + row.get("relationName"));
            System.out.println("amt:" + row.get("amt"));
            System.out.println("createDate:" + row.get("createDate"));
        }
    }


    public static void main(String[] args) {
        JsonClient js=new JsonClient();
        js.getUrl("http://localhost:8019/server?aaa=1", "");
    }

}