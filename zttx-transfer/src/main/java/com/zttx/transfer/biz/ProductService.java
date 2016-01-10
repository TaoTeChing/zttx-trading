/*
 * @(#)ProductService.java 2015-9-25 下午12:35:49
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.transfer.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zttx.transfer.utils.DbModelHelper;

/**
 * <p>File：ProductService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-25 下午12:35:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author 黄小平
 * @version 1.0
 */
public class ProductService {
    public List<String> findProductByDealDicNo(String dealNo) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> idList = new ArrayList<String>();
        try {
            connection = DbModelHelper.getInstance().getConnection();
            String findProductByDealDicNoSql = "select refrenceId as productId from ProductBaseInfo where dealNo=?";
            statement = connection.prepareStatement(findProductByDealDicNoSql);
            statement.setLong(1, Long.parseLong(dealNo));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String productId = resultSet.getString("productId");
                idList.add(productId);
            }
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbModelHelper.getInstance().release(resultSet, statement, null);
        }
        return null;
    }
}
