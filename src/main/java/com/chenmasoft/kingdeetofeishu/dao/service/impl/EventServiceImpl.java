package com.chenmasoft.kingdeetofeishu.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenmasoft.kingdeetofeishu.dao.dao.EventDao;
import com.chenmasoft.kingdeetofeishu.dao.entity.Event;
import com.chenmasoft.kingdeetofeishu.dao.service.EventService;
import org.springframework.stereotype.Service;

/**
 * (Event)表服务实现类
 *
 * @author makejava
 * @since 2022-07-18 19:17:52
 */
@Service("eventService")
public class EventServiceImpl extends ServiceImpl<EventDao, Event> implements EventService {

}

