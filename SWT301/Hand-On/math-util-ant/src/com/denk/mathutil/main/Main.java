/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.denk.mathutil.main;

import com.denk.mathutil.core.MathUtil;

/**
 *
 * @author denk
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        tryTDDFrist();

    }

    // hàm này dùng để demo/minh họa cách TDD được triển khai thế nào
    // viết code song song với viết test
    // TEST             DRIVEN          DEVELOPMENT
    // kiểm thử         hướng về        viết code
    public static void tryTDDFrist() {
        //Test case #1: tình huống 1
        //input n = 0; ta muốn thử tính 0! coi ra cái gì
        //expected = 1; // ta muốn 0! hàm phải trả về 1
        //thực tế actual = ? mấy, chạy thử hàm mới biết được
        long expected = 1; // mình muốn 0! phải trả về 1
        long actual = MathUtil.getFactorial(0);
        // kiểm thử phần mềm là so sánh expected và actual để luận đúng sai

        System.out.println("0! Status | expected: " + expected + " | actual: " + actual);

        //Test case #2: tình huống 2
        // kiểm tra hàm có chạy về đúng với 2!, hy vọng nhận về số 2 
        // input n = 2
        // expected = 2;
        // actual = ?? chạy mới biết
        System.out.println("2! Status | expected: 2 | actual: " + MathUtil.getFactorial(2));

        //Test case #3: 
        // n = 5, 5! hy vọng 120
        System.out.println("5! Status | expected: 120 | actual: " + MathUtil.getFactorial(5));

        //Test case #4: 
        // n = -5, -5! hy vọng bị đập mặt câu, cà chớ, n ko hợp lệ
        System.out.println("-5! Status | expected: Ngoại lệ | actual: ");
        MathUtil.getFactorial(-5);
        
        

    }
}
