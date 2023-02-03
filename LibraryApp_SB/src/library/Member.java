package library;

public class Member {

	private int id;
	private String name;
	private String email;
	private String phone;
	private String membershipDate;
	
	public Member(int id, String name, String email, String phone, String membershipDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.membershipDate = membershipDate;
	}
	
	public Member(String name, String email, String phone, String membershipDate) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.membershipDate = membershipDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMembershipDate() {
		return membershipDate;
	}

	public void setMembershipDate(String membershipDate) {
		this.membershipDate = membershipDate;
	}

}