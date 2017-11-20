package com.ibs.common.module.frameworkimpl.mail.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ibs.common.module.frameworkimpl.mail.dao.IMailMessageDao;
import com.ibs.common.module.frameworkimpl.mail.dto.MailMessageDto;
import com.ibs.portal.framework.server.dao.jdbc.BaseJdbcDao;
import com.ibs.portal.framework.util.StringUtils;

public class MailMessageDaoImpl extends BaseJdbcDao implements IMailMessageDao {

	public void saveMail(final MailMessageDto mailMessage){
		logger.trace("entering dao...");

		StringBuffer sql = new StringBuffer();
		sql.append("insert into pbl.T_PBL_MAIL_MESSAGE (");
		sql.append("ID,");
		sql.append("CREATE_TIME,");
		sql.append("TO_ADDRS,");
		sql.append("SUBJECT,");
		sql.append("TEXT,");
		sql.append("USE_HTML,");
		sql.append("ATTACHMENT_DIR,");
		sql.append("FAILURE_COUNT,");
		sql.append("FAILURE_TIME )");
		sql.append(" values (?,?,?,?,?,?,?,?,?)");

		this.getJdbcTemplate().update(sql.toString(),new PreparedStatementSetter(){
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1 , StringUtils.getUUID());	// ID
				ps.setTimestamp(2 , new Timestamp(System.currentTimeMillis()));	// CREATE_TIME
				ps.setString(3 , mailMessage.getToAddrs());	// TO_ADDRS
				ps.setString(4 , mailMessage.getSubject());	// SUBJECT
				ps.setString(5 , mailMessage.getText());	// TEXT
				if (mailMessage.getUseHtml() == null)	// USE_HTML
					ps.setLong(6 , 0l);
				else
					ps.setLong(6 , mailMessage.getUseHtml());
				ps.setString(7 , mailMessage.getAttachmentDir());	// ATTACHMENT_DIR
				if (mailMessage.getFailureCount() == null)	// FAILURE_COUNT
					ps.setLong(8 , 0l);
				else
					ps.setLong(8 , mailMessage.getFailureCount());
				if(mailMessage.getFailureTime()==null)
					ps.setNull(9, java.sql.Types.TIMESTAMP);	// FAILURE_TIME
				else
					ps.setTimestamp(9 , new Timestamp(mailMessage.getFailureTime().getTime()));
			}
		});
	}

	public void saveMails(final List<MailMessageDto> mailMessageList){
		logger.trace("entering dao...");

		if (mailMessageList == null || mailMessageList.size() == 0)
			return;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into pbl.T_PBL_MAIL_MESSAGE (");
		sql.append("ID,");
		sql.append("CREATE_TIME,");
		sql.append("TO_ADDRS,");
		sql.append("SUBJECT,");
		sql.append("TEXT,");
		sql.append("USE_HTML,");
		sql.append("ATTACHMENT_DIR,");
		sql.append("FAILURE_COUNT,");
		sql.append("FAILURE_TIME )");
		sql.append(" values (?,?,?,?,?,?,?,?,?)");

		this.getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){

			public int getBatchSize() {
				return mailMessageList.size();
			}

			public void setValues(PreparedStatement ps, int index)
			throws SQLException {

				MailMessageDto o = mailMessageList.get(index);
				int i = 0;

				ps.setString(++i , StringUtils.getUUID());	// ID
				ps.setTimestamp(++i , new Timestamp(System.currentTimeMillis()));	// CREATE_TIME
				ps.setString(++i , o.getToAddrs());	// TO_ADDRS
				ps.setString(++i , o.getSubject());	// SUBJECT
				ps.setString(++i , o.getText());	// TEXT
				if (o.getUseHtml() == null)	// USE_HTML
					ps.setLong(++i , 0l);
				else
					ps.setLong(++i , o.getUseHtml());
				ps.setString(++i , o.getAttachmentDir());	// ATTACHMENT_DIR
				if (o.getFailureCount() == null)	// FAILURE_COUNT
					ps.setLong(++i , 0l);
				else
					ps.setLong(++i , o.getFailureCount());
				if(o.getFailureTime()==null)
					ps.setNull(++i, java.sql.Types.TIMESTAMP);	// FAILURE_TIME
				else
					ps.setTimestamp(++i , new Timestamp(o.getFailureTime().getTime()));

			}
		});
	}

}
