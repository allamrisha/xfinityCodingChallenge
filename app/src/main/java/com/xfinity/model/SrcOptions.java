package com.xfinity.model;

import com.google.gson.annotations.SerializedName;

public class SrcOptions{

	@SerializedName("src_info")
	private String srcInfo;

	@SerializedName("is_mediawiki")
	private int isMediawiki;

	@SerializedName("is_wikipedia")
	private int isWikipedia;

	@SerializedName("skip_abstract_paren")
	private int skipAbstractParen;

	@SerializedName("language")
	private String language;

	@SerializedName("directory")
	private String directory;

	@SerializedName("skip_icon")
	private int skipIcon;

	@SerializedName("is_fanon")
	private int isFanon;

	@SerializedName("source_skip")
	private String sourceSkip;

	@SerializedName("min_abstract_length")
	private String minAbstractLength;

	@SerializedName("skip_image_name")
	private int skipImageName;

	@SerializedName("skip_abstract")
	private int skipAbstract;

	@SerializedName("skip_qr")
	private String skipQr;

	@SerializedName("skip_end")
	private String skipEnd;

	public void setSrcInfo(String srcInfo){
		this.srcInfo = srcInfo;
	}

	public String getSrcInfo(){
		return srcInfo;
	}

	public void setIsMediawiki(int isMediawiki){
		this.isMediawiki = isMediawiki;
	}

	public int getIsMediawiki(){
		return isMediawiki;
	}

	public void setIsWikipedia(int isWikipedia){
		this.isWikipedia = isWikipedia;
	}

	public int getIsWikipedia(){
		return isWikipedia;
	}

	public void setSkipAbstractParen(int skipAbstractParen){
		this.skipAbstractParen = skipAbstractParen;
	}

	public int getSkipAbstractParen(){
		return skipAbstractParen;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setDirectory(String directory){
		this.directory = directory;
	}

	public String getDirectory(){
		return directory;
	}

	public void setSkipIcon(int skipIcon){
		this.skipIcon = skipIcon;
	}

	public int getSkipIcon(){
		return skipIcon;
	}

	public void setIsFanon(int isFanon){
		this.isFanon = isFanon;
	}

	public int getIsFanon(){
		return isFanon;
	}

	public void setSourceSkip(String sourceSkip){
		this.sourceSkip = sourceSkip;
	}

	public String getSourceSkip(){
		return sourceSkip;
	}

	public void setMinAbstractLength(String minAbstractLength){
		this.minAbstractLength = minAbstractLength;
	}

	public String getMinAbstractLength(){
		return minAbstractLength;
	}

	public void setSkipImageName(int skipImageName){
		this.skipImageName = skipImageName;
	}

	public int getSkipImageName(){
		return skipImageName;
	}

	public void setSkipAbstract(int skipAbstract){
		this.skipAbstract = skipAbstract;
	}

	public int getSkipAbstract(){
		return skipAbstract;
	}

	public void setSkipQr(String skipQr){
		this.skipQr = skipQr;
	}

	public String getSkipQr(){
		return skipQr;
	}

	public void setSkipEnd(String skipEnd){
		this.skipEnd = skipEnd;
	}

	public String getSkipEnd(){
		return skipEnd;
	}

	@Override
 	public String toString(){
		return 
			"SrcOptions{" + 
			"src_info = '" + srcInfo + '\'' + 
			",is_mediawiki = '" + isMediawiki + '\'' + 
			",is_wikipedia = '" + isWikipedia + '\'' + 
			",skip_abstract_paren = '" + skipAbstractParen + '\'' + 
			",language = '" + language + '\'' + 
			",directory = '" + directory + '\'' + 
			",skip_icon = '" + skipIcon + '\'' + 
			",is_fanon = '" + isFanon + '\'' + 
			",source_skip = '" + sourceSkip + '\'' + 
			",min_abstract_length = '" + minAbstractLength + '\'' + 
			",skip_image_name = '" + skipImageName + '\'' + 
			",skip_abstract = '" + skipAbstract + '\'' + 
			",skip_qr = '" + skipQr + '\'' + 
			",skip_end = '" + skipEnd + '\'' + 
			"}";
		}
}