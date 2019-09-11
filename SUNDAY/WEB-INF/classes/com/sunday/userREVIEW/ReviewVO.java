package com.sunday.userREVIEW;


import com.sunday.cmn.DTO;

public class ReviewVO extends DTO {

	
		
		
		private String docid  ;
		private String user_id ;
		private String contents ;
		private int   user_rate ;
		private String reg_id ;
		private String  reg_dt;
		private String mod_id;
		private String mod_dt;
		private String title;
		
		public ReviewVO(){}

		public ReviewVO(String docid, String user_id, String contents, int user_rate, String reg_id, String reg_dt,
				String mod_id, String mod_dt, String title) {
			super();
			this.docid = docid;
			this.user_id = user_id;
			this.contents = contents;
			this.user_rate = user_rate;
			this.reg_id = reg_id;
			this.reg_dt = reg_dt;
			this.mod_id = mod_id;
			this.mod_dt = mod_dt;
			this.title = title;
			}
		public String getDocid() {
			return docid;
		}

		public void setDocid(String docid) {
			this.docid = docid;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getContents() {
			return contents;
		}

		public void setContents(String contents) {
			this.contents = contents;
		}

		public int getUser_rate() {
			return user_rate;
		}

		public void setUser_rate(int user_rate) {
			this.user_rate = user_rate;
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

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((contents == null) ? 0 : contents.hashCode());
			result = prime * result + ((docid == null) ? 0 : docid.hashCode());
			result = prime * result + ((mod_dt == null) ? 0 : mod_dt.hashCode());
			result = prime * result + ((mod_id == null) ? 0 : mod_id.hashCode());
			result = prime * result + ((reg_dt == null) ? 0 : reg_dt.hashCode());
			result = prime * result + ((reg_id == null) ? 0 : reg_id.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
			result = prime * result + user_rate;
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
			ReviewVO other = (ReviewVO) obj;
			if (contents == null) {
				if (other.contents != null)
					return false;
			} else if (!contents.equals(other.contents))
				return false;
			if (docid == null) {
				if (other.docid != null)
					return false;
			} else if (!docid.equals(other.docid))
				return false;
			if (mod_dt == null) {
				if (other.mod_dt != null)
					return false;
			} else if (!mod_dt.equals(other.mod_dt))
				return false;
			if (mod_id == null) {
				if (other.mod_id != null)
					return false;
			} else if (!mod_id.equals(other.mod_id))
				return false;
			if (reg_dt == null) {
				if (other.reg_dt != null)
					return false;
			} else if (!reg_dt.equals(other.reg_dt))
				return false;
			if (reg_id == null) {
				if (other.reg_id != null)
					return false;
			} else if (!reg_id.equals(other.reg_id))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			if (user_id == null) {
				if (other.user_id != null)
					return false;
			} else if (!user_id.equals(other.user_id))
				return false;
			if (user_rate != other.user_rate)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ReviewVO [docid=" + docid + ", user_id=" + user_id + ", contents=" + contents + ", user_rate="
					+ user_rate + ", reg_id=" + reg_id + ", reg_dt=" + reg_dt + ", mod_id=" + mod_id + ", mod_dt="
					+ mod_dt + ", title=" + title + "]";
		}

		
		

}
