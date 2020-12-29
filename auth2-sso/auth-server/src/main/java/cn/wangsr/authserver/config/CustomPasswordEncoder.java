package cn.wangsr.authserver.config;

import cn.hutool.crypto.digest.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义加密
 * @author WJL
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return  MD5.create().digestHex(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}
