package common.common.db;

import common.util.PropertyUtil;
import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.*;


public class JooqPgGenerator {

    /**
     * Jooq 自动化生成代码
     */
    public void gen() {
        Jdbc jdbc = new Jdbc().withDriver(PropertyUtil.getProperty("jdbc.driverClassName"))
                .withUrl(PropertyUtil.getProperty("jdbc.url"))
                .withUser(PropertyUtil.getProperty("jdbc.username"))
                .withPassword(PropertyUtil.getProperty("jdbc.password"));
        Database database = new Database()
                .withName("org.jooq.util.mysql.MySQLDatabase")
                //.withExcludes("schema_version")
                .withSchemata(new Schema().withInputSchema(PropertyUtil.getProperty("jdbc.schema")));
        Generator generator = new Generator()
                .withName("org.jooq.util.DefaultGenerator")
                .withDatabase(database)
                .withGenerate(new Generate()
                )
                .withTarget(new Target()
                        .withPackageName("jooqEntity").withDirectory("demo-entity/src/main/java"));
        Configuration conf = new Configuration()
                .withJdbc(jdbc)
                .withGenerator(generator);

        try {
            GenerationTool.generate(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        JooqPgGenerator jg = new JooqPgGenerator();
        jg.gen();
        System.out.println("JooqPgGenerator finished.");
    }

}
