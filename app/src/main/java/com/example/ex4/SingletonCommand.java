package com.example.ex4;

public class SingletonCommand{
    // static variable single_instance of type Singleton
    private static Command single_instance = null;

    // variable of type String
    public String s;

    // private constructor restricted to this class itself
    private SingletonCommand()
    {
        s = "Hello I am a string part of Singleton class";
    }

    // static method to create instance of Singleton class
    public static Command getInstance()
    {
        if (single_instance == null)
            single_instance = new Command();

        return single_instance;
    }

}
