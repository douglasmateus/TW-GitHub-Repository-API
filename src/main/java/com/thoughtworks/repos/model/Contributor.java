package com.thoughtworks.repos.model;

public class Contributor implements Comparable<Contributor>{
	
	private String login;
	private long contributions;
	
	public Contributor(String login, long contributions) {
		this.login = login;
		this.contributions = contributions;
	}
	public String getLogin() {
		return login;
	}
	public long getContributions() {
		return contributions;
	}
	
	@Override
	public int compareTo(Contributor o) {
		if (this.contributions > o.contributions) {
			return -1;
		}
		if (this.contributions < o.contributions) {
			return 1;
		}
		return 0;
	}
}
