package in.co.rays.project_3.dto;

public class BankDTO extends BaseDTO{
	
	
	private String AccountNo;
	private String Name;
	
	

	
	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

	

}
