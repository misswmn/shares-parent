package com.shares.web.home.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Subscriber {

    @Size(min = 2, max = 30)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Min(13)
    @Max(110)
    private Integer age;

//	@Size(min=10) @Phone
//	private String phone;

    @NotNull(message = "{common.param.illegal}")
    private Gender gender;

//	@DateTimeFormat(pattern="MM/dd/yyyy")
//	@NotNull @Past @Year(1989)
//	private Date birthday;

    private Boolean receiveNewsletter;

    public enum Gender {
        MALE, FEMALE
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getReceiveNewsletter() {
        return receiveNewsletter;
    }

    public void setReceiveNewsletter(Boolean receiveNewsletter) {
        this.receiveNewsletter = receiveNewsletter;
    }

    @Override
    public String toString() {
        return "Subscriber [name=" + name + ", email=" + email + ", age=" + age
                + ", phone=" + "" + ", gender=" + gender + ", birthday="
                + "" + ", receiveNewsletter=" + receiveNewsletter + "]";
    }
}
