var ioc = {
    conf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["conf/"]
        }
    },
    dataSource : {
        factory : "$conf#make",
        args : ["com.alibaba.druid.pool.DruidDataSource", "db."],
        type : "com.alibaba.druid.pool.DruidDataSource",
        events : {
            create : "init",
            depose : 'close'
        },
        fields : {
            defaultAutoCommit : false,
            connectionInitSqls : ["set names utf8mb4;"]  // 集合.
        }
    },
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    }
};