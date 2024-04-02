//package com.mju.shop.customer.controller;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.concurrent.TimeUnit;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//@FixMethodOrder(MethodSorters.JVM)
//public class CustomerControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    private static WebDriver driver;
//    private static String baseUrl;
//    private static JavascriptExecutor js;
//    private static WebDriverWait wait;
//
//    @BeforeClass
//    public static void setUp() {
//        System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver"); // 다운받은 ChromeDriver 위치를 넣어줍니다.
//        baseUrl = "http://localhost:8090";
//        driver = new ChromeDriver(); // Driver 생성
//        driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver, 20);
//        js = (JavascriptExecutor) driver;
//    }
//
//    // @AfterClass
//    // public static void tearDown() throws Exception {
//    // driver.quit(); // Driver 종료
//
//    // }
//
//    @Test
//    public void test_login() { // 타이틀 확인하는 테스트 코드
//        driver.get(baseUrl + "/login"); // URL로 접속하기
//        js.executeScript("scroll(0,300)");
//        driver.findElement(By.id("inputId")).sendKeys("yeojin");
//        driver.findElement(By.id("inputPassword")).sendKeys("123");
//        driver.findElement(By.xpath("//div[@class='col-12']/button")).click();
//        Assert.assertThat(driver.getTitle(), is("Login Page")); // Title 확인 작업
//    }
//
//    // @Test
//    // public void test_Mypage() { // 타이틀 확인하는 테스트 코드
//    // driver.get(baseUrl + "/mypage"); // URL로 접속하기`
//    // js.executeScript("scroll(0,300)");
//    // }
//
//    @Test
//    public void getCustomerList() throws Exception {
//        mockMvc.perform(get("/customer").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
////    @Test
////    public void exceptionTest() {
////        final CustomerController customerController = new CustomerController();
////        Throwable exception = assertThrows(NullPointerException.class, () -> {
////            customerController.login(null);
////        });
////        assertEquals(null, exception.getMessage());
////    }
//
//    @Test
//    public void exceptionNotThrow() {
//        assertDoesNotThrow(() -> System.out.println("Do Something"));
//    }
//
//    @Test
//    public void getOneCustomer() throws Exception {
//        mockMvc.perform(get("/customer/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void login() throws Exception {
//        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
//                .content("{\"id\":\"yeojin\",\"password\":\"1334\"}")).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void createCustomer() throws Exception {
//        mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(
//                "{\"id\":\"testhk\",\"password\":\"1234\",\"flag\":\"0\",\"phone\":\"01090195201\",\"level\":\"new\",\"name\":\"test\",\"email\":\"ask03421@naver.com\"}"))
//                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void findId() throws Exception {
//        mockMvc.perform(post("/find_id").contentType(MediaType.APPLICATION_JSON).content(
//                "{\"id\":\"yeojin\",\"password\":\"1234\",\"flag\":\"0\",\"phone\":\"01029384938\",\"level\":\"new\",\"name\":\"임여진\",\"email\":\"yeojin@mju.com\"}"))
//                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void findPassword() throws Exception {
//        mockMvc.perform(post("/find_pw").contentType(MediaType.APPLICATION_JSON).content(
//                "{\"id\":\"yeojin\",\"password\":\"1234\",\"flag\":\"0\",\"phone\":\"01029384938\",\"level\":\"new\",\"name\":\"임여진\",\"email\":\"yeojin@mju.com\"}"))
//                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void couponLookup() throws Exception {
//        mockMvc.perform(
//                get("/mypage/coupon/5").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void createWishlist() throws Exception {
//        mockMvc.perform(post("/wishlist_add").contentType(MediaType.APPLICATION_JSON)
//                .content("{\"customerno\":\"5\",\"productid\":\"5\"}")).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void findAllWish() throws Exception {
//        mockMvc.perform(get("/wishlist/5").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//}
