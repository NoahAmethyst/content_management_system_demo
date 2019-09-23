package amethyst.shiro.cache;

import lombok.extern.slf4j.Slf4j;
import amethyst.po.sys.User;
import amethyst.shiro.constant.ShiroConstants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisCache<K,V> implements Cache<K, V> {

    private transient RedisTemplate<K, V> redisTemplate;
    public RedisCache(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //设置cache的名称
    private String createCacheKey(K k){
        if(k instanceof PrincipalCollection) {
            PrincipalCollection principalCollection = (PrincipalCollection) k;
            User user = (User) principalCollection.getPrimaryPrincipal();
            return user.getUserId()+"";
        }
        return "";
    }


    @Override
    public V get(K k) throws CacheException {
        log.info("从cache中get内容,key:{}",createCacheKey(k));
        return redisTemplate.opsForValue().get(ShiroConstants.SHIRO_CACHE_PREFIX+createCacheKey(k));

    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForValue().set((K)(ShiroConstants.SHIRO_CACHE_PREFIX+createCacheKey(k)),v,ShiroConstants.CACHE_TIMEOUT, TimeUnit.SECONDS);
        log.info("从cache中put内容,key:{}",createCacheKey(k));
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = redisTemplate.opsForValue().get(ShiroConstants.SHIRO_CACHE_PREFIX+createCacheKey(k));
        redisTemplate.delete((K)(ShiroConstants.SHIRO_CACHE_PREFIX+createCacheKey(k)));
        log.info("从cache中remove内容,key:{}",createCacheKey(k));
        return v;
    }

    @Override
    public void clear() throws CacheException {
        Set<K> ks = redisTemplate.keys((K)ShiroConstants.SHIRO_CACHE_PREFIX_KYES);
        redisTemplate.delete(ks);
        log.info("从cache中clear内容,key:{}",ks);
    }

    @Override
    public int size() {
        Set<K> ks = redisTemplate.keys((K)ShiroConstants.SHIRO_CACHE_PREFIX_KYES);
        return ks.size();
    }

    @Override
    public Set<K> keys() {
       return redisTemplate.keys((K)ShiroConstants.SHIRO_CACHE_PREFIX_KYES);
    }

    @Override
    public Collection<V> values() {
        Set<K> ks = redisTemplate.keys((K)ShiroConstants.SHIRO_CACHE_PREFIX_KYES);
        return redisTemplate.opsForValue().multiGet(ks);
    }
}
