package cn.wangsr.service.impl;

import cn.wangsr.model.po.UserInformation;
import cn.wangsr.dao.mapper.UserInformationMapper;
import cn.wangsr.service.IUserInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author wjl
 * @since 2019-11-11
 */
@Service
public class UserInformationServiceImpl extends ServiceImpl<UserInformationMapper, UserInformation> implements IUserInformationService {

}
