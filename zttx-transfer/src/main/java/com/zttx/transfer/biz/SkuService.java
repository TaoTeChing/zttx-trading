/*
 * @(#)SkuService.java 2015-9-25 下午12:34:00
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
 * <p>File：SkuService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-25 下午12:34:00</p>
 * <p>Company: 8637.com</p>
 *
 * @author 黄小平
 * @version 1.0
 */
public class SkuService {
    public List<String> updateSku(String productId, String attrItemId) {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        List<String> skuIds = new ArrayList<String>();
        try {
            connection = DbModelHelper.getInstance().getConnection();
            connection.setAutoCommit(false);
            String selectSkuByProductId = "select * from ProductSku where productId=?";
            statement = connection.prepareStatement(selectSkuByProductId);
            statement.setString(1, productId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String attrItemIds = resultSet.getString("attrItemIds");
                if (attrItemIds.indexOf("_") == -1) {
                    String refrenceId = resultSet.getString("refrenceId");
                    skuIds.add(refrenceId);
                    String productSkuName = resultSet.getString("productSkuName");
                    productSkuName = productSkuName + "_" + "默认";
                    String orgAttrItemIds = resultSet.getString("attrItemIds");
                    orgAttrItemIds = orgAttrItemIds + "_" + attrItemId;
                    String updateSkuSql = "update ProductSku set productSkuName=?,attrItemIds=? where refrenceId=?";
                    statement2 = connection.prepareStatement(updateSkuSql);
                    statement2.setString(1, productSkuName);
                    statement2.setString(2, orgAttrItemIds);
                    statement2.setString(3, refrenceId);
                    statement2.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbModelHelper.getInstance().release(resultSet, statement, null);
            DbModelHelper.getInstance().release(null, statement2, null);
        }
        return skuIds;
    }
}
