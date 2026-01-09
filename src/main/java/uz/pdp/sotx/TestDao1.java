package uz.pdp.sotx;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("test1")
public class TestDao1 implements TestDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Test> getAll(){
        return jdbcTemplate.query("select * from test", (rs, rowNum) -> {
            Test test = new Test();
            test.setId(rs.getString("id"));
            test.setName(rs.getString("name"));
            return test;
        });
    }
}
