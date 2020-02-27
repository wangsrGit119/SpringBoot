package cn.wangsr.security.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/**
 * @author: wjl
 * @description:
 * @time: 2020/1/10 0010 17:31
 */
public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("123456");
        System.out.println(result);
        System.out.println(encoder.matches("123456", result));


    }
}
