package com.xfinity.model;

import com.google.gson.annotations.SerializedName;

public class Maintainer{

	@SerializedName("github")
	private String github;
/*

	public void setGithub(String github){
		this.github = github;
	}

	public String getGithub(){
		return github;
	}
*/

	@Override
 	public String toString(){
		return 
			"Maintainer{" + 
			"github = '" + github + '\'' + 
			"}";
		}
}