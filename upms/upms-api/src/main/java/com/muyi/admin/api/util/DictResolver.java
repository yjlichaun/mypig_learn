package com.muyi.admin.api.util;

import cn.hutool.core.lang.Assert;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.muyi.admin.api.entity.SysDictItem;
import com.muyi.admin.api.feign.RemoteDictService;
import com.muyi.common.core.util.SpringContextHolder;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description 字典解析器
 * @date 2023/8/2 9:00
 */
@UtilityClass
public class DictResolver {
    
    /**
     * 根据字典类型获取字典项列表
     * @param type 字典类型
     * @return 子弹列表
     */
    public List<SysDictItem> getDictItemsByType(String type) {
        Assert.isTrue(StringUtils.isNotBlank(type), "参数不合法");
        
        RemoteDictService remoteDictService = SpringContextHolder.getBean(RemoteDictService.class);
        
        return remoteDictService.getDictByType(type).getData();
    }
    
    /**
     * 根据字典类型及字典项字典值获取字典标签
     * @param type 字典类型
     * @param itemValue 字典项字典值
     * @return 字典项标签
     */
    public String getDictItemLabel(String type, String itemValue) {
        Assert.isTrue(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(itemValue), "参数不合法");
        
        SysDictItem sysDictItem = getDictItemByItemValue(type, itemValue);
        
        return ObjectUtils.isEmpty(sysDictItem)? sysDictItem.getLabel(): StringPool.EMPTY;
    }
    
    /**
     * 根据字典类型以及字典值获取字典项
     * @param type 字典类型
     * @param itemValue 字典值
     * @return 字典项
     */
    public SysDictItem getDictItemByItemValue(String type, String itemValue) {
        Assert.isTrue(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(itemValue), "参数不合法");
        
        List<SysDictItem> dictItemList = getDictItemsByType(type);
        
        if(CollectionUtils.isNotEmpty(dictItemList)) {
            return dictItemList.stream().filter(item -> itemValue.equals(item.getValue())).findFirst().orElse(null);
        }
        return null;
    }
    
    /**
     * 根据字典类以及字典标签获取字典值
     * @param type 字典类型
     * @param itemLabel 字典标签
     * @return 字典值
     */
    public String getDictItemValue(String type , String itemLabel) {
        Assert.isTrue(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(itemLabel), "参数不合法");
        
        SysDictItem sysDictItem = getDictItemByItemLabel(type, itemLabel);
        
        return ObjectUtils.isEmpty(sysDictItem)? sysDictItem.getValue(): StringPool.EMPTY;
    }
    /**
     * 根据字典类型以及字典标签获取字典项
     * @param type 字典类型
     * @param itemLabel 字典数据项标签
     * @return 字典数据项
     */
    public SysDictItem getDictItemByItemLabel(String type, String itemLabel) {
        Assert.isTrue(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(type) && com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(itemLabel), "参数不合法");
        
        List<SysDictItem> dictItemList = getDictItemsByType(type);
        
        if (com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isNotEmpty(dictItemList)) {
            return dictItemList.stream().filter(item -> itemLabel.equals(item.getLabel())).findFirst().orElse(null);
        }
        
        return null;
    }
}
