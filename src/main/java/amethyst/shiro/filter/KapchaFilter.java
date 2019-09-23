package amethyst.shiro.filter;

import amethyst.vo.AjaxResult;
import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class KapchaFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        //返回true就放行，返回false就拦截
        HttpServletRequest request= WebUtils.toHttp(servletRequest);
        //获取输入的验证码
        String formVC=WebUtils.getCleanParam(request, "validateCode");
        //获取生成验证码
        String kaptchaCode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        if(formVC.equalsIgnoreCase(kaptchaCode)){
            return true;
        }else {
            return false;
        }

    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //不放行时的提示
//        HttpServletRequest request= WebUtils.toHttp(servletRequest);
//        request.getSession().setAttribute("err_msg", "验证码不正确");
//        this.saveRequestAndRedirectToLogin(servletRequest,servletResponse);
        HttpServletResponse response=WebUtils.toHttp(servletResponse);
        response.setContentType("application/json;charset:utf-8");
        PrintWriter out=response.getWriter();
        out.print(JSON.toJSON(new AjaxResult(500, "验证码错误", null)));
        out.flush();
        return false;
    }
}
