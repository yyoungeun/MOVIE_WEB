package com.sunday.member;

import java.util.List;

import com.sunday.cmn.DTO;


public class MemberVO extends DTO{
		private String user_id;
		private String passwd;
		private String name;
		private String email;
		private String lvl;
		private String reg_id;
		private String reg_dt;
		private String mod_id;
		private String mod_dt;
		
		public MemberVO() {}

		public MemberVO(String user_id, String passwd, String name, String email, String lvl, String reg_id,
				String reg_dt, String mod_id, String mod_dt) {
			super();
			this.user_id = user_id;
			this.passwd = passwd;
			this.name = name;
			this.email = email;
			this.lvl = lvl;
			this.reg_id = reg_id;
			this.reg_dt = reg_dt;
			this.mod_id = mod_id;
			this.mod_dt = mod_dt;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLvl() {
			return lvl;
		}

		public void setLvl(String lvl) {
			this.lvl = lvl;
		}

		public String getReg_id() {
			return reg_id;
		}

		public void setReg_id(String reg_id) {
			this.reg_id = reg_id;
		}

		public String getReg_dt() {
			return reg_dt;
		}

		public void setReg_dt(String reg_dt) {
			this.reg_dt = reg_dt;
		}

		public String getMod_id() {
			return mod_id;
		}

		public void setMod_id(String mod_id) {
			this.mod_id = mod_id;
		}

		public String getMod_dt() {
			return mod_dt;
		}

		public void setMod_dt(String mod_dt) {
			this.mod_dt = mod_dt;
		}

		@Override
		public String toString() {
			return "memberVO [user_id=" + user_id + ", passwd=" + passwd + ", name=" + name + ", email=" + email
					+ ", lvl=" + lvl + ", reg_id=" + reg_id + ", reg_dt=" + reg_dt + ", mod_id=" + mod_id + ", mod_dt="
					+ mod_dt + ", toString()=" + super.toString() + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((lvl == null) ? 0 : lvl.hashCode());
			result = prime * result + ((mod_id == null) ? 0 : mod_id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
			result = prime * result + ((reg_id == null) ? 0 : reg_id.hashCode());
			result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
			MemberVO other = (MemberVO) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (lvl == null) {
				if (other.lvl != null)
					return false;
			} else if (!lvl.equals(other.lvl))
				return false;
			if (mod_id == null) {
				if (other.mod_id != null)
					return false;
			} else if (!mod_id.equals(other.mod_id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (passwd == null) {
				if (other.passwd != null)
					return false;
			} else if (!passwd.equals(other.passwd))
				return false;
			if (reg_id == null) {
				if (other.reg_id != null)
					return false;
			} else if (!reg_id.equals(other.reg_id))
				return false;
			if (user_id == null) {
				if (other.user_id != null)
					return false;
			} else if (!user_id.equals(other.user_id))
				return false;
			return true;
		}

}
