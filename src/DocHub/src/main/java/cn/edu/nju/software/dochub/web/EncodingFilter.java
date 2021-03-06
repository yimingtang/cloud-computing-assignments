package cn.edu.nju.software.dochub.web;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding = null;

    public void destroy() {
        // TODO Auto-generated method stub
        encoding = null;
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        // TODO Auto-generated method stub
        arg0.setCharacterEncoding(encoding);
        arg1.setCharacterEncoding(encoding);
        arg2.doFilter(arg0, arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        encoding = arg0.getInitParameter("encoding");
        System.out.println(">>>>>>>>>>filter  " + encoding);
    }

    public String getEncoding() {
        return encoding;
    }

}
