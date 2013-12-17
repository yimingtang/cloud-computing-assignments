package cn.edu.nju.software.dochub.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseBuilder {
	public void WriteJSONObject(HttpServletResponse response,JSONObject json){
		response.setHeader("Content-Type", "application/json;charset= utf-8");
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
