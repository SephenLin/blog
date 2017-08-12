package aode.ssm.blog.intercepter;

/**
 * Created by 林进华 on 2017/7/31.
 */
import aode.ssm.blog.po.Admin;
import aode.ssm.blog.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 黄柏樟 on 2016/5/13.
 * @Explain: 商家拦截器
 */
public class AdminIntercepter implements HandlerInterceptor {


    private List<String> excludeUrls;

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
    /**
     * 完成页面的render后调用
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

    }

    /**
     * 在调用controller具体方法后拦截
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在调用controller具体方法前拦截
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if (excludeUrls.contains(url)) {
            System.out.println("通过了拦截器");
            return true;
        } else {
            Admin loginAdmin = (Admin) request.getSession().getAttribute("loginAdmin");
            if (loginAdmin != null && loginAdmin.getId()!= null && !loginAdmin.getId().equals("")) {
                return true;
            } else {
                System.out.println("未通过拦截器");
                System.out.println("未通过拦截器 : " + url);
                request.setAttribute("result",false);
                request.setAttribute("message", "您还没有登录或登录已超时，请重新登录");
                request.getRequestDispatcher("/adminLoginController/admin/loginUI.action").forward(request, response);
                return false;
            }
        }
    }

}
