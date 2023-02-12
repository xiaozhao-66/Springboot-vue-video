package com.xz.video.alipay;

/**
 * <p>
 *     支付宝常量类
 * </p>
 *
 * @author xiaozhao
 */
public class AliPayConstant {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static final String APP_ID = "2016101900721668";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static final String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCoaggAcz6tHwIS5cxtNb6vUl6SsqFzE/ESAMIaQRavHOKPVBqHFyec5W9EO5nFGw7rBUXrM84PxMECsi+cK969Ux+zDJqncVDGgk3Saf2BwEwSB4RABibmNDFe9JuTsfpynUDNMt7HT8tMKMWWdhbt8sr1cHbmgMZbT6uvrmRMZ1M0ykF+pEdADKT3yiRFDsnWEy/HfZUymFmUkjVsqgMiVDhDx16iejLlJZTgeVC/8h+wjwnNU0+50XmyedIKKzFu9gf/kKdqS0409mDCSvWxJjAWfkJp3yhea7Oo9o9K+QoZUz3GYgswkp01/hxEvQr3BN5gWyzwpf/VQGWKboxJAgMBAAECggEAeJicrD3aBrvqjz+wmA9hQUgZYrC5PZ9tA7g5RfWP2cs0TPArosqo2gfNGi4XmXYbw51eCONqzg3VLCoOP6WGdUndrCf9YqOY9Nz//wlYt53Mmyh4wiPlnPiI1fcQpL1QmtwSOB4SOuTfreSQQILFPpSaUxC6g6p434/4X+/f8HjtfqM3pa3jb+VlVjpUZU5rdr531FYSID2QBJiT8yex0K8JvABFMUSeEdKWCWIfukLqxWknv9FEYHMGss1NjalPfDCvVI83TEIIWJ1+nb4/Z4A3FQxLa39eizUj2dSUrl+uS9tESQA2ozfhIE9QsNRdM0kEC0G0m18aAQXMBKQDxQKBgQDoAbJZ6RHKar0FCOXnal+5lDMCEA244FCRzDXIGcqNPXSMXPcOBHztt+7BDnXDPDPnSGADiRcCFb+QQltsT6a/WnxSAXm/erU6ni7wtrGNUz9ViStqSOeBE8hYJle2Pyn2KEN6bh+UMaxo7pqv//SqaVTT4un8a4qxngEzZxbO8wKBgQC51L6SOWaZ41TYDSLbt9lRz0xX94uUprnroeOHfjh8scWIuMdOvNgZATMCeNxzTQ7g5panNmP7dOirv2yavxQageGHT/YZoPjCsUk3rU85QiSiaaanL+0hZlGaMcIJGvjSXRLtHNRg4k/cdmL8jGmlBY5EWEMLx58lhT0gz3ie0wKBgQDhl4zBqkRXS3SiaaWLYL7Vp4n1roB5TPxq2GNPP+zZa3Fhbp/3rFuBlsD8TcAqZ+acNJ7PsUSDDXyonC1jYwvjsuuBndexXXLNHbTM8QgyQEksNeXWQi+BLig3tojBOCfoJ/x9Fol0Qty/Z4tYKYzRCvylz1Xv0O4aiWXngo0GCwKBgCVNGe+6C+lJ44b2pA+ciMfOJ5Hkw6TUDq/IsCZLZMVK3c7C4GbiqzHhtr6BSibuydTFFGp+FXWtEuMeqospXbywY3fIyip80FIsSfkeFa/1oerj8CrRCPVSV4DvCfbP7jAztI/Z4JuN8d/b96Sf/ri8M8FuQg8EQ+LBKvMCEkE3AoGAKpjC3cLczxPbG9uXQQ9XVeh4lh4rB3SDGA8wDf6qjFwVeyZ5kkZcMOONJtasAy79YYTiq+FSv+NI0SWFePCGaxZFVV3Ii8GGowfFiNGWaNfvCSBP6o7y/6mc68x1Ea3/J9hlaywMPWSYFx3oyJP3VfZ5Oi/rqPomB82c+ximqDU=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkBb5tqgbUket6Q+P0VB2fZ3gVbd3ABK9FZDFa9lQXJI/VYkQ1SMxIg/V4BvfWV0+SyNbHqXXFDU//ZqHRTfgkkJ+tPT/w3WY9cDRCUaFi0PKuHWlCHKT5vGZW0xQbNDzh45wVWz6QQRTMC/z5y6bO6wS83uptEn/5rQ8GRDOtFOwKheg5kJeLKJIkQVqx2VygDcZZqNkStN5MVRUqKxT5FnpsUSLigdUUE7JwsoePQrnaL18mfRZ/QYZKi39314SPUwRnnmgzA5CjtrxxiFzQO+iwxF3vkIaggEIokZD1/03PsxHVMouW6FZf1PLaSgLWFT/isHolTHTDygfmM0zswIDAQAB";
    // 签名方式
    public static final String SIGN_TYPE = "RSA2";
    // 字符编码格式
    public static final String CHARSET = "utf-8";
    // 支付宝网关
    public static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    //http://../../payed/notify
    public static final String NOTIFY_URL = "http://43.248.97.192:9956/api/video/zhifubao/payed/notify";

    // 支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    public static final String RETURN_URL = "http://localhost:7001";

}
