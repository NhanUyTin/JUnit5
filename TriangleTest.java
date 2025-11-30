package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TriangleTest {
    Triangle triangle = new Triangle();

    @Nested
    @DisplayName("Co Phai Tam Giac Khong")
    class isTriangleTest {
        @Test
        @DisplayName("Tam giác thường 3-4-5 hợp lệ")
        void tamGiac345HopLe() {
            boolean result = triangle.isTriangle(3, 4, 5);
            Assertions.assertTrue(result, "Input 3,4,5 phải là tam giác hợp lệ");
        }

        @Test
        @DisplayName("Cạnh âm hoặc bằng 0")
        void negativeInput() {
            Assertions.assertFalse(triangle.isTriangle(0, 5, 5), "Fail tại a");
            Assertions.assertFalse(triangle.isTriangle(5, 0, 5), "Fail tại b");
            Assertions.assertFalse(triangle.isTriangle(5, 5, 0), "Fail tại c");
        }

        @Test
        @DisplayName("Tổng vi phạm bất đẳng thức tam giác")
        void error() {
            Assertions.assertFalse(triangle.isTriangle(1, 2, 3));
            Assertions.assertFalse(triangle.isTriangle(2, 6, 2));
            Assertions.assertFalse(triangle.isTriangle(6, 2, 2));
        }
    }

    @Nested
    @DisplayName("Phan Loai Tam Giac")
    class LoaiTamGiacTest {
        @Test
        @DisplayName("Loai: Tam giác đều")
        void tamGiacDeu() {
            String result = triangle.loaiTamGiac(2, 2, 2);
            Assertions.assertEquals("Tam giac deu", result);
        }

        @Test
        @DisplayName("Loai: Tam giác cân")
        void tamGiacCan() {
            Assertions.assertEquals("Tam giac can", triangle.loaiTamGiac(8, 5, 5));
            Assertions.assertEquals("Tam giac can", triangle.loaiTamGiac(5, 5, 8));
            Assertions.assertEquals("Tam giac can", triangle.loaiTamGiac(5, 8, 5));
        }

        @Test
        @DisplayName("Loai: Tam giác vuông")
        void tamGiacVuong() {
            Assertions.assertEquals("Tam giac vuong", triangle.loaiTamGiac(3, 4, 5));
            Assertions.assertEquals("Tam giac vuong", triangle.loaiTamGiac(3, 5, 4));
            Assertions.assertEquals("Tam giac vuong", triangle.loaiTamGiac(5, 3, 4));
        }

        @Test
        @DisplayName("Loai: Tam giác thường")
        void tamGiacThuong() {
            // 4-5-6 không phải vuông, không cân, không đều
            Assertions.assertEquals("Tam giac thuong", triangle.loaiTamGiac(4, 5, 6));
        }

        @Test
        @DisplayName("Classify: Ném lỗi khi input sai")
        void testClassify_ThrowsException() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                triangle.loaiTamGiac(1, 2, 10);
            });
        }
    }

    @Nested
    @DisplayName("Tinh Tam Giac")
    class CalculationTests {
        @Test
        @DisplayName("Tính chu vi")
        void testPerimeter() {
            Assertions.assertEquals(12.0, triangle.chuViTamGiac(3, 4, 5), 0.001);
        }

        @Test
        @DisplayName("Tính diện tích")
        void testArea() {
            Assertions.assertEquals(6.0, triangle.dienTichTamGiac(3, 4, 5), 0.001);
            Assertions.assertEquals(1.732, triangle.dienTichTamGiac(2, 2, 2), 0.001);
        }

        @Test
        @DisplayName("Tính toán ném lỗi")
        void testCalculationThrowError() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                triangle.dienTichTamGiac(-1, 5, 5);
            });
        }
    }

    @Nested
    @DisplayName("Report Tam Giac")
    class ReportTests {
        @Test
        @DisplayName("Report: Kiểm tra nội dung báo cáo đầy đủ")
        void testFullReport() {
            String report = triangle.tongHopTamGiac(3, 4, 5);
            System.out.println("Actual Report Output: " + report);
            Assertions.assertAll("Check report content",
                    () -> Assertions.assertTrue(report.contains("Tam giac vuong"), "Thiếu loại tam giác"),
                    () -> Assertions.assertTrue(report.contains("12.00"), "Thiếu hoặc sai chu vi"),
                    () -> Assertions.assertTrue(report.contains("6.00"), "Thiếu hoặc sai diện tích")
            );
        }
    }
}
