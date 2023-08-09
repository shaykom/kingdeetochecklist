package com.chenmasoft.kingdeetofeishu.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenmasoft.kingdeetofeishu.dao.dao.InvoiceDao;
import com.chenmasoft.kingdeetofeishu.dao.entity.Invoice;
import com.chenmasoft.kingdeetofeishu.dao.service.InvoiceService;
import org.springframework.stereotype.Service;

/**
 * (Invoice)表服务实现类
 *
 * @author makejava
 * @since 2022-07-21 09:22:02
 */
@Service("invoiceService")
public class InvoiceServiceImpl extends ServiceImpl<InvoiceDao, Invoice> implements InvoiceService {

}

