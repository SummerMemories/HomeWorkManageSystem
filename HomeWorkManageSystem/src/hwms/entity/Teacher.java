package hwms.entity;

public class Teacher {
	private int t_Num;
	private String t_Account;		//IDºÅ
	private String t_Name;			//ĞÕÃû
	private String t_Password;		//ÃÜÂë
	
	public int getT_Num() {
		return t_Num;
	}
	public void setT_Num(int t_Num) {
		this.t_Num = t_Num;
	}
	public String getT_Account() {
		return t_Account;
	}
	public void setT_Account(String t_Account) {
		this.t_Account = t_Account;
	}
	public String getT_Name() {
		return t_Name;
	}
	public void setT_Name(String t_Name) {
		this.t_Name = t_Name;
	}
	public String getT_Password() {
		return t_Password;
	}
	public void setT_Password(String t_Password) {
		this.t_Password = t_Password;
	}
}
