package com.thoughtworks.repos.model;

public class Contributor implements Comparable<Contributor>{
	
	private String login;
	private long contributions;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public long getContributions() {
		return contributions;
	}
	public void setContributions(long contributions) {
		this.contributions = contributions;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (contributions ^ (contributions >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contributor other = (Contributor) obj;
		if (contributions != other.contributions)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Contributor [login=" + login + ", contributions=" + contributions + "]";
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
