package com.yidumen.cms.framework;

import com.alibaba.appengine.api.ds.DataSourceFactory;
import com.jfinal.config.*;
import com.jfinal.log.Log4jLoggerFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.yidumen.cms.dao.Account;
import com.yidumen.cms.dao.Goods;
import com.yidumen.cms.dao.Tag;
import com.yidumen.cms.dao.Video;
import com.yidumen.cms.view.controller.GoodsController;
import com.yidumen.cms.view.controller.LoginController;
import com.yidumen.cms.view.controller.VideoController;
import com.yidumen.cms.view.controller.ajax.GoodsAjaxCtrl;
import com.yidumen.cms.view.controller.ajax.TagAjaxCtrl;
import com.yidumen.cms.view.controller.ajax.VideoAjaxCtrl;

/**
 * 配置JFinal
 * @author 蔡迪旻
 */
public final class JFinalConfig extends com.jfinal.config.JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(false);
        me.setEncoding("UTF-8");
        me.setLoggerFactory(new Log4jLoggerFactory());
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", LoginController.class);
        me.add("/video", VideoController.class);
        me.add("/goods", GoodsController.class);
        me.add("/ajax/video", VideoAjaxCtrl.class);
        me.add("/ajax/tag", TagAjaxCtrl.class);
        me.add("/ajax/goods", GoodsAjaxCtrl.class);
    }

    @Override
    public void configPlugin(Plugins me) {
        final ActiveRecordPlugin arp = new ActiveRecordPlugin(DataSourceFactory.getDataSource("ydm"));
        me.add(arp);
        arp.setDialect(new MysqlDialect());
        arp.addMapping("Video", Video.class);
        arp.addMapping("Account", Account.class);
        arp.addMapping("Tag", Tag.class);
        arp.addMapping("Goods", Goods.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {
    }

    @Override
    public void configHandler(Handlers me) {
        me.add(new SecurityHandler());
    }
    
}
