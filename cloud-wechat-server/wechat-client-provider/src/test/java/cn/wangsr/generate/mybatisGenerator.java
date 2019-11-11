package cn.wangsr.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wjl
 * @description:
 * @time: 2019/10/31 0031 15:45
 */
public class mybatisGenerator {

    private static final String projectPath = System.getProperty("user.dir");
    private static final String module = "wechat-client-provider";
    static String xmlPath="";

    /**
     * @Description    策略配置
     * @author wjl
     * @date 2019/11/1 0001
     * @param tableNames
     * @return com.baomidou.mybatisplus.generator.config.StrategyConfig
     */
    private static StrategyConfig getStrategyConfig(String[] tableNames) {
        StrategyConfig strategyConfig = new StrategyConfig();
        // 自动填充字段
        List<TableFill> tableFills = new ArrayList<>();
        TableFill tableFill1 = new TableFill("delete_status", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill3 = new TableFill("modify_time", FieldFill.UPDATE);
        tableFills.add(tableFill1);
        tableFills.add(tableFill2);
        tableFills.add(tableFill3);
        strategyConfig
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //这里结合了Lombok，所以设置为true，如果没有集成Lombok，可以设置为false
                .setEntityLombokModel(true)
                .setEntityTableFieldAnnotationEnable(true)
                .setTableFillList(tableFills)
                .setControllerMappingHyphenStyle(true)
                .setRestControllerStyle(true)
                .setLogicDeleteFieldName("delete_status")
                .setInclude(tableNames);
        return strategyConfig;
    }

    /**
     * @Description  包名配置
     * @author wjl
     * @date 2019/11/1 0001
     * @return com.baomidou.mybatisplus.generator.config.PackageConfig
     */
    public static PackageConfig getPackageConfig(){
        PackageConfig packageConfig = new PackageConfig()
                .setParent("cn.wangsr")
                .setController("controller")
                .setEntity("model.po")
                .setMapper("dao.mapper")
                .setServiceImpl("service.impl")
                .setService("service");


        return packageConfig;
    }

    /**
     * @Description   全局配置
     * @author wjl
     * @date 2019/11/1 0001
     * @param
     * @return com.baomidou.mybatisplus.generator.config.GlobalConfig
     */
    public static GlobalConfig getGlobalConfig(){
        GlobalConfig config = new GlobalConfig();
        //设置作者，输出路径，是否重写等属性
        config .setOpen(false)  //生成后是否打开文件夹
                .setActiveRecord(true)
                .setEnableCache(false)
                .setBaseResultMap(true)  //resultMap
                .setBaseColumnList(true)
                .setAuthor("wjl")
                .setOutputDir(projectPath +"/"+module+ "/src/main/java")
                .setFileOverride(true)
                .setServiceName("I%sService");
        return config;
    }

    /**
     * @Description  数据源配置
     * @author wjl
     * @date 2019/11/1 0001
     * @param dbUrl
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     */
    public static DataSourceConfig getDataSourceConfig(String dbUrl){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        return  dataSourceConfig;
    }


    /**
     * @Description   自定义配置
     * @author wjl
     * @date 2019/11/1 0001
     * @param
     * @return com.baomidou.mybatisplus.generator.InjectionConfig
     */
     public static InjectionConfig getInjectionConfig(){
         InjectionConfig cfg = new InjectionConfig() {
             @Override
             public void initMap() {
             }
         };
         // 如果模板引擎是 freemarker
         String templatePath = "/templates/mapper.xml.ftl";
         // 如果模板引擎是 velocity
         // String templatePath = "/templates/mapper.xml.vm";

         // 自定义输出配置
         List<FileOutConfig> focList = new ArrayList<>();
         // 自定义配置会被优先输出
         focList.add(new FileOutConfig(templatePath) {
             @Override
             public String outputFile(TableInfo tableInfo) {
                 xmlPath=projectPath +"/"+module+ "/src/main/resources/mapper/"
                         + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                 // 自定义输出文件名
                 return xmlPath;
             }
         });
         cfg.setFileOutConfigList(focList);
        return cfg;
     }


    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/wechat-app-server?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
        String[] tableNames={"user_information"};

        //关闭默认 xml 生成  调整使用自定义配置  getInjectionConfig()
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        AutoGenerator mpg = new AutoGenerator();
                //  获取全局配置
                mpg.setGlobalConfig(getGlobalConfig())
                //获取数据源配置
                .setDataSource(getDataSourceConfig(dbUrl))
                //模板引擎配置
                .setTemplateEngine(new FreemarkerTemplateEngine())
                //获取决策配置
                .setStrategy(getStrategyConfig(tableNames))
                //自定义配置
                .setCfg(getInjectionConfig())
                //关闭默认 xml 生成
                .setTemplate(tc)
                //获取包名的设置
                .setPackageInfo(getPackageConfig())
                .execute();
    }
}
