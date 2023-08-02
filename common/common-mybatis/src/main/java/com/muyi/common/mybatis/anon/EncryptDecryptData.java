package com.muyi.common.mybatis.anon;

import java.lang.annotation.*;

/**
 * @author 历川
 * @version 1.0
 * @description 加密解密数据
 * @date 2023/8/2 20:26
 */
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptData {

}