package com.ireland.index;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;


public class SolrTemplate<T>
{
	private static final String URL = "http://localhost:8983/solr";
	
	//1.创建SolrServer对象(HttpSolrServer)
	protected static final SolrServer server = new HttpSolrServer(URL);

	/**
	 * 添加文档,相同id的doc会被覆盖
	 * @param doc
	 * @return
	 */
	public boolean addDocument(SolrInputDocument doc)
	{
		try
		{
			server.add(doc);
			server.commit();
		} 
		catch (SolrServerException | IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 删除特定id的文档
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id)
	{
		try
		{
			server.deleteById(id);
			server.commit();
		} 
		catch (SolrServerException | IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * 查询
	 * @param query
	 * @return
	 */
	public SolrDocumentList query(SolrQuery query)
	{
		QueryResponse resp = null;
		
		try
		{
			resp = server.query(query);
		} catch (SolrServerException e)
		{
			e.printStackTrace();
			return new SolrDocumentList();
		}
		
		return resp.getResults();
	}
	
}
