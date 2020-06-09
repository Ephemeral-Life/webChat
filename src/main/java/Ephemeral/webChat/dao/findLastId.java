//寻找表中最后一条记录的msgId值
package Ephemeral.webChat.dao;

import org.apache.ibatis.annotations.Param;


public interface findLastId
{
	int Id(@Param("listName")String listName, @Param("table")String table);
}
