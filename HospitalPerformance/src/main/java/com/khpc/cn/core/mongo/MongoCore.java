package com.khpc.cn.core.mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Vinne
 * @date 2020/1/18 10:55
 * @description mongoDB 核心操作工具类
 **/
@Component
public class MongoCore {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static MongoCore mongoCoreUtil;

    @PostConstruct
    public void init() {
        mongoCoreUtil = this;
        mongoCoreUtil.mongoTemplate = this.mongoTemplate;
    }

    /**
     *  查询操作
     * @param criteria  参数组装标准
     * @param pojoClass  查询对象类
     * @param collectionName  查询集合名称
     * @return
     */
    public static List selectList(Criteria criteria,Class pojoClass,String collectionName){
        Query coreQuery= new Query();
        coreQuery.addCriteria(criteria);
        return mongoCoreUtil.mongoTemplate.find(coreQuery,pojoClass,collectionName);
    }

    /**
     * 查询单条数据信息
     * @param criteria
     * @param pojoClass
     * @param collectionName
     * @return
     */
    public static Object selectOne(Criteria criteria,Class pojoClass,String collectionName){
        Query coreQuery= new Query();
        coreQuery.addCriteria(criteria);
        return mongoCoreUtil.mongoTemplate.findOne(coreQuery,pojoClass,collectionName);
    }

    /**
     *  数据插入
     * @param object
     * @param collectionName
     */
    public static void addOne(Object object,String collectionName){
          mongoCoreUtil.mongoTemplate.save(object,collectionName);
    }

    /**
     *  批量数据插入
     * @param object
     * @param collectionName
     */
    public static void addBathData(List object, String collectionName){
        mongoCoreUtil.mongoTemplate.insert(object,collectionName);
    }

    /**
     *  更新对象数据
     * @param query
     * @param update
     * @param collectionName
     */
    public static void updateData(Query query, Update update,String collectionName){
        mongoCoreUtil.mongoTemplate.upsert(query,update,collectionName);
    }
}
