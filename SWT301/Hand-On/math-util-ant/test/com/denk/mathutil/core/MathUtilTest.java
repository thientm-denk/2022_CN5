package com.denk.mathutil.core;

// TRONG CLASS NÀY CHỨA CÁC ĐOẠN CODE DÙNG ĐỂ

import org.junit.Assert;
import org.junit.Test;



public class MathUtilTest {
    // mỗi @Test tương đương 1 case
    
    // kiểm thử tình huống cà chớn đưa data vào cà chớn thì nhận về cú tát
    // EXPECTION
    // Hàm getFactorial được thiết kết để:
    // - Nếu đưa vào n từ tế    -> sure phải ra n! đúng đắn
    // - nếu đưa vào n cà chớn n < 0 và n > 20
    // CHỬI ĐI và ném ra EXCEPTION/iLLEGALARGUMENTEXCEPTION
    // bây giờ, nếu tui đưa -5 cho hàm, tui nhận về gi??? ngoại lệ
    // thấy ngoại lệ thì mừng rơi nước mắt
    
    // Thấy ngoại lên như kì vọng -5! -> viết code đúng rồi, màu xanh
    
    
    // Lỗi cần bắt thì đặt như này
    // Lỗi quăng ra cần phải giống nhau mới xanh được -> một object thuộc class
    // ngoại lệ
    // phân cấp
//    java.lang.Object
//      java.lang.Throwable
//          java.lang.Exception
//              java.lang.RuntimeException
//                  java.lang.IllegalArgumentException
//                  ...
//    càng lên cao thì bắt lỗi càng rộng
    
    @Test(expected = RuntimeException.class)
    public void testFactorialGivenCaChonArgument(){
        // TEST CASE 7
        // n = -5, tính -5 hy vọng bị vả bô mặt
        // ko xài assertEquals được
        // ngoại lệ thì phải dùng 1 thứ đo lường = 1 cách mày có xuất hiện hay ko
        // mày ko là 1 giá trị kiểu 5 10 15 20
        MathUtil.getFactorial(-5);// ngoại lệ cầm chắc trong tay
        // nhưng phải dò xem mày có xuất hiện nguyên hình ko
        //
        
        
    
    }
    
    @Test  
    public void testFactorialGivenRightArgumentReturnWell2(){
        // TEST CASE 4
        // n = 3; 3! hy vọng 6
        Assert.assertEquals(6, MathUtil.getFactorial(3));
        
        // TEST CASE 5
        // n = 4; 4! hy vọng 24
        Assert.assertEquals(24, MathUtil.getFactorial(4));
        
        // TEST CASE 6
        // n = 5; 5! hy vọng 120
        Assert.assertEquals(120, MathUtil.getFactorial(5));
        
        
    }
    
    @Test 
    public void tryAssertion(){
        Assert.assertEquals(100, 100);
    }
    
    @Test 
    // Quy tắc đặt tên hàm của dân kiểm thử
    // viết cho tường minh, phải mang ý nghĩa
    public void testFactorialGivenRightArgumentReturnWell(){
        // TEST CASE 1 - tình huống xài hàm getF() đầu tiên
        // n = 0, 0! hi vọng expected = 1; actual thực tế là mấy, đoán xem
        // gọi hàm và chạy hàm mới biết đưẹoc
        // nếu expected == actual, hàm đúng, MÀU XANH
        // nếu expected != actual thì có gì đó SAI SAI
        
        long expected = 1; // mình muốn 0! phải trả về 1
        long actual = MathUtil.getFactorial(0);
        // phải so sánh 2 giá trị này
        Assert.assertEquals(expected, actual);              
        // Vietsub: so sánh xem 0! có đúng là trả về 1 hem
    
        //TESTCASE 2: 
        // n = 1; 1! hy vọng trả về 1
        expected = 1;
        actual = MathUtil.getFactorial(1);
        Assert.assertEquals(expected, actual);
        
        
    }
    
    
}
// TÉST CÁC CODE CHÍNH Ở BÊN THƯ MỤC SOURCE
// CÁC ĐOẠN CODE Ở ĐÂY SẼ DÙNG ĐỂ TEST CÁI HÀM
// GETFACTORIAL() COI CHẠY CÓ ĐÚNG KO
// VIẾT CODE ĐỂ TEST CODE
// CODE Ở TRONG NÀY DO DEVELOPER VIẾT RA DÙNG ĐỂ TEST CHÍNH CÁI CODE Ở
// TRONG THƯ MỤC SOURCE ĐỂ ĐẢM BẢO RẰNG HÀM HAY CLASS VIẾT RA PHẢI CHẠY ĐÚNG
// ĐOẠN CODE DÙNG ĐỂ TEST CODE THÌ ĐƯỢC GỌI LÀ TEST SCRIPT
//
// CÁC TEST SCRIPT NÀY SẼ CÓ LỆNH CƠ BẢN: SO SÁNH GIỮ EXPECTED VS ACTIUAL
// NHƯ BÊN HÀM MAIN() ĐÃ LÀM THỬ !!!

// NHỮNG ĐOẠN CODE NÀY KHÔNG DÙNG LẾNH SOUT() MÀ DÙNG NHỮNG THƯ VIỆN ĐẶC BIỆT
// ĐỂ KHI HÀM SAI, KHI EXPECTED != ACTUAL THÌ NÉM RA MÀU ĐỎ
// KHI HÀM CHẠY ĐÚNG, TỨC LÀ EXPECTED == ACTUAL THÌ MÀU XANH
// Các thư viện giúp thảy ra màu xanh đỏ, tự so sánh giùm expected và actual
// để kết luận hàm đúng sai, mắt lúc này chỉ cần nhìn đúng 2 màu xanh đỏ không
// cần xem chi tiết các dòng so sánh như bên main
// bộ thư viện này còn gọi là
// UNIT TEST FARMWORK
// mỗi ngôn ngữ lập trình đều có vài bộ thư viện thảy ra màu xanh đỏ
// giúp các dev test hàm của mình và ví dụ
// JAVA :JUnit, TestNG
// C#   : xUnit, NUnit, MSTest
// PHP  : phpUnit 
// JavaScript:
// Python:
// Google gõ câu Unit Test Farmwork for ...

// CODE nào ép viết đúng cú pháp thì chính là famework
// Thư Viện chỉ gọi ra, thoải mái

// @TEST được gọi là một cái cờ đánh dấu (Anotation) để báo hiệu cho
// thư viện JUnit biết cần Generate hàm đi kèm @Test và biến nó thành hàm main 
// và gửi cái main này cho JVM - Java Vituyal Machine biết để chạy, khi chạy
// thì so sánh 2 đữa Expected và ac tu al , coi nếu chúng == nhau, thày màu xanh
// khác nhau thì thày màu đỏ

// nếu ko có @TEST, chả cả hàm Main() nào dược Generate, No runable Test thì đỏ
// Việc xài thư viện mà bị ép phải làm những gì đó, theo quy tắc nào đó
// được gọi là FAMEWORK


// CHỐT DEAL: Xanh khi tất cả các testcase cùng xanh, xanh khi tất cả các việc so sánh
// đều == actual

// Xanh: tất cả đúng
// xanh tất cả các so sánh đều bằng nhau

// đỏ: chỉ cần 1 thằng trong đám test đỏ, tất cả đỏ
// chỉ cần 1 việc so sánh đỏ, tất cả đỏ

// LOGIC: Hàm nếu đã đúng, thì phải đúng với tất cả các case đã được liệt kê
// hàm ngon với tất cả case đã test
// cố gắng liệt kê được đủ case là tốt nhất
// nguyên lý số 2

// CHỐT DEAL 2:
// khi ra màu xanh tổng, hàm ổn với tất các test case
// khi ra màu đỏ, nghĩa là có ít nhất 1 việc so sánh EXPECTED != ACTUAL
// Màu đỏ vì lí do gì phía sau
//  + Do actual trả về ko đúng -> BUG
//  + Do Expected sai, QC/TESTER bị ngáo
//