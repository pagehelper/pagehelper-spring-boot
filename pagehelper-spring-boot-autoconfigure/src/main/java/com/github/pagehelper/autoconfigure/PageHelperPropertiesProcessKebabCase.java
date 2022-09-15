package com.github.pagehelper.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;

/**
 * 原来的{@link PageHelperProperties}继承了{@link java.util.Properties}<br>
 * 在使用中发现SpringBoot会直接将application.yml(application.properties)的配置名原样set到集合中，
 * 这样会导致配置文件里的kebab-case风格配置映射到配置类中还是kebab-case而不是camelCase，
 * 之后设置属性的时候就会因为找不到camelCase的配置导致失效。
 * <br>
 * 所有kebab-case风格的配置项都会产生这个问题，即配置不生效。
 * <br>
 * 这个类不继承{@link java.util.Properties}，用于提前接收配置文件中的配置项，
 * 由于没有继承{@link java.util.Properties}，SpringBoot可以做正常的风格转换，
 * 转换完成后再将camelCase风格的配置放到原来的{@link PageHelperProperties}中
 *
 * @author showen
 */
@ConfigurationProperties(prefix = PageHelperPropertiesProcessKebabCase.PAGEHELPER_PREFIX)
public class PageHelperPropertiesProcessKebabCase {

    public static final String PAGEHELPER_PREFIX = "pagehelper";

    private final PageHelperProperties properties;
    private Boolean offsetAsPageNum;
    private Boolean rowBoundsWithCount;
    private Boolean pageSizeZero;
    private Boolean reasonable;
    private Boolean supportMethodsArguments;
    private String dialect;
    private String helperDialect;
    private Boolean autoRuntimeDialect;
    private Boolean autoDialect;
    private Boolean closeConn;
    private String params;
    private Boolean defaultCount;
    private String dialectAlias;
    private String autoDialectClass;

    @Autowired
    public PageHelperPropertiesProcessKebabCase(PageHelperProperties properties) {
        this.properties = properties;
    }

    public Boolean getOffsetAsPageNum() {
        return offsetAsPageNum;
    }

    public void setOffsetAsPageNum(Boolean offsetAsPageNum) {
        this.offsetAsPageNum = offsetAsPageNum;
        Optional.ofNullable(offsetAsPageNum).ifPresent(properties::setOffsetAsPageNum);
    }

    public Boolean getRowBoundsWithCount() {
        return rowBoundsWithCount;
    }

    public void setRowBoundsWithCount(Boolean rowBoundsWithCount) {
        this.rowBoundsWithCount = rowBoundsWithCount;
        Optional.ofNullable(rowBoundsWithCount).ifPresent(properties::setRowBoundsWithCount);
    }

    public Boolean getPageSizeZero() {
        return pageSizeZero;
    }

    public void setPageSizeZero(Boolean pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
        Optional.ofNullable(pageSizeZero).ifPresent(properties::setPageSizeZero);
    }

    public Boolean getReasonable() {
        return reasonable;
    }

    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
        Optional.ofNullable(reasonable).ifPresent(properties::setReasonable);
    }

    public Boolean getSupportMethodsArguments() {
        return supportMethodsArguments;
    }

    public void setSupportMethodsArguments(Boolean supportMethodsArguments) {
        this.supportMethodsArguments = supportMethodsArguments;
        Optional.ofNullable(supportMethodsArguments).ifPresent(properties::setSupportMethodsArguments);
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
        Optional.ofNullable(dialect).ifPresent(properties::setDialect);
    }

    public String getHelperDialect() {
        return helperDialect;
    }

    public void setHelperDialect(String helperDialect) {
        this.helperDialect = helperDialect;
        Optional.ofNullable(helperDialect).ifPresent(properties::setHelperDialect);
    }

    public Boolean getAutoRuntimeDialect() {
        return autoRuntimeDialect;
    }

    public void setAutoRuntimeDialect(Boolean autoRuntimeDialect) {
        this.autoRuntimeDialect = autoRuntimeDialect;
        Optional.ofNullable(autoRuntimeDialect).ifPresent(properties::setAutoRuntimeDialect);
    }

    public Boolean getAutoDialect() {
        return autoDialect;
    }

    public void setAutoDialect(Boolean autoDialect) {
        this.autoDialect = autoDialect;
        Optional.ofNullable(autoDialect).ifPresent(properties::setAutoDialect);
    }

    public Boolean getCloseConn() {
        return closeConn;
    }

    public void setCloseConn(Boolean closeConn) {
        this.closeConn = closeConn;
        Optional.ofNullable(closeConn).ifPresent(properties::setCloseConn);
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
        Optional.ofNullable(params).ifPresent(properties::setParams);
    }

    public Boolean getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount(Boolean defaultCount) {
        this.defaultCount = defaultCount;
        Optional.ofNullable(defaultCount).ifPresent(properties::setDefaultCount);
    }

    public String getDialectAlias() {
        return dialectAlias;
    }

    public void setDialectAlias(String dialectAlias) {
        this.dialectAlias = dialectAlias;
        Optional.ofNullable(dialectAlias).ifPresent(properties::setDialectAlias);
    }

    public String getAutoDialectClass() {
        return autoDialectClass;
    }

    public void setAutoDialectClass(String autoDialectClass) {
        this.autoDialectClass = autoDialectClass;
        Optional.ofNullable(autoDialectClass).ifPresent(properties::setAutoDialectClass);
    }
}
