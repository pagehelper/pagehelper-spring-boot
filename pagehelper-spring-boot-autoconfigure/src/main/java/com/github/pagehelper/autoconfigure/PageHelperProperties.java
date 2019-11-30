/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.pagehelper.autoconfigure;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for PageHelper.
 *
 * @author liuzh
 */
@ConfigurationProperties(prefix = PageHelperProperties.PAGEHELPER_PREFIX)
public class PageHelperProperties {

    public static final String PAGEHELPER_PREFIX = "pagehelper";

    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

	public Boolean getOffsetAsPageNum() {
		return Boolean.valueOf(properties.getProperty("offsetAsPageNum"));
    }

	public void setOffsetAsPageNum(Boolean offsetAsPageNum) {
		properties.setProperty("offsetAsPageNum", offsetAsPageNum.toString());
    }

	public Boolean getRowBoundsWithCount() {
		return Boolean.valueOf(properties.getProperty("rowBoundsWithCount"));
    }

	public void setRowBoundsWithCount(Boolean rowBoundsWithCount) {
		properties.setProperty("rowBoundsWithCount", rowBoundsWithCount.toString());
    }

	public Boolean getPageSizeZero() {
		return Boolean.valueOf(properties.getProperty("pageSizeZero"));
    }

	public void setPageSizeZero(Boolean pageSizeZero) {
		properties.setProperty("pageSizeZero", pageSizeZero.toString());
    }

	public Boolean getReasonable() {
		return Boolean.valueOf(properties.getProperty("reasonable"));
    }

	public void setReasonable(Boolean reasonable) {
		properties.setProperty("reasonable", reasonable.toString());
    }

	public Boolean getSupportMethodsArguments() {
		return Boolean.valueOf(properties.getProperty("supportMethodsArguments"));
    }

	public void setSupportMethodsArguments(Boolean supportMethodsArguments) {
		properties.setProperty("supportMethodsArguments", supportMethodsArguments.toString());
    }

    public String getDialect() {
        return properties.getProperty("dialect");
    }

    public void setDialect(String dialect) {
        properties.setProperty("dialect", dialect);
    }

    public String getHelperDialect() {
        return properties.getProperty("helperDialect");
    }

    public void setHelperDialect(String helperDialect) {
        properties.setProperty("helperDialect", helperDialect);
    }

	public Boolean getAutoRuntimeDialect() {
		return Boolean.valueOf(properties.getProperty("autoRuntimeDialect"));
    }

	public void setAutoRuntimeDialect(Boolean autoRuntimeDialect) {
		properties.setProperty("autoRuntimeDialect", autoRuntimeDialect.toString());
    }

	public Boolean getAutoDialect() {
		return Boolean.valueOf(properties.getProperty("autoDialect"));
    }

	public void setAutoDialect(Boolean autoDialect) {
		properties.setProperty("autoDialect", autoDialect.toString());
    }

	public Boolean getCloseConn() {
		return Boolean.valueOf(properties.getProperty("closeConn"));
    }

	public void setCloseConn(Boolean closeConn) {
		properties.setProperty("closeConn", closeConn.toString());
    }

    public String getParams() {
        return properties.getProperty("params");
    }

    public void setParams(String params) {
        properties.setProperty("params", params);
    }

	public Boolean getDefaultCount() {
		return Boolean.valueOf(properties.getProperty("defaultCount"));
    }

	public void setDefaultCount(Boolean defaultCount) {
		properties.setProperty("defaultCount", defaultCount.toString());
    }

    public String getDialectAlias() {
        return properties.getProperty("dialectAlias");
    }

    public void setDialectAlias(String dialectAlias) {
        properties.setProperty("dialectAlias", dialectAlias);
    }
}
