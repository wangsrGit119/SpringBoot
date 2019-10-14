package cn.wangsr.socket;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/27 0027 14:43
 */
@Component
@ServerEndpoint("/sys/webSocket/{userInfo}")
public class WebSocket {

    private Logger logger= LoggerFactory.getLogger(WebSocket.class);
    private Session session;
    private String userInfo;
    private static CopyOnWriteArraySet<WebSocket> socketSet=new CopyOnWriteArraySet<>();
    private static Map userMap=new ConcurrentHashMap();

    @OnOpen
    public void onOnen(Session session,@PathParam("userInfo") String userInfo){
        this.session=session;
        this.userInfo=userInfo;
        logger.info("新加入id信息：" +JSON.toJSONString(this.session.getId()));
        socketSet.add(this);
        userMap.put(userInfo,session.getId());
        logger.info("【websocket消息】 有新的连接，总数：{}",socketSet.size());
    }

    @OnClose
    public void onClose(){
        socketSet.remove(this);
        userMap.remove(userInfo);
        logger.info("【websocket消息】 连接断开，总数：{}",socketSet.size());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        logger.info("【websocket消息】 收到客户端发来的消息：{}  客户端ID   {}",message,session.getId());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务端发生了错误"+error.getMessage());
    }

    /**
     * @Description   点对点 针对当前登录用户
     * @author wjl
     * @date 2019/9/27 0027
     * @param [message]
     * @return void
     */
    public void sendMessageOneToOne(String message,String userInfo){
        Object currentId=userMap.get(userInfo);
            try {
                logger.info("【websocket消息】 点对点消息，message={},接收消息用户  {}",message,userInfo);
                for(WebSocket webSocket:socketSet){
                    try {
                        if(webSocket.session.getId().equals(currentId))
                        webSocket.session.getBasicRemote().sendText(message);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

    }

    /**
     * @Description   广播  针对所有登录用户
     * @author wjl
     * @date 2019/9/27 0027
     * @param [message]
     * @return void
     */
    public void sendMessage(String message){
        for(WebSocket webSocket:socketSet){
            logger.info("【websocket消息】 广播消息，message={}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
