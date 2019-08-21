package com.sparticles.vo;


import lombok.Data;

/**
 * @Description
 * @Date 2019/8/18 7:23 PM
 * @Auther smart
 **/
@Data
public class BaseResVO<T> {

    private Integer code;

    private String message;

    private T data;
}
