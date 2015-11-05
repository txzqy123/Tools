package com.function.util;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库相关操作工具类
 * 
 * @author  xo
 * @version 1.0 2013-10-12
 */
public class JdbcUtils {

    private static final Logger logger  = LoggerFactory.getLogger(JdbcUtils.class);
    
    private static Map<String, String> sqlMap = new HashMap<String, String>();
    
    /**
     * 根据指定的键获得对应的sql语句
     * 
     * @param  id 键
     * @return String sql内容
     */
    public static String getSql(String id){
        if(sqlMap.containsKey(id)){
            return sqlMap.get(id);
        }else{
            SAXReader saxReader = new SAXReader();
            Document doc;
            try {
                doc = saxReader.read(JdbcUtils.class.getResource("/sql.xml"));
            } catch(DocumentException e) {
                logger.error("获取数据库语句出错", e);
                return null;
            }
            if(doc != null){
                Node node = doc.selectSingleNode("/sqls/sql[@id='"+id+"']");
                return node.getText();
            }else{
                return null;
            }
        }
    }
    
    private JdbcUtils(){}
}
