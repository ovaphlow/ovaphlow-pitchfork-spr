package ovaphlow.pitchfork.spr.entity;

/*
CREATE TABLE pitchfork."user" (
	id bigserial NOT NULL,
	dept_id int8 NOT NULL,
	"name" varchar NOT NULL,
	phone varchar NOT NULL,
	"password" varchar NOT NULL,
	salt varchar NOT NULL,
	tag jsonb NOT NULL,
	dept varchar NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);
 */

public class User {
    Long id;
    String name;
    String password;
    String salt;
    String phone;
    String dept;
    String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", dept='" + dept + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
