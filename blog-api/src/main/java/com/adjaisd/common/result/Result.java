package com.adjaisd.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

// 统一返回结果
@Data
public class Result {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    // 私有化构造方法
    private Result(){}

    // 成功
    public static Result succ(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCESS);
        result.setMessage("成功");
        return result;
    }

    // 失败
    public static Result fail(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public Result Code(int code) {
        this.code = code;
        return this;
    }

    public Result code(int code){
        this.setCode(code);
        return this;
    }

    public Result message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result data(String key, Object val){
        this.getData().put(key, val);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
