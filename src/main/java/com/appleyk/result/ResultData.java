/**
 * 
 */
package com.appleyk.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Title: ResultData.java Description: TODO
 * 
 * @author : xhli 2017年8月15日上午10:11:06
 * 
 *         Version 1.0
 */
public class ResultData<T> {
	private ResponseMessage state;
	private Object data;
	private List<T> dataList;
	

	public ResultData(ResponseMessage state) {

		this.state = state;
	}

	public ResultData(ResponseMessage state, Object data) {
		this.data = data;
		this.state = state;
	}
	
	public ResultData(ResponseMessage state, long data, boolean isId){
		this.state = state;
		Map<String, Long> map = new HashMap<>();
		map.put("id", data);
		this.data = map;
	}
	
	public ResultData(ResponseMessage state, long id){
		this.state = state;
		Map<String, Long> map = new HashMap<>();
		map.put("id", id);
		this.data = map;
	}
	
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	
	public ResponseMessage getState(){
		return state;
	}
	
	public List<T> getDataList(){
		return dataList;
	}

}
