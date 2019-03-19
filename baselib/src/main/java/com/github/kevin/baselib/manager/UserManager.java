package com.github.kevin.baselib.manager;


import com.github.kevin.baselib.bean.Person;

public class UserManager implements IUserManager{
    private static UserManager instance = null;
    private Person person;

    public UserManager(){

    }

    //架构约束
    public static synchronized UserManager getInstance(){
        if (instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }
}
