package cn.wangsr.model.dto;


import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author wjl
 * @since 2019-11-01
 */
@Data
public class UserInformationDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    private Integer userStatus;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer deleteStatus;

}
