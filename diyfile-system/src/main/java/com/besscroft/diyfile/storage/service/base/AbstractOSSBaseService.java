package com.besscroft.diyfile.storage.service.base;

import com.besscroft.diyfile.common.param.storage.init.OssParam;

/**
 * @Description OSS 基础服务抽象类
 * @Author Bess Croft
 * @Date 2023/2/17 12:45
 */
public abstract class AbstractOSSBaseService<T extends OssParam> extends AbstractFileBaseService<T> {

    /**
     * 获取对象访问地址
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @return 对象访问地址
     */
    public abstract String getObjectUrl(String bucketName, String objectName);

}
