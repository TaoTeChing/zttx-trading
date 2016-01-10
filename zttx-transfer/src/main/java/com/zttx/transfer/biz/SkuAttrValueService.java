/*
 * @(#)SkuAttrValueService.java 2015-9-25 下午12:34:12
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
import com.zttx.transfer.utils.SerialnoUtils;

/**
 * <p>File：SkuAttrValueService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-25 下午12:34:12</p>
 * <p>Company: 8637.com</p>
 *
 * @author 黄小平
 * @version 1.0
 */
public class SkuAttrValueService {
    public List<String> findDefaultItemId(String dealNo) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> result = new ArrayList<String>();
        try {
            connection = DbModelHelper.getInstance().getConnection();
            String findDefaultItemIdSql = "SELECT a.refrenceId,a.attributeId FROM CateAttributeItem a, CateAttribute b, CateAttributeRel c WHERE a.attributeItem = '默认' and b.attributeNo='101' AND a.attributeId =b.refrenceId and b.refrenceId=c.attributeId and c.cateNo=?";
            statement = connection.prepareStatement(findDefaultItemIdSql);
            statement.setLong(1, Long.parseLong(dealNo));
            resultSet = statement.executeQuery();
            int i = 0;
            String itemId = null;
            String attrId = null;
            while (resultSet.next()) {
                itemId = resultSet.getString("refrenceId");
                attrId = resultSet.getString("attributeId");
                i++;
            }
            if (i > 1) {
                throw new Exception("默认尺码超过一个");
            }
            result.add(itemId);
            result.add(attrId);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbModelHelper.getInstance().release(resultSet, statement, null);
        }
        throw new Exception("没有默认尺码");
    }

    public void updateBySkuId(String skuId, String productId, String attrItemId, String attrId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PreparedStatement statement2 = null;
        try {
            connection = DbModelHelper.getInstance().getConnection();
            connection.setAutoCommit(false);
            String findAttrValueBySkuId = "select * from ProductAttrValue where productSkuId=? and attributeId=101";
            statement = connection.prepareStatement(findAttrValueBySkuId);
            statement.setString(1, skuId);
            resultSet = statement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            if (i == 0) {
                String insertAttrValueSql = "insert into ProductAttrValue(refrenceId,productId,attributeId,attributeItemId,extAttributeValue,productSkuId,isSkuAttr,sortOrder) values(?,?,?,?,?,?,?,?)";
                statement2 = connection.prepareStatement(insertAttrValueSql);
                statement2.setString(1, SerialnoUtils.buildPrimaryKey());
                statement2.setString(2, productId);
                statement2.setString(3, attrId);
                statement2.setString(4, attrItemId);
                statement2.setString(5, "默认");
                statement2.setString(6, skuId);
                statement2.setBoolean(7, true);
                statement2.setInt(8, 99);
                statement2.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbModelHelper.getInstance().release(resultSet, statement, null);
            DbModelHelper.getInstance().release(null, statement2, null);
        }
    }
}
