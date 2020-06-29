package com.example.paymentservice.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonConstant {

  private CommonConstant() {
  }

  public static final ObjectMapper MAPPER = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  static {
    MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  public static final String PACKAGE_ENTITIES = "com.viettel.mobilemoney.model.entity";
  public static final String MM_JPA_UNIT_NAME = "MM_JPA_UNIT_NAME";
  public static final String EQUAL = "=";
  public static final String AND = "&";
  public static final String QUESTION = "?";
  public static final String CHANNEL = "Channel";
  public static final String X_REQUEST_ID = "X-Request-Id";
  public static final String TIMESTAMP = "Timestamp";
  public static final String TIMESTAMP_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";
  public static final String ACCEPT_LANGUAGE_VI = "vi";
  public static final int PAID_ORDER = 1;
}