package bupt.zou.message.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import bupt.zou.message.cache.MessageCache;
import bupt.zou.message.cache.util.CacheUtil;
import bupt.zou.message.dto.MessageDto;
import bupt.zou.message.enums.MessageResources;
import bupt.zou.message.util.Utils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @Description 消息中心, 通过线程池进行消息处理
 *
 * @Author zoujian
 * @Date 2019-01-14
 */
@Slf4j
public class MessageCenter {

    private static int SERVER_IP_PORT = Utils.getIntValue("server.ip.port");
    private static int THREAD_MAX_SIZE = Utils.getIntValue("thread.max.number");
    private static int THREAD_CORE_SIZE = Utils.getIntValue("thread.core.number");
    private static int THREAD_KEEPALIVE_TIME = Utils.getIntValue("thread.keepalive.time");
    private static int THREAD_QUEUE_SIZE = Utils.getIntValue("thread.queue.size");

    public static void main(String[] args) {
        //创建一个线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                THREAD_CORE_SIZE,
                THREAD_MAX_SIZE,
                THREAD_KEEPALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(THREAD_QUEUE_SIZE),
                new ThreadPoolExecutor.AbortPolicy());
        ServerSocket serverSocket;
        Socket socket = null;
        try {
            log.info("server started");
            serverSocket = new ServerSocket(SERVER_IP_PORT);
            while (true) {
                socket = serverSocket.accept();
                log.info("client's ip address is : {}", socket.getInetAddress().getLocalHost());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //读取客户端发送来的消息
                String message = br.readLine();
                System.out.println(message);
                MessageDto dto;
                Gson gson = new Gson();
                dto = gson.fromJson(message, MessageDto.class);
                //email添加消息到缓存,其他消息直接消费
                switch (dto.getMessageResources()) {
                    case WECHAT:
                        CacheUtil.addMessage2Cache(dto);
                        break;
                }
                log.info("client message entity is {} ", dto.toString());
                //创建线程,执行消息的发送任务
                MessageTask messageTask = new MessageTask(dto);
                threadPoolExecutor.submit(messageTask);
            }
        } catch (IOException e) {
            log.error("socket accepts error, {}", e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                log.error("socket closes error, {}", e);
            }
        }
    }

    private static class MessageTask implements Runnable {
        private MessageDto messageDto;

        public MessageTask(MessageDto messageDto) {
            this.messageDto = messageDto;
        }

        @Override
        public void run() {
            MessageResources resources = messageDto.getMessageResources();
            switch (resources) {
                case WECHAT:
                    try {
                        //线程去缓存中取数据,如果10s之后缓存中依旧没有数据则线程结束
                        String emailMessage = MessageCache.getCache().poll(10, TimeUnit.SECONDS);
                        if (emailMessage != null) {
                            log.info("wechat message is {} ", emailMessage);
                        }
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        log.error("get cache error, {}", e);
                    }
                    break;
                case QQ:
                    System.out.println(messageDto.getMessageEntity());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("thread sleeps error, {}", e);
                    }
                    break;
                case SHORT_MESSAGE:
                    System.out.println(messageDto.getMessageEntity());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("thread sleeps error, {}", e);
                    }
                    break;
                default:
                    break;
            }
        }
    }

}