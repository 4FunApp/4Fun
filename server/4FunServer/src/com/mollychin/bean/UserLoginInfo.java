package com.mollychin.bean;

import java.util.List;

public class UserLoginInfo {
	private boolean error;
	private List<Bean> result;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<Bean> getResult() {
		return result;
	}

	public void setResult(List<Bean> result) {
		this.result = result;
	}

	public static class Bean {
		private String userName;
		private String password;
		private int sex;
		private String email;
		private int code;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
