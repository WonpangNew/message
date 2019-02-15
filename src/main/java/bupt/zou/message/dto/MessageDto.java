package bupt.zou.message.dto;
import java.io.Serializable;

import bupt.zou.message.enums.MessageResources;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *
 * @Description 消息实体
 *
 * @Author Zou
 * @Date 2019-01-08
 */
@ToString(exclude = "serialVersionUID")
@Accessors(chain = true)
public class MessageDto implements Serializable {

    private static final long serialVersionUID = -5790710187206916558L;

    @Getter
    @Setter
    private MessageResources messageResources;

    @Getter
    @Setter
    private String messageEntity;

}
