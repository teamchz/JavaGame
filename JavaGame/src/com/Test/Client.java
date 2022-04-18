package com.Test;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("localhost", 6789);

        InputStream inputStream = s.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        Object y = objectInputStream.readObject();
        System.out.println(y);
    }
}

