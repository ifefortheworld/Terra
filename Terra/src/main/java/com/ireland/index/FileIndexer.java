package com.ireland.index;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import com.ireland.model.business.File;


@Component("fileIndexer")
public class FileIndexer extends SolrTemplate<File>
{
	/**
	 * 添加File到索引
	 * @param file
	 */
	public void add(File file)
	{
		//创建文档
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", file.getId());
		doc.addField("name", file.getName(),1.5f);				//name的权重更大
		doc.addField("description", file.getDetail(),1.0f);
		
		//索引文档
		addDocument(doc);
	}
	
	/**
	 * 删除特定id的File的索引
	 * @param id
	 */
	public void delete(String id)
	{
		deleteById(id);
	}
	
	
	/**
	 * 根据query,查找File的id列表
	 * @param q
	 * @return id列表
	 */
	public List<String> query(String q)
	{
		//查询
		SolrQuery query = new SolrQuery(q);
		
		SolrDocumentList docList = query(query);
		
		
		//返回id列表
		List<String> ids = new ArrayList<String>(docList.size());
		
		for(SolrDocument doc : docList)
		{
			ids.add((String) doc.get("id"));
		}
		
		return ids;
	}

}
