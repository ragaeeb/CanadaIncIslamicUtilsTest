package com.canadainc.sunnah10;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

final class StubSunnahCollectionsTable implements SunnahPrimaryTable<String>
{
	@Override
	public void setLanguage(String language) {}
	
	@Override
	public void setConnection(Connection c) {}
	
	@Override
	public String getTableName() {
		return null;
	}
	
	@Override
	public void process(Collection<String> elements) throws SQLException {}
	
	@Override
	public int getIdFor(String code)
	{
		if ( code.equals("bukhari") ) {
			return 1;
		} else {
			return 2;
		}
	}

	@Override
	public void createIndices() throws SQLException
	{
	}
}