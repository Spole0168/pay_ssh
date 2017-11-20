package com.ibs.core.module.mdmbasedata.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.dao.IDataDictDao;
import com.ibs.core.module.mdmbasedata.dao.ILanguageDao;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class DataDictDaoImpl extends BaseEntityDao<DataDict> implements
		IDataDictDao {

	private ILanguageDao languageDao;

 

	public List<DataDict> list(String type, String language) {

		StringBuffer hql = new StringBuffer();
		if (!StringUtils.isEmpty(language)) {
			hql.append(" from DataDict where type = ? and status='").append(
					Constants.IS_VALID_VALID).append("' and  languageCode='")
					.append(language).append("' order by displayOrder");
		} else {
			hql.append(" from DataDict where type = ? and status='").append(
					Constants.IS_VALID_VALID).append("' order by displayOrder");
		}

		return super.findByValue(hql.toString(), type);
	}

	public DataDict load(String id) {
		return super.load(id);
	}

	public String save(DataDict dataDict) {

		dataDict.setVersionNo(System.currentTimeMillis());

		// dataDict.setLanguageCode(languageCode)(languageDao.load(dataDict.getLocale().getId()));
		return (String) super.save(dataDict);

	}

	public void saveOrUpdate(DataDict dataDict) {
		dataDict.setVersionNo(System.currentTimeMillis());
		// dataDict.setLocale(languageDao.load(dataDict.getLocale().getId()));
		super.saveOrUpdate(dataDict);
	}

	public void delete(String id) {
		DataDict dataDict = super.load(id);
		dataDict.setVersionNo(System.currentTimeMillis());
		dataDict.setStatus(Constants.IS_VALID_NOT_VALID);
		super.saveOrUpdate(dataDict);
	}

	// public List<DataDict> findDataDictsByCodes(Collection<String> cols) {
	// return getItemTypeDict(cols);
	// }

	// @SuppressWarnings("unchecked")
	// private List<DataDict> getItemTypeDict(Collection<String> types) {
	// StringBuffer bf = new StringBuffer();
	// String type = Constants.DICT_BUSINESS_ITEM_TYPE;
	// String hql = "from DataDict dict where dict.type='" + type + "' ";
	// if (types != null && types.size() > 0) {
	// hql += " and dict.code in ( ";
	// }
	// for (Iterator<String> itr = types.iterator(); itr.hasNext();) {
	// String code = itr.next();
	// bf.append("'");
	// bf.append(code);
	// bf.append("'");
	// if (itr.hasNext()) {
	// bf.append(",");
	// }
	// }
	// hql += bf.toString() + " ) ";
	// final String executeHql = hql;
	// return (List<DataDict>) getHibernateTemplate().execute(
	// new HibernateCallback() {
	// public Object doInHibernate(Session session)
	// throws HibernateException {
	// Query query = session.createQuery(executeHql);
	// return query.list();
	// }
	// });
	// }

	/*  
	 *   
	 */
	public IPage<DataDict> findDataDictByPage(QueryPage queryPage) {
		return super.findPageBy(queryPage);
	}

	public ILanguageDao getLanguageDao() {
		return languageDao;
	}

	public void setLanguageDao(ILanguageDao languageDao) {
		this.languageDao = languageDao;
	}

	public String getCodeName(String type, String code, String language) {

		StringBuffer hql = new StringBuffer();

		hql.append(" from DataDict where type = ? and status='").append(
				Constants.IS_VALID_VALID).append("' and  languageCode='").append(
				language).append("'  and code= ?");
		List<Object> args = new ArrayList<Object>(0);
		args.add(type);
		if(code!=null)
		{
			args.add(code.toUpperCase().trim());
		}else
		{
			return null;
		}
		
		List<DataDict> alist = super
				.findByValue(hql.toString(), args.toArray());
		if (alist == null || alist.size() < 1)
			return null;

		DataDict o = alist.get(0);
		return o.getName();

	}
	
	public List<DataDict> getDataListByPage(QueryPage page){
		return this.findAllBy(page);
	}
}
