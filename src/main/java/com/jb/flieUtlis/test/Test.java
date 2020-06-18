package com.jb.flieUtlis.test;

import com.jb.flieUtlis.OSSFileImp;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        String s = new OSSFileImp().ossByteUpload("蒋蒋6666".getBytes(), "byte.txt");
        System.out.println(s);
    }
}
