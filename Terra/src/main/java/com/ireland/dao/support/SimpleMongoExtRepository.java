package com.ireland.dao.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.Assert;

import static org.springframework.data.mongodb.core.query.Criteria.where;
//import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;



/**
 * 对SimpleMongoRepository增加额外常用的操作,如insert
 * 
 * @version 2013-02-26 
 * 
 * @author KEN
 *
 * @param <T>
 * @param <ID>
 */

public class SimpleMongoExtRepository<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> 
																		implements MongoExtRepository<T, ID>
{

	private final MongoOperations mongoOperations;


	private final MongoEntityInformation<T, ID> entityInformation;

	
	public SimpleMongoExtRepository(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations)
	{
		super(metadata, mongoOperations);

		this.entityInformation = metadata;
		this.mongoOperations = mongoOperations;
	}

	
	private Query getIdQuery(Object id) {
		return new Query(getIdCriteria(id));
	}

	private Criteria getIdCriteria(Object id) {
		return where(entityInformation.getIdAttribute()).is(id);
	}
	
	private List<T> findAll(Query query) {

		if (query == null) {
			return Collections.emptyList();
		}

		return mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
	}

	//---------------------
	
	
	@Override
	public <S extends T> S insert(S entity)
	{
		Assert.notNull(entity, "Entity must not be null!");
		
		mongoOperations.insert(entity, entityInformation.getCollectionName());
		
		return entity;
	}
	

	@Override
	public <S extends T> Collection<S> insert(Collection<S> batchToInsert)
	{
		Assert.notNull(batchToInsert, "The given Collection<S> batchToInsert not be null!");
		
		
		mongoOperations.insert(batchToInsert, entityInformation.getCollectionName());

		
		return batchToInsert;
	}



	@Override
	public T findOne(String key, Object value)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");
		
		return mongoOperations.findOne(	query(where(key).is(value)), entityInformation.getJavaType(), entityInformation.getCollectionName());
	}



	@Override
	public List<T> findAll(String key, Object value)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");
		
		return findAll( query(where(key).is(value)) );
	}

	@Override
	public Page<T> findAll(Criteria criteria, Pageable pageable)
	{
		Long count = count(criteria);
		
		List<T> list = findAll(query(criteria).with(pageable));

		return new PageImpl<T>(list, pageable, count);
	}
	
	@Override
	public List<T> findAllNotEqual(String key, Object value)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");
		
		return findAll( query(where(key).is(value).not()) );
	}
	
	
	
	@Override
	public long count(String key, Object value)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");

		return mongoOperations.count(query(where(key).is(value)), entityInformation.getJavaType());
	}
	
	@Override
	public long count(Criteria criteria)
	{
		return mongoOperations.count(query(criteria), entityInformation.getJavaType());
	}

	
	

	// 这个本质是通过统计指定id的文档的数量来判断文档是否存在,而父类需要将整个文档查询出来,比父类的做法效率高!
	// SimpleMongoRepository.exists():    14s / 14000次半随机查询 ,  
	// SimpleMongoExtRepository.exists(): 10s / 14000次半随机查询 
	@Override
	public boolean exists(ID id)
	{
		Assert.notNull(id, "The given id must not be null!");

		return count(entityInformation.getIdAttribute(),id) > 0;
	}

	
	//这个本质是通过统计指定id的文档的数量来判断文档是否存在,而父类需要将整个文档查询出来,比父类的做法效率高!
	 
	@Override
	public boolean exists(String key, Object value)
	{
		return count(key,value) > 0;
	}





	@Override
	public void delete(String key, Object value)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");
		
		mongoOperations.remove( query(where(key).is(value)), entityInformation.getJavaType());
	}
	
	
	
	
	
	@Override
	public T findAndUpdate(ID id, Update update)
	{
		Assert.notNull(id, "The given id must not be null!");
		Assert.notNull(update, "The given update must not be null!");
		
		return findAndUpdate( getIdQuery(id), update);
	}

	
	@Override
	public T findAndUpdate(String key, Object value, Update update)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");
		Assert.notNull(update, "The given update must not be null!");

		return findAndUpdate( query(where(key).is(value)), update);
	}

	
	 /**
	  * 根据查询条件查找第一个实体,并执行更新操作
	  * 
	  * Finds the first document in the query and updates it. the old document is returned!
	  *  
	  * @param query
	  * @param update
	  * @return
	  * 		返回更新前的实体
	  */		
	
	private T findAndUpdate(Query query, Update update)
	{
		return mongoOperations.findAndModify(query, update, entityInformation.getJavaType(), entityInformation.getCollectionName());
	}


	@Override
	public void update(ID id, Update update)
	{
		Assert.notNull(id, "The given id must not be null!");
		Assert.notNull(update, "The given update must not be null!");
		
		mongoOperations.updateFirst( getIdQuery(id), update, entityInformation.getJavaType());
	}


	@Override
	public void update(String key, Object value, Update update)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");
		Assert.notNull(update, "The given update must not be null!");
		
		mongoOperations.updateFirst( query(where(key).is(value)), update, entityInformation.getJavaType());
	}


	@Override
	public void updateMulti(String key, Object value, Update update)
	{
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(value, "The given value must not be null!");
		Assert.notNull(update, "The given update must not be null!");
		
		mongoOperations.updateMulti( query(where(key).is(value)), update, entityInformation.getJavaType());
	}


	@Override
	public void inc(ID id, String key, Number inc)
	{
		Assert.notNull(id, "The given id must not be null!");
		Assert.hasText(key, "The given key must not be empty!");
		Assert.notNull(inc, "The given inc must not be null!");
		
		update(id, new Update().inc(key, inc));
	}

}
