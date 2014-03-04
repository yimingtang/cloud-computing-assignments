package cn.edu.nju.software.dochub.web;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseBuilder {
    public void WriteJSONObject(HttpServletResponse response, JSONObject json) {
        response.setHeader("Content-Type", "application/json;charset= utf-8");
        try {
            if (json != null) {
                response.getWriter().write(json.toString());
            }
            response.getWriter().flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
