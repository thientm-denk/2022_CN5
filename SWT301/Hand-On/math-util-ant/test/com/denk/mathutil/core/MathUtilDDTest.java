
package com.denk.mathutil.core;
// import kiểu này chỉ cần dùng method
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value = Parameterized.class)
public class MathUtilDDTest {
    // chuẩn bị data: mảng 2 chiều vì có data n đưa vào và expected
    // và có nhiều cặp như thế
    @Parameterized.Parameters
    public static Object[][] initData(){
        int a[] = {5 , 10 ,15 ,20};
        int b[][] = {{1, 0}, 
                     {1, 1}, 
                     {2, 2}, 
                     {6, 3}, 
                     {24, 4}, 
                     {120, 5}, 
                     {720, 6}
                    };
        // nhưng là màng của object nên ko thể dùng int mà phải là wrapper class
        Integer c[][] = {{1, 0}, 
                     {1, 1}, 
                     {2, 2}, 
                     {6, 3}, 
                     {24, 4}, 
                     {120, 5}, 
                     {720, 6}
                    };
        
        return c;
    }
    
    
    // sau khi có bộ data qua mảng 2 chiều, JUnit sẽ tự lặp For để lôi ra từng cặp
//    datta và nhồi cặp này vào trong hàm so sánh
//    nhưng nhồi bằng cách nào?
//    gán cái value này vào biến nào đó
//    Gán vào biến - THAM SỐ HÓA PARAMETERIZED
//    ta sẽ map/ánh xạ 2 cột ứng với 2 viến
    
    @Parameterized.Parameter(value = 0)
    public long expected;
    
    @Parameterized.Parameter(value = 1)
    public int n;
    
    // test hoi vì đã cod test case và data
    @Test
    public void testFactorialGivenRightArgumentReturnWell(){
        assertEquals(expected, MathUtil.getFactorial(n));
    }
}


// CLASSS Này sẽ chứa code dùng để test code chính ở bên class MathUtil
// CLASS này sẽ chứa code dùng để test hame getF() coi hàm chạy đúng hok
// Code viết ra dùng để test code khác (Hàm khác/ class khác)
// thì đoạn code này, class này được gọi tên là: TEST SCRIPT
// trong test scriptr sẽ có những tình huống xài app, đưa data cụ thể vào
// chờ xem hàm xử lí kết quả có như kỳ vòng hay không?
// một test scriot sẽ chưa nhiều TEST CASES
//                                  Mỗi test case ứng với 1 tình huỗng xài gàm
//Phân tích Test Scriptre cũ - hôm qua ôm kia
//trong scriptrs cũ xuất hiện bad Smell: sự trùng lập câu lệnh
// Lệnh so sánh giá trị, lệnh gọi hàm được lập đi lập lại với mỗi bộ test case
// mỗi câu là 1 data.

// vậy có cách nào kiểu thay 2 con số trong lệnh so sánh = 2 ??? nào đó
// nếu ta tạc dduweocj toàn bộ data trong các câu lệnh so sánh ở trên
// ra 1 chỗ riêng biệt sau đó 
//ta chỉ việc pick/lấy data này napk dần vào lệnh thì ta sẽ đặt được:
// - Code rõ ràng ko bị trùng lập
// - Nhìn tổng quan biết có bao nhiêu test case và liệu ràng chúng đủ hơi chưa

// kỹ thuật viết test script (câu lệnh test) mà tách biệt data ra khỏi lệnh so sánh
// đươẹc gọi bằng nhứng tên sau
// PARACETERIZED - THAM SỐ HÓA, BIẾN DATA RA MỘT CHỖ ĐẶT CHO CHÚNG CÁI TÊN
//                  LÁT NHỒI CHÚNG TRỞ LẠI LỆNH SO SÁNH
// DDT - DATA DRIVEN TESTING: VIÊT CODE KIỂM THỬ THEO STYLE TÁCH DATA

// JUNIT FW HỖ TRỢ SẴN TA VỤ TÁCH DÂT, DUYỆT VÒNG FOR TRÊN DATA
// DUYỆT HÀM TYUOWNG ỨNG LUÔN

// ĐỂ CHƠI VỚI DDT:
// - Tách Data ra một chỗ - Mảng
// - Map cái Data này vào các biến tương ứng
// - nhôi các biến tương ứng này vào câu lệnh so sánh/ gọi hàm

