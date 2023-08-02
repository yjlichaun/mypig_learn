package com.muyi.common.satoken.core.dao;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.util.SaFoxUtil;
import com.muyi.common.redis.utils.RedisUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description Sa-token持久层接口(使用框架自带redisUtils实现协议统一)
 * @date 2023/8/2 19:22
 */

public class SaTokenDaoImpl implements SaTokenDao {
    
    /**
     * 获取value ，如无返回
     * @param key 键值对的key
     * @return value
     */
    @Override
    public String get(String key) {
        return RedisUtils.getCacheObject(key);
    }
    
    /**
     * 写入value
     * @param key 键
     * @param value 值
     * @param timeout 存活时间(单位：秒)
     */
    @Override
    public void set(String key , String value, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        //判断是否为永不过期
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            RedisUtils.setCacheObject(key,value);
        }else {
            RedisUtils.setCacheObject(key,value, Duration.ofSeconds(timeout));
        }
    }
    
    /**
     * 修改指定键值对 (过期时间不变)
     * @param key 键
     * @param value 值
     */
    @Override
    public void update(String key, String value) {
        long expires = getTimeout(key);
        //-2 = 无此键
        if (expires == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.set(key, value, expires);
    }
    
    
    /**
     * 删除value
     * @param key 键
     */
    @Override
    public void delete(String key) {
        RedisUtils.deleteObject(key);
    }
    
    /**
     * 获取value剩余存活时间
     */
    @Override
    public long getTimeout(String key) {
        long timeout = RedisUtils.getTimeToLive(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }
    
    /**
     * 更新value剩余存活时间
     * @param key 键
     * @param timeout 值
     */
    @Override
    public void updateTimeout(String key, long timeout) {
        //判断是否设为永久
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expires = getTimeout(key);
            if (expires == SaTokenDao.NEVER_EXPIRE) {
                //已经被设为永久
            }else {
                this.set(key, this.get(key),timeout);
            }
            return;
        }
        RedisUtils.expire(key, Duration.ofSeconds(timeout));
    }
    
    /**
     * 获取Object ，如无返空
     * @param key 键
     * @return Object
     */
    @Override
    public Object getObject(String key) {
        return RedisUtils.getCacheObject(key);
    }
    
    /**
     * 写入Object，设定存活时间（单位：秒）
     * @param key 键
     * @param value 值
     * @param timeout 存活时间(单位：秒)
     */
    @Override
    public void setObject(String key, Object value, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            RedisUtils.setCacheObject(key,value);
        } else {
          RedisUtils.setCacheObject(key,value,Duration.ofSeconds(timeout));
        }
    }
    
    /**
     * 更新Object 存活时间不变
     * @param key 键
     * @param object 对象
     */
    @Override
    public void updateObject(String key, Object object) {
        long expires = getTimeout(key);
        if (expires == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.setObject(key, object, expires);
    }
    
    /**
     * 删除Object
     * @param key 键
     */
    @Override
    public void deleteObject(String key) {
        RedisUtils.deleteObject(key);
    }
    
    /**
     * 获取Object的剩余存活时间 (单位: 秒)
     */
    @Override
    public long getObjectTimeout(String key) {
        long timeout = RedisUtils.getTimeToLive(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }
    
    /**
     * 修改Object的剩余存活时间 (单位: 秒)
     */
    @Override
    public void updateObjectTimeout(String key, long timeout) {
        // 判断是否想要设置为永久
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getObjectTimeout(key);
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // 如果其已经被设置为永久，则不作任何处理
            }
            else {
                // 如果尚未被设置为永久，那么再次set一次
                this.setObject(key, this.getObject(key), timeout);
            }
            return;
        }
        RedisUtils.expire(key, Duration.ofSeconds(timeout));
    }
    
    /**
     * 搜索数据
     */
    @Override
    public List<String> searchData(String prefix, String keyword, int start, int size) {
        Collection<String> keys = RedisUtils.keys(prefix + "*" + keyword + "*");
        List<String> list = new ArrayList<>(keys);
        return SaFoxUtil.searchList(list, start, size);
    }
}
