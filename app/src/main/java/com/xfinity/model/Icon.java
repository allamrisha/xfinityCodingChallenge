package com.xfinity.model;

import com.google.gson.annotations.SerializedName;

public class Icon{

	@SerializedName("Height")
	private String height;

	@SerializedName("Width")
	private String width;

	@SerializedName("URL")
	private String uRL;

	public Icon(String uRL) {
		this.uRL = uRL;
	}

	public void setHeight(String height){
		this.height = height;
	}

	public String getHeight(){
		return height;
	}

	public void setWidth(String width){
		this.width = width;
	}

	public String getWidth(){
		return width;
	}

	public void setURL(String uRL){
		this.uRL = uRL;
	}

	public String getURL(){
		return uRL;
	}

	@Override
 	public String toString(){
		return 
			"Icon{" + 
			"height = '" + height + '\'' + 
			",width = '" + width + '\'' + 
			",uRL = '" + uRL + '\'' + 
			"}";
		}
}