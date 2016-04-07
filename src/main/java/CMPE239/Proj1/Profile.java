package CMPE239.Proj1;


public class Profile {

		 String age;
		 public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return "profile [age=" + age + ", gender=" + gender + ", id=" + id + "]";
		}
		String gender;
		 Long id;
}