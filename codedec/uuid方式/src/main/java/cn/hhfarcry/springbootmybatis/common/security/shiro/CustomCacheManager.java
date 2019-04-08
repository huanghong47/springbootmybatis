package cn.hhfarcry.springbootmybatis.common.security.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 16:11
 */
public class CustomCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new CustomCache<K,V>();
    }
}
