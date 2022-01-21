package vo;

public class WelcomeVO {
	public int id;
	public String msg;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public WelcomeVO()
	{
		
	}
	
	public WelcomeVO(int id, String msg)
	{
		this.id = id;
		this.msg = msg;
	}
	
}
