package cn.hhfarcry.springbootmybatis.common.base.interceptor;

import cn.hhfarcry.springbootmybatis.common.base.annotation.BaseParamAnnotation;
import cn.hhfarcry.springbootmybatis.common.base.exception.BaseException;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.base.vo.CodeMsgVo;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @program: boccasecurityofproductionplatformbackside
 * @description: 自定义拦截
 * @author: huanghong
 * @date: 2019-03-29 13:26
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BaseException {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        BaseParamAnnotation params = method.getAnnotation(BaseParamAnnotation.class);
        //如果没有参数是必传的，那么直接通过
        if(ParamUtils.isBlank(params)){
            return true;
        }
        boolean isOK = true;
        if(ParamUtils.isNotBlank(params.queryParams())){
            String []queryparams = params.queryParams();
            for (String queryparam : queryparams) {
                String nowparam = request.getParameter(queryparam);
                if(ParamUtils.isBlank(nowparam)){
                    isOK = false;
                    throw new BaseException(CodeMsgVo.QUERY_PARAMETER_LACK.getCode(),CodeMsgVo.QUERY_PARAMETER_LACK.getMsg(),queryparam+"必传");
                }
            }
        }
        if(ParamUtils.isNotBlank(params.headerParams())){
            String []headerparams = params.headerParams();
            for (String headerparam : headerparams) {
                String nowparam = request.getParameter(headerparam);
                if(ParamUtils.isBlank(nowparam)){
                    isOK = false;
                    throw new BaseException(CodeMsgVo.HEADER_PARAMETER_LACK.getCode(),CodeMsgVo.HEADER_PARAMETER_LACK.getMsg(),headerparam+"必传");
                }
            }
        }
        return isOK;
    }
}
