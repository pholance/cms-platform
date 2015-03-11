package com.yidumen.cms.framework;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.yidumen.cms.controller.GoodsController;
import com.yidumen.cms.controller.LoginController;
import com.yidumen.cms.controller.VideoController;
import com.yidumen.cms.controller.ajax.GoodsAjaxCtrl;
import com.yidumen.cms.controller.ajax.TagAjaxCtrl;
import com.yidumen.cms.controller.ajax.VideoAjaxCtrl;
import com.yidumen.cms.controller.wechat.MessageController;
import com.yidumen.cms.model.Account;
import com.yidumen.cms.model.Goods;
import com.yidumen.cms.model.Tag;
import com.yidumen.cms.model.Video;

/**
 * 配置JFinal
 * @author 蔡迪旻
 */
public final class JFinalTestConfig extends com.jfinal.config.JFinalConfig {
    @Override
    public void afterJFinalStart() {
        this.loadPropertyFile("appengine-service.properties");
    }

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setEncoding("UTF-8");

        ApiConfigKit.setDevMode(me.getDevMode());
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", LoginController.class);
        me.add("/video", VideoController.class);
        me.add("/goods", GoodsController.class);
        me.add("/ajax/video", VideoAjaxCtrl.class);
        me.add("/ajax/tag", TagAjaxCtrl.class);
        me.add("/ajax/goods", GoodsAjaxCtrl.class);
        
        me.add("/wechat/message", MessageController.class, "/wechat");
    }

    @Override
    public void configPlugin(Plugins me) {
        final C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://localhost:3306/rl8k07vaxq6b1663", "yidumen", "yidumen", "com.mysql.jdbc.Driver");
        me.add(c3p0Plugin);
        final ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
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
    }
    
}