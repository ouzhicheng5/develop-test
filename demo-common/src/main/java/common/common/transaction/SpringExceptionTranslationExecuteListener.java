package common.common.transaction;

import org.jooq.ExecuteContext;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

import java.sql.SQLException;


public class SpringExceptionTranslationExecuteListener extends DefaultExecuteListener {

    @Override
    public void exception(ExecuteContext ctx) {
        SQLException e = ctx.sqlException();

        if (e != null) {
            String name = ctx.configuration().dialect().thirdParty().springDbName();

            SQLExceptionTranslator translator = (name != null)
                    ? new SQLErrorCodeSQLExceptionTranslator(name)
                    : new SQLStateSQLExceptionTranslator();

            ctx.exception(translator.translate("jOOQ", ctx.sql(), e));
        }
    }
}
