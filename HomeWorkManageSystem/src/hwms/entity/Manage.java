package hwms.entity;

public class Manage {
	private int m_Num;
	private String m_ID;			//ID号
	private String m_Account;   	//用户名
	private String m_Name;			//姓名
	private String m_Password;		//密码

	
	public int getM_Num() {
		return m_Num;
	}
	public void setM_Num(int m_Num) {
		this.m_Num = m_Num;
	}
	public String getM_Account() {
		return m_Account;
	}
	public void setM_Account(String m_Account) {
		this.m_Account = m_Account;
	}
	public String getM_Name() {
		return m_Name;
	}
	public void setM_Name(String m_Name) {
		this.m_Name = m_Name;
	}
	public String getM_ID() {
		return m_ID;
	}
	public void setM_ID(String m_ID) {
		this.m_ID = m_ID;
	}
	public String getM_Password() {
		return m_Password;
	}
	public void setM_Password(String m_Password) {
		this.m_Password = m_Password;
	}
}
