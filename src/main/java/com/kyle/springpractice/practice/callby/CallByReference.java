package com.kyle.springpractice.practice.callby;

import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveUnboxingDelegate;

public class CallByReference {
    int value;


    CallByReference(int value) {

        this.value = value;

    }


    public static void swap(CallByReference x, CallByReference y) {

        int temp = x.value;

        x.value = y.value;

        y.value = temp;

    }

    static class A {
        public int value;

        A(int value) {
            this.value = value;
        }
    }
    static void run(A arg1, A arg2) {
        arg1.value = 111;
        arg2.value = arg1.value;
    }

    static void modify(int x1, int x2) {
        x1 = 5;
        x2 = 10;
    }

    static void modify(A x1, A x2) {
        x1.value++;

        x2 = new A(3);
        x2.value++;
    }


    public static void main(String[] args) {

        //call by address 로 call by reference 라고 착각하기 쉽다
        CallByReference a = new CallByReference(10);

        CallByReference b = new CallByReference(20);


        System.out.println("swap() 호출 전 : a = " + a.value + ", b = " + b.value);


        swap(a, b);


        System.out.println("swap() 호출 후 : a = " + a.value + ", b = " + b.value);

        A a1 = new A(1);
        A a2 = new A(2);

        System.out.println(a1.value);
        System.out.println(a2.value);

        run(a1, a2);

        System.out.println(a1.value);
        System.out.println(a2.value);

        // call by value 증거
        int x1 = 1;
        int x2 = 2;

        System.out.println("modify() 호출 전 : x1 = " + x1 + ", x2 = " + x2);

        modify(x1, x2);

        System.out.println("modify() 호출 후 : x1 = " + x1 + ", x2 = " + x2);


        A o1 = new A(1);
        A o2 = new A(2);

        System.out.println("modify() 호출 전 : o1 = " + o1.value + ", o2 = " + o2.value);

        modify(o1, o2);

        System.out.println("modify() 호출 후 : o1 = " + o1.value + ", o2 = " + o2.value);

    }

}
