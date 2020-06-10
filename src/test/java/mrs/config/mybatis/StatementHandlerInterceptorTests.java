package mrs.config.mybatis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Stream;

import org.apache.ibatis.mapping.SqlCommandType;
import org.junit.jupiter.api.Test;

class StatementHandlerInterceptorTests {

	@Test
	void testIntercept() {
		fail("まだ実装されていません"); // TODO
	}

	@Test
	void testIsInterceptTarget() {
		fail("まだ実装されていません"); // TODO
	}

	@Test
	void testUpdateNullValues() {
		fail("まだ実装されていません"); // TODO
	}

	@Test
	void getColumnParamIndexMap_sqlCommandTypeがINSERTの場合getColumnParamIndexMapForInsertが呼び出されること() {
		var statementHandlerInterceptor = spy(StatementHandlerInterceptor.class);
		var sql = "test";

		doReturn(Map.of()).when(statementHandlerInterceptor).getColumnParamIndexMapForInsert(eq(sql));

		statementHandlerInterceptor.getColumnParamIndexMap(SqlCommandType.INSERT, sql);

		verify(statementHandlerInterceptor, times(1)).getColumnParamIndexMapForInsert(eq(sql));
		verify(statementHandlerInterceptor, times(0)).getColumnParamIndexMapForUpdate(anyString());
	}

	@Test
	void getColumnParamIndexMap_sqlCommandTypeがUPDATEの場合getColumnParamIndexMapForUpdateが呼び出されること() {
		var statementHandlerInterceptor = spy(StatementHandlerInterceptor.class);
		var sql = "test";

		doReturn(Map.of()).when(statementHandlerInterceptor).getColumnParamIndexMapForUpdate(eq(sql));

		statementHandlerInterceptor.getColumnParamIndexMap(SqlCommandType.UPDATE, sql);

		verify(statementHandlerInterceptor, times(0)).getColumnParamIndexMapForInsert(anyString());
		verify(statementHandlerInterceptor, times(1)).getColumnParamIndexMapForUpdate(eq(sql));
	}

	@Test
	void getColumnParamIndexMap_sqlCommandTypeがINSERTかUPDATEではない場合getColumnParamIndexMapForUpdateが呼び出されること() {
		var statementHandlerInterceptor = mock(StatementHandlerInterceptor.class);
		var sql = "test";

		Stream.of(SqlCommandType.values()).filter((sqlCommandType) -> {
			return SqlCommandType.INSERT != sqlCommandType && SqlCommandType.UPDATE != sqlCommandType;
		}).forEach((sqlCommandType) -> {
			statementHandlerInterceptor.getColumnParamIndexMap(sqlCommandType, sql);
		});

		verify(statementHandlerInterceptor, times(0)).getColumnParamIndexMapForInsert(sql);
		verify(statementHandlerInterceptor, times(0)).getColumnParamIndexMapForUpdate(sql);
	}

	@Test
	void getColumnParamIndexMapForInsert_insert文からcolumnごとにパラメータのindexを保持したMapが正しく作成されること() {
		StringJoiner column = new StringJoiner(",", "INSERT INTO table_name (", ")");
		column.add("prop1");
		column.add("prop2");
		column.add("prop3");
		column.add("prop4");
		column.add("prop5");

		StringJoiner param = new StringJoiner(",", " VALUES (", ")");
		param.add("?");
		param.add("?");
		param.add("?");
		param.add("NULL");
		param.add("?");

		String sql = column.toString() + param.toString();
		var statementHandlerInterceptor = new StatementHandlerInterceptor();
		var expected = statementHandlerInterceptor.getColumnParamIndexMapForInsert(sql);

		var actual = Map.of("prop1", List.of(1), "prop2", List.of(2), "prop3", List.of(3), "prop5", List.of(4));

		assertEquals(expected, actual);
	}

	@Test
	void getColumnParamIndexMapForUpdate_update文からcolumnごとにパラメータのindexを保持したMapが正しく作成されること() {
		StringJoiner column = new StringJoiner(",", "UPDATE table_name SET ", "");
		column.add("prop1 = ?");
		column.add("prop2 = ?");
		column.add("prop3 = ?");
		column.add("prop4 = NULL");

		StringJoiner param = new StringJoiner(" AND ", " WHERE ", "");
		param.add("prop4 IS NULL");
		param.add("prop2 = ?");
		param.add("prop3 = ?");

		String sql = column.toString() + param.toString();
		var statementHandlerInterceptor = new StatementHandlerInterceptor();
		var expected = statementHandlerInterceptor.getColumnParamIndexMapForUpdate(sql);

		var actual = Map.of("prop1", List.of(1), "prop2", List.of(2,4), "prop3", List.of(3, 5));

		assertEquals(expected, actual);
	}

	@Test
	void getMapByColumnAndParam_columnごとにindexを保持したMapが正しく作成されること() {
		var statementHandlerInterceptor = new StatementHandlerInterceptor();

		List<String> columnList = List.of("prop1", "prop2", "prop3", "prop2");
		List<String> paramList = List.of("?", "?", "?", "?");
		var expected = statementHandlerInterceptor.getMapByColumnAndParam(columnList, paramList);
		var actual = Map.of("prop1", List.of(1), "prop2", List.of(2,4), "prop3", List.of(3));

		assertEquals(actual, expected);
	}

}
