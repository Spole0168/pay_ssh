package com.ibs.common.module.frameworkimpl.data.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.ibs.common.module.frameworkimpl.data.dao.IImportDataDao;
import com.ibs.common.module.frameworkimpl.data.domain.ImportData;
import com.ibs.portal.framework.server.dao.jdbc.BaseTransformJdbcDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.PageFooterColumn;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;
import com.ibs.portal.framework.util.ZipUtils;

public class ImportDataJdbcDaoImpl extends BaseTransformJdbcDao implements IImportDataDao {
	
	private static String PARTITION_LIMIT = " and o.IMPORT_TIME >= sysdate - 1";
	private Map<String, String> colMap = new HashMap<String, String>();
	
	public ImportDataJdbcDaoImpl() {
		colMap.put("id", "ID");
		colMap.put("importId", "IMPORT_ID");
		colMap.put("rowNo", "ROW_NO");
		colMap.put("isSuccess", "IS_SUCCESS");
		colMap.put("errorMessage", "ERROR_MESSAGE");
		colMap.put("excelRowId", "EXCEL_ROW_ID");
		colMap.put("excelRowCode", "EXCEL_ROW_CODE");
		colMap.put("excelData", "EXCEL_DATA");
	}

	public void batchSaveImportData(final List<ImportData> datas) {
		logger.trace("entering dao...");
		
		String sql = "INSERT /*+append*/ INTO pbl.T_PBL_IMPORT_DATA (ID, IMPORT_ID, IMPORT_TIME, ROW_NO, IS_SUCCESS, ERROR_MESSAGE, EXCEL_ROW_ID, EXCEL_ROW_CODE, EXCEL_DATA,SUM1,SUM2,SUM3,SUM4,SUM5) values (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
		
		this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter(){

			public int getBatchSize() {
				return datas.size();
			}

			public void setValues(PreparedStatement ps, int index)
					throws SQLException {
				
				ImportData importData = datas.get(index);
				
				int i = 0;
				
				ps.setString(++i, StringUtils.getUUID());
				ps.setString(++i, importData.getImportId());
				ps.setTimestamp(++i, new Timestamp(importData.getImportTime().getTime()));
				ps.setLong(++i, importData.getRowNo());
				ps.setBoolean(++i, importData.getIsSuccess());
				ps.setString(++i, importData.getErrorMessage());
				ps.setString(++i, importData.getExcelRowId());
				ps.setString(++i, importData.getExcelRowCode());
				ps.setString(++i, ZipUtils.compress(importData.getExcelData()));
				if(importData.getSum1() == null)
					ps.setDouble(++i, 0);
				else 
					ps.setDouble(++i, importData.getSum1());
				if(importData.getSum2() == null)
					ps.setDouble(++i, 0);
				else 
					ps.setDouble(++i, importData.getSum2());
				if(importData.getSum3() == null)
					ps.setDouble(++i, 0);
				else 
					ps.setDouble(++i, importData.getSum3());
				if(importData.getSum4() == null)
					ps.setDouble(++i, 0);
				else 
					ps.setDouble(++i, importData.getSum4());
				if(importData.getSum5() == null)
					ps.setDouble(++i, 0);
				else 
					ps.setDouble(++i, importData.getSum5());
//				ps.setNull(6, Types.CLOB);
//				ps.setNull(6, Types.VARCHAR);
			}
			
		});
        
	}

	@SuppressWarnings("unchecked")
	public IPage<ImportData> findImportDataByPage(QueryPage queryPage,
			Map<String, String> searchFields) {
		
		logger.trace("entering dao...");
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, IMPORT_ID, ROW_NO, IS_SUCCESS, ERROR_MESSAGE, EXCEL_ROW_ID, EXCEL_ROW_CODE, EXCEL_DATA,SUM1,SUM2,SUM3,SUM4,SUM5 ");
		sql.append(" FROM pbl.T_PBL_IMPORT_DATA o WHERE o.IMPORT_ID = ?").append(PARTITION_LIMIT);
		
		queryPage.addQueryCondition("importId", searchFields.get("importId"));
		if (StringUtils.isNotEmpty(searchFields.get("isSuccess"))) {
			sql.append(" and o.IS_SUCCESS = ?");
			queryPage.addQueryCondition("isSuccess", searchFields.get("isSuccess"));
		}
		
		queryPage.setSqlString(sql.toString());
		queryPage.setRowMapper(new ImportDataRowMapper());
//		queryPage.addPageFooterColumn(new PageFooterColumn("rowNo", "COUNT(*)"));
		queryPage.addPageFooterColumn(new PageFooterColumn("errorMessage", "'校验失败记录数：'||sum(case when IS_SUCCESS = 0 then 1 else 0 end)"));
		
		IPage<ImportData> datas = (IPage<ImportData>) super.queryByPage(queryPage);

		return datas;
	}

	@Override
	protected Map<String, String> getColMap() {
		return colMap;
	}

	@SuppressWarnings("unchecked")
	public List<ImportData> findImportDataList(String importId) {
		logger.trace("entering dao...");
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, IMPORT_ID, ROW_NO, IS_SUCCESS, ERROR_MESSAGE, EXCEL_ROW_ID, EXCEL_ROW_CODE, EXCEL_DATA ");
		sql.append(" FROM pbl.T_PBL_IMPORT_DATA o WHERE o.IS_SUCCESS = 1 and o.IMPORT_ID = ?").append(PARTITION_LIMIT);
		
		List<Object> args = new ArrayList<Object>();
		args.add(importId);
		return this.getJdbcTemplate().query(sql.toString(), args.toArray(), new ImportDataRowMapper());
		
	}
	
	@SuppressWarnings("rawtypes")
	public boolean haveImportErrData(String importId) {
		logger.trace("entering dao...");
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT IMPORT_ID FROM pbl.T_PBL_IMPORT_DATA o WHERE o.IS_SUCCESS = 0 and o.IMPORT_ID = ? and rownum = 1 ").append(PARTITION_LIMIT);
		
		List<Object> args = new ArrayList<Object>();
		args.add(importId);
		List list = this.getJdbcTemplate().queryForList(sql.toString(), args.toArray());
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

}

class ImportDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImportData data = new ImportData();

		data.setId(rs.getString("ID"));
		data.setImportId(rs.getString("IMPORT_ID"));
		data.setRowNo(rs.getLong("ROW_NO"));
		data.setIsSuccess(rs.getBoolean("IS_SUCCESS"));
		data.setErrorMessage(rs.getString("ERROR_MESSAGE"));
		data.setExcelRowId(rs.getString("EXCEL_ROW_ID"));
		data.setExcelRowCode(rs.getString("EXCEL_ROW_CODE"));
//		data.setExcelData(StringUtils.getStringFromClob(rs.getClob("EXCEL_DATA")));
		data.setExcelData(ZipUtils.uncompress(rs.getString("EXCEL_DATA")));

		return data;
	}

}