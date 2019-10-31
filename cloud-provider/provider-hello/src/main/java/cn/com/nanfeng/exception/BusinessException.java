package cn.com.nanfeng.exception;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-31 15:26
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 839391091046655388L;

    private int code;

    public BusinessException(String message) {
       super(message);
    }

    public BusinessException(int code,String meaasge){
        super(meaasge);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
