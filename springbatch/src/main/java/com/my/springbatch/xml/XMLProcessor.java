package com.my.springbatch.xml;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

public class XMLProcessor implements ItemProcessor<Goods, Goods>{

	 /**
     * 对取到的数据进行简单的处理。
     * 
     * @param goods
     *            处理前的数据。
     * @return 处理后的数据。
     * @exception Exception
     *                处理是发生的任何异常。
     */
    @Override
    public Goods process(Goods goods) throws Exception {
    	// 购入日期变更
        goods.setBuyDay(new Date());
        // 顾客信息变更
        goods.setCustomer(goods.getCustomer() + "顾客!");
        // ISIN变更
        goods.setIsin(goods.getIsin() + "IsIn");
        // 价格变更
        goods.setPrice(goods.getPrice() + 1000.112);
        // 数量变更
        goods.setQuantity(goods.getQuantity() + 100);
        // 处理后的数据返回
        return goods;
    }

}
