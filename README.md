# mybatis-generator
##主要解决了之前mybatis-generator中的几个小问题
##1.生成的model没有把注释加进去，通过配置JDBCConnectionConfiguration，加入remarksReporting属性
        if (!config.getProperties().containsKey("remarksReporting")) {
            config.getProperties().put("remarksReporting", "true");
        }
##2.生成的xml文件每次不会覆盖，只能追加，改写getGeneratedXmlFiles方法，加入mergeable属性，默认不追加；
public List<GeneratedXmlFile> getGeneratedXmlFiles() {
    List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
    if (xmlMapperGenerator != null) {
        Document document = xmlMapperGenerator.getDocument();
        String tmp = context.getProperty("mergeable");
        boolean mergeable = false;
        if("true".equalsIgnoreCase(tmp)){
            mergeable = true;
        }
        GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(),
                context.getSqlMapGeneratorConfiguration().getTargetProject(),
                mergeable, context.getXmlFormatter());
        if (context.getPlugins().sqlMapGenerated(gxf, this)) {
            answer.add(gxf);
        }
    }
    return answer;
}
