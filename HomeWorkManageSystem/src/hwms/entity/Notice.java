package hwms.entity;

public class Notice {
	private int nt_Num;			//公告号
	private String nt_Title;	//主题
	private String nt_Content;	//内容
	private String nt_Time;		//发布时间
	private String nt_t_ID;		//教师号
	private String nt_t_Name;	//教师姓名
	private String nt_cour_ID;		//课程号
	
	public int getNt_Num() {
		return nt_Num;
	}
	public void setNt_Num(int nt_Num) {
		this.nt_Num = nt_Num;
	}
	public String getNt_Title() {
		return nt_Title;
	}
	public void setNt_Title(String nt_Title) {
		this.nt_Title = nt_Title;
	}
	public String getNt_Content() {
		return nt_Content;
	}
	public void setNt_Content(String nt_Content) {
		this.nt_Content = nt_Content;
	}
	public String getNt_Time() {
		return nt_Time;
	}
	public void setNt_Time(String nt_Time) {
		this.nt_Time = nt_Time;
	}
	public String getNt_t_ID() {
		return nt_t_ID;
	}
	public void setNt_t_ID(String nt_t_ID) {
		this.nt_t_ID = nt_t_ID;
	}
	
	public String getNt_t_Name() {
		return nt_t_Name;
	}
	public void setNt_t_Name(String nt_t_Name) {
		this.nt_t_Name = nt_t_Name;
	}
	
	public String getNt_cour_ID() {
		return nt_cour_ID;
	}
	public void setNt_cour_ID(String nt_c_ID) {
		this.nt_cour_ID = nt_c_ID;
	}
	
}
