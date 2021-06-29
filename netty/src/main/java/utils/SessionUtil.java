package utils;

import attr.Attributes;
import domain.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
  //userId -》 Channel
  private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

  public static void buildSession(Session session, Channel channel) {
    userIdChannelMap.put(session.getUserId(),channel);
    //设置属性
    channel.attr(Attributes.SESSION).set(session);
  }

  public static void unSession(Channel channel) {
    if (hasLogin(channel)) {
      userIdChannelMap.remove(
          getSession(channel)
              .getUserId()
      );
      channel.attr(Attributes.SESSION).set(null);
    }
  }

  public static boolean hasLogin(Channel channel) {
    return channel.hasAttr(Attributes.SESSION);
  }

  public static Session getSession(Channel channel) {
    return channel.attr(Attributes.SESSION).get();
  }

  public static Channel getChannel(String userId) {
    return userIdChannelMap.get(userId);
  }
}
