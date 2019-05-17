package com.loong.mnote.test.common.utils;

import com.loong.mnote.common.util.RSAUtils;

import java.util.Map;

/**
 * @author: sam
 * @date: 2019-04-09 22:37
 */
public class RSAMainTest {

    public static final String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3Y0uVrwDksjNdXZ7BPsP2P5SlxuzF0wlDbawwqc/dfaBiLDJZgQv8VQG24F4GLsCpfz0oAEkLyoKMnv5qUK9B9T4QZOejy91I3MRd15fw85BlWgqXFoaryxKwp1jRtCTR5C5TGSOSvwB7RD3D3aGV1le9YcF9r1iXYwNrE6KXIwIDAQAB";

    public static void main(String[] args) {

        Map<String, Object> stringObjectMap = RSAUtils.initKey();

//        DatatypeConvert.parseHexBinary(someString)
        String inputStr = "MerchantAccount=testsh001&MerchantOrderNo=20190409204830&NotifyUrl=http://125.88.183.123:9500/payResultNotify.aspx&PayMoney=1.00&PayType=100&RequestData=test&ReturnUrl=http://125.88.183.123:9500/payResultReturn.aspx&sign=b3c24a0a0446ec244598f3bc907194b4";
//        String inputStr = "a";
        byte[] data = RSAUtils.encryptByPublicKey(inputStr.getBytes(), public_key);
        System.out.println(data);

        Map<String, Object> keyMap = RSAUtils.initKey();

        String publicKey = RSAUtils.getPublicKey(keyMap);
        String privateKey = RSAUtils.getPrivateKey(keyMap);

        byte[] bytes = RSAUtils.encryptByPublicKey(inputStr.getBytes(), publicKey);
        byte[] bytes1 = RSAUtils.decryptByPrivateKey(bytes, privateKey);
        System.out.println(new String(bytes1));

        String sign = RSAUtils.sign(inputStr.getBytes(), privateKey);
        boolean verify = RSAUtils.verify(inputStr.getBytes(), publicKey, sign);
        System.out.println(verify);
    }
}
