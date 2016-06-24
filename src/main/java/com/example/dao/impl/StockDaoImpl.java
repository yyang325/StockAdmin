package com.example.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.StockDao;
import com.example.model.Stock;

/**
 * Implement Stock Dao by Hibernate Template
 * @author Yi
 *
 */
@Repository
public class StockDaoImpl implements StockDao {
	private HibernateTemplate template;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}
	
	public void save(Stock stock) {
		template.save(stock);
	}

	public void update(Stock stock) {
		template.update(stock);
	}

	public void delete(Stock stock) {
		template.delete(stock);
	}

	@SuppressWarnings("unchecked")
	public Stock findBySymbol(String symbol) {
		List<Stock> list = this.template.findByNamedParam("From Stock s where s.symbol = :symbol", "symbol", symbol);
		return (list == null || list.size() < 1) ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Stock findByName(String name) {
		List<Stock> list = this.template.findByNamedParam("From Stock s where s.name = :name", "name", name);
		return (list == null || list.size() < 1) ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Stock> queryAll() {
		String hql = "from Stock";
		return template.find(hql);
	}

}
