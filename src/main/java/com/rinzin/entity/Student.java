package com.rinzin.entity;

import java.io.Serializable;

/**
 * @BelongsProject:IntelliJ IDEA
 * @BelongsPackage:com.rinzin.entity
 * @Author:Rinzin
 * @CreateTime:2020-05-23-13-24
 * @Decription:测试实体
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -7956202241072972369L;
    private String name;
    private String phone;
    private int age;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, String phone, int age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
