package com.utils.WEUtils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("wx")
@Component
public class weixinProperties {
    private String AppID;   // 小程序appid
    private String Secret;  // 小程序密钥
    private String media;   // 商户号
    private String mchSerialNO; // 商户api证书序列号
    private String privateKeyFilePath;  // 商户私钥文件
    private String apiV3Key;    // 证书解密的密钥
    private String weChatPayCertFilePath;   // 平台证书
    private String notifyUrl;   // 支付成功回调地址
    private String refundNotifyUrl; // 退款成功的回调地址


}
