package com.hashem.java;

class ObjectForReference
{
    public void print()
    {
        System.out.println("I am a live!");
    }

    protected void finalize()
    {
        System.out.println("I am dead!");
    }
}
