package com.muyi.common.mybatis.anon;

import java.lang.annotation.*;

/**
 * @author 历川
 * @version 1.0
 * @description TODO
 * @date 2023/8/2 20:28
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptField {
}
