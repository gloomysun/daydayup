package com.ly;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ByteBuf delimiter = Unpooled.copiedBuffer("_$".getBytes());
        System.out.println("_$".getBytes());
        System.out.println(delimiter);
    }
}
