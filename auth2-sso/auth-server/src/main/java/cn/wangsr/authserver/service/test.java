package cn.wangsr.authserver.service;

import cn.hutool.crypto.digest.MD5;
import org.apache.commons.codec.digest.Md5Crypt;
import org.bouncycastle.crypto.digests.MD5Digest;

public class test {
    public static void main(String[] args) {

        System.out.println(MD5.create().digest("123456"));
    }
}
