package demo.jpa.relationships.dto;

import org.springframework.validation.ObjectError;

import java.util.List;

public class RestResponseBody {


    String msg;

    private List<?> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }
}
