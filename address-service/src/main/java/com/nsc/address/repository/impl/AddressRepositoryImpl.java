package com.nsc.address.repository.impl;

import com.nsc.address.model.address.Address;
import com.nsc.address.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AddressRepositoryImpl implements IAddressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Address> rowMapper = new RowMapper<Address>() {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setId(rs.getLong("id"));
            address.setCity(rs.getString("city"));
            address.setDistrict(rs.getString("district"));
            return address;
        }
    };

    @Override
    public List<Address> findAll() {
        String sql = "select id, city, district from address";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Address findById(Long id) {
        String sql = "select id, city, district from address where id = ?";
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper, id));
    }

    @Override
    public void create(Address address) {
        String sql = "insert into address(city, district) values(?, ?)";
        jdbcTemplate.update(sql, address.getCity(), address.getDistrict());
    }

    @Override
    public void update(Address address) {
        String sql = "update address set city = :city, district = :district where id = :id";
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id", address.getId());
        parameterMap.put("city", address.getCity());
        parameterMap.put("district", address.getDistrict());
        namedParameterJdbcTemplate.update(sql, parameterMap);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id", id);
        String sql = "delete from address where id = :id";
        namedParameterJdbcTemplate.update(sql, parameterMap);
    }
}
