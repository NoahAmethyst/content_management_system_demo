package amethyst.shiro.session;


import lombok.extern.slf4j.Slf4j;
import amethyst.shiro.constant.ShiroConstants;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 关于shiro频繁访问redis,共分为两种,
 *      一种是频繁的去redis读取session ，
 *      一种是频繁的去更新redis 中的session，针对两种不同情况,分别写出解决方案。
 * <p>
 * 1. 关于频繁去Redis中读取Session有一个更好的解决方案，重写DefaultWebSessionManager 的 retrieveSession()方法。
 * 在 Web 下使用 shiro 时这个 sessionKey 是 WebSessionKey 类型的，这个类有个我们很熟悉的属性：servletRequest。小伙伴们应该都灵光一现了！
 * 直接把 session 对象怼进 request 里去！那么在单次请求周期内我们都可以从 request 中取 session 了，
 * 而且请求结束后 request 被销毁，作用域和生命周期的问题都需要我们考虑了。
 * 显然我们要 Override 这个retrieveSession方法，为此我们需要使用自定义的 SessionManager，如下：
 */
@Slf4j
public class ShiroSessionManager extends DefaultWebSessionManager {

    @Autowired
    private RedisTemplate<Serializable,Session> redisTemplate;

    /**
     * 获取session
     * 优化单次请求需要多次访问redis的问题
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        //获取sessionId
        Serializable sessionId = getSessionId(sessionKey);

        //获取请求对象
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        //从请求中获取session对象
        if (request != null && null != sessionId) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {

                return (Session) sessionObj;
            }
        }

        log.debug("read redis from request");
        //当前sessionId没有时，调用 super.retrieveSession(sessionKey)==>创建session
        if(sessionId==null)
            return super.retrieveSession(sessionKey);
        //从redis读取
        Session session = redisTemplate.opsForValue().get(sessionId);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.info(" getActiveSessions......");
        Set<Serializable> sessionIds = redisTemplate.keys(ShiroConstants.SHIRO_SESSION_PREFIX_KYES);
        if (sessionIds == null || sessionIds.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        List<Session> sessions = redisTemplate.opsForValue().multiGet(sessionIds);
        //可以得到一个集合的镜像，它的返回结果不可直接被改变,否则会提示//java.lang.UnsupportedOperationException
        return Collections.unmodifiableCollection(sessions);
        //return  super.getActiveSessions();
    }
}
