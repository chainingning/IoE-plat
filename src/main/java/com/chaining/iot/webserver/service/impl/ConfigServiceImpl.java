//package com.chaining.iot.webserver.service.impl;
//
//import com.chaining.iot.common.utils.CacheUtils;
//import com.chaining.iot.common.utils.StringUtils;
//import com.chaining.iot.webserver.domain.Config;
//import com.chaining.iot.webserver.mapper.ConfigMapper;
//import com.chaining.iot.webserver.service.IConfigService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.xmlunit.util.Convert;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
///**
// * 参数配置 服务层实现
// *
// * @author ruoyi
// */
//@Service
//public class ConfigServiceImpl implements IConfigService
//{
//    @Autowired
//    private ConfigMapper configMapper;
//
//    /**
//     * 项目启动时，初始化参数到缓存
//     */
//    @PostConstruct
//    public void init()
//    {
//        List<Config> configsList = configMapper.selectConfigList(new Config());
//        for (Config config : configsList)
//        {
//            CacheUtils.put(getCacheName(), getCacheKey(config.getConfigKey()), config.getConfigValue());
//        }
//    }
//
//    /**
//     * 查询参数配置信息
//     *
//     * @param configId 参数配置ID
//     * @return 参数配置信息
//     */
//    @Override
//    public Config selectConfigById(Long configId)
//    {
//        Config config = new Config();
//        config.setConfigId(configId);
//        return configMapper.selectConfig(config);
//    }
//
//    /**
//     * 根据键名查询参数配置信息
//     *
//     * @param configKey 参数名称
//     * @return 参数键值
//     */
//    @Override
//    public String selectConfigByKey(String configKey)
//    {
//        String configValue = Convert.toStr(CacheUtils.get(getCacheName(), getCacheKey(configKey)));
//        if (StringUtils.isNotEmpty(configValue))
//        {
//            return configValue;
//        }
//        Config config = new Config();
//        config.setConfigKey(configKey);
//        Config retConfig = configMapper.selectConfig(config);
//        if (StringUtils.isNotNull(retConfig))
//        {
//            CacheUtils.put(getCacheName(), getCacheKey(configKey), retConfig.getConfigValue());
//            return retConfig.getConfigValue();
//        }
//        return StringUtils.EMPTY;
//    }
//
//    /**
//     * 查询参数配置列表
//     *
//     * @param config 参数配置信息
//     * @return 参数配置集合
//     */
//    @Override
//    public List<Config> selectConfigList(Config config)
//    {
//        return configMapper.selectConfigList(config);
//    }
//
//
//
//
//    /**
//     * 批量删除参数配置对象
//     *
//     * @param ids 需要删除的数据ID
//     * @return 结果
//     */
//    @Override
//    public int deleteConfigByIds(String ids)
//    {
//        int count = configMapper.deleteConfigByIds(Convert.toStrArray(ids));
//        if (count > 0)
//        {
//
//            CacheUtils.removeAll(getCacheName());
//        }
//        return count;
//    }
//
//    /**
//     * 清空缓存数据
//     */
//    @Override
//    public void clearCache()
//    {
//        CacheUtils.removeAll(getCacheName());
//    }
//
//    /**
//     * 校验参数键名是否唯一
//     *
//     * @param config 参数配置信息
//     * @return 结果
//     */
//    @Override
//    public String checkConfigKeyUnique(Config config)
//    {
//        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
//        Config info = configMapper.checkConfigKeyUnique(config.getConfigKey());
//        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue())
//        {
//            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
//        }
//        return UserConstants.CONFIG_KEY_UNIQUE;
//    }
//
//    /**
//     * 获取cache name
//     *
//     * @return 缓存名
//     */
//    private String getCacheName()
//    {
//        return Constants.SYS_CONFIG_CACHE;
//    }
//
//    /**
//     * 设置cache key
//     *
//     * @param configKey 参数键
//     * @return 缓存键key
//     */
//    private String getCacheKey(String configKey)
//    {
//        return Constants.SYS_CONFIG_KEY + configKey;
//    }
//}
