package com.github.kevin.processframework;

import com.github.kevin.baselib.annotion.ClassId;
import com.github.kevin.baselib.bean.Person;

@ClassId("com.github.kevin.processframework.UserManager")
public interface IUserManager {

    Person getPerson();

    void setPerson(Person person);

}
