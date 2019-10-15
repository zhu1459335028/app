package com.secusoft.ssw.service.impl.monitor;

import com.secusoft.ssw.common.exception.ServiceException;
import com.secusoft.ssw.mapper.global.MonitorMapper;
import com.secusoft.ssw.mapper.monitor.OutletMapper;
import com.secusoft.ssw.mapper.monitor.UsersMapper;
import com.secusoft.ssw.model.monitor.Outlet;
import com.secusoft.ssw.model.monitor.Users;
import com.secusoft.ssw.model.viewobject.Request.*;
import com.secusoft.ssw.service.LoginService;
import com.secusoft.ssw.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LoginServiceImpl
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 9:38
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    protected MonitorMapper monitorMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private OutletMapper outletMapper;


    @Override
    public Object login1(int monitorId, LoginAppUser loginAppUser) {
        Customer user = usersMapper.selectUserByUserName(loginAppUser.getUsername());
        if (user == null){
            throw new ServiceException("用户名有误");
        }
        if (loginAppUser!= null && !loginAppUser.getUserpswd().equals(user.getUserpswd())){
            throw new ServiceException("密码有误");
        }
        ArrayList<Outlet> list = new ArrayList<>();
        List<Integer> outletIds = outletMapper.selectOutletIdByUserId(user.getId());
        if(outletIds!=null && outletIds.size() > 0){
            for (Integer outletId: outletIds) {
                Outlet outlet = outletMapper.selectOutletByOutletId(outletId);
                outlet.setUserid(user.getId());
                if (user.getLastoutletid()!= null){
                    outlet.setLastoutletid(user.getLastoutletid());
                }
                list.add(outlet);
            }
        }

        return list;

    }

    @Override
    public String resultToken(int monitorId, AppOutlet appOutlet) {
        if(appOutlet.getOutletid() == null || appOutlet.getUserId() == null){
            throw new ServiceException("入参有误");
        }
        String token = JwtUtil.generateToken(monitorId+","+appOutlet.getUserId()+","+appOutlet.getOutletid());
        usersMapper.updateLastOutletId(appOutlet.getOutletid(),appOutlet.getUserId());
        logger.info("[{}]登录成功，返回token[{}]",token);
        return token;

    }

}
