package com.wsywddr.sample.model;

/**
 * Created by chenwei on 17-3-10.
 */

public class PersonBd {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private PersonBd(Builder builder)
    {
        this.name=builder.name;
        this.age=builder.age;
    }

    public static class Builder
    {
        private String name;
        private String age;

        public Builder name(String name)
        {
            this.name=name;
            return this;
        }

        public Builder age(String age)
        {
            this.age=age;
            return this;
        }

        public PersonBd build()
        {
            return new PersonBd(this);
        }

    }
}
