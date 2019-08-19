package resultCode;

import java.io.Serializable;

public class ResultDto<T> implements Serializable{
	
	private static final long serialVersionUID = -5699331628836038284L;
	
	private int code;
	private String msg;
	private T data;
	
	
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	public ResultDto() {
		
	}
	
	public ResultDto (int code,String msg,T data) {
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public  ResultDto<T> successDto(T data){
		ResultDto<T> resultDto=new ResultDto<>();
		resultDto.setCode(200);
		resultDto.setMsg("success");
		resultDto.setData(data);
		return resultDto;
	}
	
	public  ResultDto<T> failedDto(T data){
		ResultDto<T> resultDto=new ResultDto<>();
		resultDto.setCode(500);
		resultDto.setMsg("failed");
		resultDto.setData(data);
		return resultDto;
	}

	@Override
	public String toString() {
		return "ResultDto [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
 	
	

}
