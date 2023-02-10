package com.besscroft.diyfile.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.diyfile.common.entity.SystemConfig;
import com.besscroft.diyfile.mapper.SystemConfigMapper;
import com.besscroft.diyfile.service.SystemConfigService;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description 系统配置服务实现类
 * @Author Bess Croft
 * @Date 2023/1/7 19:09
 */
@Service
@RequiredArgsConstructor
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {

    private final Cache<String, Object> caffeineCache;

    @Override
    public List<SystemConfig> getConfig() {
        return this.list();
    }

    @Override
    public String getSiteTitle() {
        return this.baseMapper.queryByConfigKey("title").getConfigValue();
    }

    @Override
    public Map<String, String> getSiteConfig() {
        List<SystemConfig> configList = this.baseMapper.queryAllByType(1);
        if (CollectionUtils.isEmpty(configList)) return new HashMap<>();
        return configList.stream().collect(Collectors.toMap(SystemConfig::getConfigKey, SystemConfig::getConfigValue));
    }

    @Override
    public String getBeian() {
        return this.baseMapper.queryByConfigKey("beian").getConfigValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(String configKey, String configValue) {
        // TODO 更新用户获取
        Assert.isTrue(this.baseMapper.updateConfig(configKey, configValue, 1L) > 0, "更新失败！");
    }

    @Override
    public String getBarkId() {
        // 先从缓存中获取 token，如果没有则从数据库获取
        return Optional.ofNullable(caffeineCache.getIfPresent("system:barkId"))
                .orElseGet(this::getBarkIdFromDb).toString();
    }

    private String getBarkIdFromDb() {
        String barkId = this.baseMapper.queryByConfigKey("barkId").getConfigValue();
        if (StrUtil.isNotBlank(barkId)) {
            caffeineCache.put("system:barkId", barkId);
        }
        return barkId;
    }

}