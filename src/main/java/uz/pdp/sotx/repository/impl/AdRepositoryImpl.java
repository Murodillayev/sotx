package uz.pdp.sotx.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.sotx.model.entity.Ad;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;
import uz.pdp.sotx.repository.AdRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdRepositoryImpl implements AdRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Ad save(Ad ad) {
        Optional<Ad> byId = findById(ad.getId());

        String insertSql = "insert into ads(title,description,category,currency,price,created_by,updated_by,deleted,id) values (?,?,?,?,?,?,?,?,?)";
        String updateSql = "update ads set title=?,description=?,category=?,currency=?,price=?, updated_at=now(),updated_by=?,deleted=? where id=?";

        if (byId.isPresent()) {
            jdbcTemplate.update(updateSql, ad.getTitle(), ad.getDescription(), ad.getCategory().name(), ad.getCurrency().name(), ad.getPrice(), ad.getUpdatedBy(), ad.getDeleted(), ad.getId());
        } else {
            jdbcTemplate.update(insertSql, ad.getTitle(), ad.getDescription(), ad.getCategory().name(), ad.getCurrency().name(), ad.getPrice(), ad.getCreatedBy(), ad.getUpdatedBy(), ad.getDeleted(), ad.getId());
        }

        return ad;
    }

    @Override
    public Optional<Ad> findById(String id) {
        String sql = "select * from ads where not deleted and id = ?";
        try {
            Ad ad = jdbcTemplate.queryForObject(sql, rowMapper(), id);
            return Optional.ofNullable(ad);
        } catch (Exception e) {
            return Optional.empty();
        }


    }

    @Override
    public List<Ad> findAll() {
        String sql = "select * from ads where not deleted order by created_at desc";
        return jdbcTemplate.query(sql, rowMapper());
    }

    private RowMapper<Ad> rowMapper() {
        return (rs, rowNum) -> {
            Ad ad = new Ad();
            String category = rs.getString("category");
            String currency = rs.getString("currency");

            ad.setId(rs.getString("id"));
            ad.setTitle(rs.getString("title"));
            ad.setDescription(rs.getString("description"));
            ad.setCategory(Category.valueOf(category));
            ad.setCurrency(Currency.valueOf(currency));
            ad.setPrice(rs.getDouble("price"));
            ad.setCreatedBy(rs.getString("created_by"));
            ad.setUpdatedBy(rs.getString("updated_by"));
            ad.setDeleted(rs.getBoolean("deleted"));
            ad.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            ad.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return ad;
        };
    }
}
