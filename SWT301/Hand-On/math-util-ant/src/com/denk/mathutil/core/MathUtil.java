package com.denk.mathutil.core;

/**
 *
 * @author denk
 */
// ta sẽ mô phỏng/clone cái class math của jdk
//Class của ta viết sẽ cung câp các hàm/method tiện íc dùng cho nhiều nơi khác nhau
// thường cái gì mà là tiện ích/tool dùng cho các nơi thì nó sẽ được thiết kế
// là STATIC
public class MathUtil {

    public static final double PI = 3.141615;

    // hàm tiện ích dùng chung, tính giai thưa
    // n! = 1.2.3.4....n;
    // không tính giai thừa số âm
    // 0! = 1! = 1;
    // 21! là cực kỳ to, vượt 18 số 0 -> tràn kiểu long
    // ta quy ước không tính 21! trở lên , vì tràn long
    public static long getFactorial(int n) {

        if (n <= -1 || n >= 21) {
            throw new IllegalArgumentException("Invalid n. n must be between 0...20");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        // sống đến đây, sure, n chạy yuwf 2...20
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;

    }
}

// KĨ THUẬT TDD - TEST DRIVEN DEVELOPMENT
//	Là kĩ thuật lập trình/phong cách lập trình mà khi viết code phải viết 
//          luôn các bộ kiểm thử để kiểm tra ngay code của mình chạy đúng hay sai
//	Viết code song song với viết các TEST CASES
//	KĨ thuật này giúp code liên tục liên tục đưẹoc kiểm tra xem mày chạy có
//          đúng như kỳ vọng hay không
//	Nếu ta xài TDD nó sẽ giúp ta đi vào cái quy trình 
//          CI – COUNTINUOUS INTEGRATION
//	Để làm TDD Ta:
//•	1. Viết phác thảo prototype của hàm/method/class
//•	2. Viết các bộ kiểm thử/Test cases là các đoạn code bổ sung thêm nhưng là code để xài cái hàm vừa viết để check xem hàm vừa viết ổn không
//•	3. Chạy thử hàm vừa viết vowid bộ test case vừa tạo, xem tình hình hàm đúng sai ra sao. Sai -> Refator, sửa code|Đúng -> Hoàn thiện code khác
    //•	LIÊN TỤC LIÊN TỤC NHƯ VẬY
