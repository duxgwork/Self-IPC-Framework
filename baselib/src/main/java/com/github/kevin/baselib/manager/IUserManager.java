package com.github.kevin.baselib.manager;

import com.github.kevin.baselib.annotion.ClassId;
import com.github.kevin.baselib.bean.Person;

@ClassId("com.github.kevin.baselib.manager.UserManager")
public interface IUserManager {

    Person getPerson();

    void setPerson(Person person);

}
