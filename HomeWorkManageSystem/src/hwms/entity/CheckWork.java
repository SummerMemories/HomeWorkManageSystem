package hwms.entity;

public class CheckWork {
	private int ch_Num;				//作业批改号
	private String ch_s_Account;	//学号
	private String ch_cour_ID;		//课程ID号
	private int ch_w_Num;			//作业号
	private int ch_Score;			//得分
	private String ch_Mark;			//评语
	private String ch_CheckTime;	//批改时间
	private String ch_UpTime;		//提交时间
	private String ch_Path;			//答案
	
	public int getCh_Num() {
		return ch_Num;
	}
	public void setCh_Num(int ch_Num) {
		this.ch_Num = ch_Num;
	}
	public String getCh_s_Account() {
		return ch_s_Account;
	}
	public void setCh_s_Account(String ch_s_Account) {
		this.ch_s_Account = ch_s_Account;
	}
	
	public String getCh_cour_ID() {
		return ch_cour_ID;
	}
	public void setCh_cour_ID(String ch_cour_ID) {
		this.ch_cour_ID = ch_cour_ID;
	}
	public int getCh_w_Num() {
		return ch_w_Num;
	}
	public void setCh_w_Num(int ch_w_Num) {
		this.ch_w_Num = ch_w_Num;
	}
	public int getCh_Score() {
		return ch_Score;
	}
	public void setCh_Score(int ch_Score) {
		this.ch_Score = ch_Score;
	}
	public String getCh_Mark() {
		return ch_Mark;
	}
	public void setCh_Mark(String ch_Mark) {
		this.ch_Mark = ch_Mark;
	}
	public String getCh_CheckTime() {
		return ch_CheckTime;
	}
	public void setCh_CheckTime(String ch_CheckTime) {
		this.ch_CheckTime = ch_CheckTime;
	}
	public String getCh_UpTime() {
		return ch_UpTime;
	}
	public void setCh_UpTime(String ch_UpTime) {
		this.ch_UpTime = ch_UpTime;
	}
	public String getCh_Path() {
		return ch_Path;
	}
	public void setCh_Path(String ch_Path) {
		this.ch_Path = ch_Path;
	}
}
