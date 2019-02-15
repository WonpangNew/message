package bupt.zou.message.client.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import bupt.zou.message.dto.MessageDto;
import com.google.gson.Gson;

/**
 *
 * @Description 客户端工具类
 *
 * @Author zoujian
 * @Date 2019-01-15
 */
public class ClientUtil {

    public static void sendMessageUtil(String ipAddress, int port, MessageDto dto) {
        Socket socket = null;
        try {
            socket = new Socket(ipAddress, port);
            OutputStream os = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            dto.setMessageResources(dto.getMessageResources()).setMessageEntity(dto.getMessageEntity());
            Gson gson = new Gson();
            String msg = gson.toJson(dto);
            writer.write(msg);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}