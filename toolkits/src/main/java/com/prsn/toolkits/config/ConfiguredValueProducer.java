package com.prsn.toolkits.config;


import com.prsn.toolkits.AppMode;
import com.prsn.toolkits.config.annotation.ConfiguredValue;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * Загрузка значений отмеченных {@link ConfiguredValue} из конфигурационного файла.
 * <p>
 * Для загрузки параметров из файла нужно создать фабрику для {@link Configuration}.
 * </p>
 */
@Dependent
public class ConfiguredValueProducer {

    @Inject
    @SuppressWarnings("cdi-ambiguous-dependency")
    protected ConfigurationProducer producer;

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public AppMode getAuthTypeConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final AppMode result;
        final String defaultValue = StringUtils.isNotEmpty(ann.def()) ? ann.def() : null;
        if(ann.required() && defaultValue == null) {
            result = AppMode.valueOf(producer.getConfiguration().getString(ann.value()));
        } else {
            result = AppMode.valueOf(producer.getConfiguration().getString(ann.value(), defaultValue));
        }
        return result;
    }
    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public String getStringConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final String result;
        final String defaultValue = StringUtils.isNotEmpty(ann.def()) ? ann.def() : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getString(ann.value());
        } else {
            result = producer.getConfiguration().getString(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public Boolean getBooleanConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final Boolean result;
        final Boolean defaultValue = StringUtils.isNotEmpty(ann.def()) ? new Boolean(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getBoolean(ann.value());
        } else {
            result = producer.getConfiguration().getBoolean(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public Byte getByteConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final Byte result;
        final Byte defaultValue = StringUtils.isNotEmpty(ann.def()) ? Byte.valueOf(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getByte(ann.value());
        } else {
            result = producer.getConfiguration().getByte(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public Short getShortConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final Short result;
        final Short defaultValue = StringUtils.isNotEmpty(ann.def()) ? Short.valueOf(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getShort(ann.value());
        } else {
            result = producer.getConfiguration().getShort(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public Integer getIntegerConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final Integer result;
        final Integer defaultValue = StringUtils.isNotEmpty(ann.def()) ? Integer.valueOf(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getInt(ann.value());
        } else {
            result = producer.getConfiguration().getInteger(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public Long getLongConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final Long result;
        final Long defaultValue = StringUtils.isNotEmpty(ann.def()) ? Long.valueOf(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getLong(ann.value());
        } else {
            result = producer.getConfiguration().getLong(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public BigInteger getBigIntegerConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final BigInteger result;
        final BigInteger defaultValue = StringUtils.isNotEmpty(ann.def()) ? new BigInteger(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getBigInteger(ann.value());
        } else {
            result = producer.getConfiguration().getBigInteger(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public BigDecimal getBigDecimalConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final BigDecimal result;
        final BigDecimal defaultValue = StringUtils.isNotEmpty(ann.def()) ? new BigDecimal(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getBigDecimal(ann.value());
        } else {
            result = producer.getConfiguration().getBigDecimal(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public Float getFloatConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final Float result;
        final Float defaultValue = StringUtils.isNotEmpty(ann.def()) ? Float.valueOf(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getFloat(ann.value());
        } else {
            result = producer.getConfiguration().getFloat(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public Double getDoubleConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final Double result;
        final Double defaultValue = StringUtils.isNotEmpty(ann.def()) ? Double.valueOf(ann.def()) : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getDouble(ann.value());
        } else {
            result = producer.getConfiguration().getDouble(ann.value(), defaultValue);
        }
        return result;
    }

    @Produces
    @ConfiguredValue(StringUtils.EMPTY)
    public String[] getStringArrayConfigValue(InjectionPoint ip) {
        final ConfiguredValue ann = ip.getAnnotated().getAnnotation(ConfiguredValue.class);
        final String[] result;
        final String defaultValue = StringUtils.isNotEmpty(ann.def()) ? ann.def() : null;
        if(ann.required() && defaultValue == null) {
            result = producer.getConfiguration().getStringArray(ann.value());
        } else {
            final List<Object> resultList =
                    producer.getConfiguration().getList(ann.value(), Arrays.asList(defaultValue.split(",")));
            result = resultList.stream().map((item) -> item.toString()).toArray(String[]::new);
        }
        return result;
    }
}
