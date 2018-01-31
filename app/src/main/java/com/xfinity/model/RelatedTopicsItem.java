package com.xfinity.model;

import com.google.gson.annotations.SerializedName;

public class RelatedTopicsItem{

	@SerializedName("Text")
	private String text;

	@SerializedName("Icon")
	private Icon icon;

	@SerializedName("FirstURL")
	private String firstURL;

	@SerializedName("Result")
	private String result;

	public RelatedTopicsItem(String text, Icon icon) {
		this.text = text;
		this.icon = icon;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setIcon(Icon icon){
		this.icon = icon;
	}

	public Icon getIcon(){
		return icon;
	}

	/*public void setFirstURL(String firstURL){
		this.firstURL = firstURL;
	}

	public String getFirstURL(){
		return firstURL;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}*/

	@Override
 	public String toString(){
		return 
			"RelatedTopicsItem{" + 
			"text = '" + text + '\'' + 
			",icon = '" + icon + '\'' + 
			",firstURL = '" + firstURL + '\'' + 
			",result = '" + result + '\'' + 
			"}";
		}
}