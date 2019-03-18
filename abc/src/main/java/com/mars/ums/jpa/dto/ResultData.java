package com.mars.ums.jpa.dto;

/**
 * Controller数据返回结果统一
 * Created  by sunam
 * 2019/1/18 14:15
 */
public class ResultData {
    private int code = -1;//返回状态码
    private boolean result;//返回结果
    private String msg;//描述信息
    private Object data = "";//具体数据

    public ResultData(int code, boolean result){
        this.code = code;
        this.result = result;
    }
    public ResultData(boolean result){
        this.result = result;
    }

    /***********************成功的情况************************/

    //1.只有返回状态和返回结果
    public static ResultData success(){
        ResultData resultData = new ResultData(200,true);
        return resultData;
    }
    //2.具体数据参数、返回状态和返回结果
    public static ResultData success(Object data){
        ResultData resultData = new ResultData(200,true);
        resultData.setData(data);
        return resultData;
    }
    //3.具体数据参数、描述信息、返回状态和返回结果
    public static ResultData success(String msg, Object data){
        ResultData resultData = new ResultData(200,true);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    //1.只有返回状态和返回结果
    public static ResultData success(String msg){
        ResultData resultData = new ResultData(200,true);
        resultData.setMsg(msg);
        return resultData;
    }

    /***********************失败的情况************************/

    //1.只有返回结果
    public static ResultData fail(){
        ResultData resultData = new ResultData(false);
        return resultData;
    }
    //2.描述信息、返回结果
    public static ResultData fail(String msg){
        ResultData resultData = new ResultData(false);
        resultData.setMsg(msg);
        return resultData;
    }
    public static ResultData fail(int code, String msg){
        ResultData resultData = new ResultData(code,false);
        resultData.setMsg(msg);
        return resultData;
    }
    public static ResultData fail(String msg, Object data){
        ResultData resultData = new ResultData(-1,false);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    //3.具体数据参数、描述信息、返回状态和返回结果
    public static ResultData fail(int code, String msg, Object data){
        ResultData resultData = new ResultData(code,false);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
