package com.sparticles.vo;


import com.sparticles.enums.ResultEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2019/8/18 7:22 PM
 * @Auther smart
 **/
public class ResultVOUtils {

    /**
     * 成功时返回
     * @param data 返回的data对象
     * @return {@link BaseResVO}
     */
    public static BaseResVO success(Object data) {
        BaseResVO<Object> baseResVO = new BaseResVO<>();
        baseResVO.setCode(200);
        baseResVO.setMessage("success");
        baseResVO.setData(data);
        return baseResVO;
    }

    /**
     * 成功时返回
     * @return {@link BaseResVO}
     */
    public static BaseResVO success() {
        Map data = new HashMap();
        return success(data);
    }

    /**
     * 错误时返回
     * @param code 错误码
     * @param message 错误信息
     * @return {@link BaseResVO}
     */
    public static BaseResVO error(Integer code, String message) {
        BaseResVO<Object> baseResVO = new BaseResVO<>();
        baseResVO.setCode(code);
        baseResVO.setMessage(message);
        Map data = new HashMap();
        baseResVO.setData(data);
        return baseResVO;
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @return {@link BaseResVO}
     */
    public static BaseResVO error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMessage());
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @param message 错误的信息
     * @return {@link BaseResVO}
     */
    public static BaseResVO error(ResultEnum resultEnum, String message) {
        return error(resultEnum.getCode(), message);
    }

    /**
     * 默认的错误
     * @return {@link BaseResVO}
     */
    public static BaseResVO error() {
        return error(ResultEnum.NOT_NETWORK);
    }
}
