package amethyst.shiro.dao;

import lombok.extern.slf4j.Slf4j;
import amethyst.shiro.constant.ShiroConstants;
import amethyst.shiro.session.ShiroSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        log.info("redisSessionDao doCreate......");
        Serializable sessionId = ShiroConstants.SHIRO_SESSION_PREFIX+ UUID.randomUUID().toString();
        //分配sessionId
        this.assignSessionId(session,sessionId);
        redisTemplate.opsForValue().set(session.getId(),session, ShiroConstants.SESSION_TIMEOUT, TimeUnit.SECONDS);
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("redisSessionDao doReadSession......");
        //读取时，重置过期时间
        redisTemplate.expire(sessionId, ShiroConstants.SESSION_TIMEOUT, TimeUnit.SECONDS);
        return (Session) redisTemplate.opsForValue().get(sessionId);
    }

    @Override
    protected void doUpdate(Session session) {

        if(session instanceof ShiroSession){
            ShiroSession shiroSession = (ShiroSession) session;
            //属性没有改变，直接返回
            if(!shiroSession.isAttributeChanged()){
                return;
            }
            log.info("redisSessionDao doUpdate......");
            //属性有改变更新redis
            redisTemplate.opsForValue().set(session.getId(),session,ShiroConstants.SESSION_TIMEOUT, TimeUnit.SECONDS);
            //属性重置false
            shiroSession.setAttributeChanged(false);
        }


    }

    @Override
    protected void doDelete(Session session) {
        log.info("redisSessionDao doDelete......");
        redisTemplate.opsForValue().getOperations().delete(session.getId());
    }


}
