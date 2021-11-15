package com.lut.licon.netty.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 13:55
 */
public class BufferTest {
    public static void main(String[] args) {
        /*IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            int i = intBuffer.get();
            System.out.println(i);
        }*/
        test();
    }

    public static void test(){
        IntBuffer buffer = IntBuffer.allocate(5);
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        buffer.put(5);

        System.out.println(Arrays.toString(buffer.array()));

        buffer.clear();
        buffer.put(100);
        buffer.put(101);

        for (int i = 0; i < buffer.position(); i++) {
            System.out.print(buffer.get(i)+" ");
        }
        //System.out.println(Arrays.toString(buffer.array()));
    }
}
