package com.besscroft.diyfile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.diyfile.common.entity.Storage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 存储映射接口
 * @Author Bess Croft
 * @Date 2022/12/18 21:09
 */
public interface StorageMapper extends BaseMapper<Storage> {

    /**
     * 列表查询
     * @param type 存储类型
     * @return 存储列表
     */
    List<Storage> selectPage(@Param("type") Integer type);

    /**
     * 查询默认存储的 id
     * @return 存储 id
     */
    Long selectIdByDefault();

    /**
     * 查询默认存储
     * @return 存储
     */
    Storage selectByDefault();

    /**
     * 根据存储 key 查询存储
     * @param storageKey 存储 key
     * @return 存储
     */
    Storage selectByStorageKey(@Param("storageKey") String storageKey);

    /**
     * 根据存储 key 查询存储 id
     * @param storageKey 存储 key
     * @return 存储 id
     */
    Long selectIdByStorageKey(@Param("storageKey") String storageKey);

    /**
     * 设置全部存储为非默认
     * @return 更新条数
     */
    int updateDefaultByNo();

    /**
     * 查询全部启用的存储
     * @return 存储列表
     */
    List<Storage> selectAllByEnable();

}
