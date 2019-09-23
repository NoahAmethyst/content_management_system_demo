package amethyst.shiro.session;

import lombok.Data;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class ShiroSession extends SimpleSession {

    /** 属性是否改变 优化session数据同步 */
    private transient boolean attributeChanged = false;

    public ShiroSession() {
        super();
        setAttributeChanged(true);
    }

    public ShiroSession(String host) {
        super(host);
        setAttributeChanged(true);
    }

    @Override
    public void setId(Serializable id) {
        super.setId(id);
        setAttributeChanged(true);
    }

    @Override
    public void setStartTimestamp(Date startTimestamp) {
        super.setStartTimestamp(startTimestamp);
        setAttributeChanged(true);
    }

    @Override
    public void setStopTimestamp(Date stopTimestamp) {
        super.setStopTimestamp(stopTimestamp);
        setAttributeChanged(true);
    }

    @Override
    public void setLastAccessTime(Date lastAccessTime) {
        super.setLastAccessTime(lastAccessTime);
        setAttributeChanged(false);
    }

    @Override
    public void setExpired(boolean expired) {
        super.setExpired(expired);
        setAttributeChanged(true);
    }

    @Override
    public void setTimeout(long timeout) {
        super.setTimeout(timeout);
        setAttributeChanged(true);
    }

    @Override
    public void setHost(String host) {
        super.setHost(host);
        setAttributeChanged(true);
    }

    @Override
    public void setAttributes(Map<Object, Object> attributes) {
        super.setAttributes(attributes);
        setAttributeChanged(true);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
        setAttributeChanged(true);
    }

}
