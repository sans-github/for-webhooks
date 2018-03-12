package com.spring.boot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class WebhooksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhooksController.class);

    @RequestMapping(value = "/hello", method = GET)
    @ResponseBody
    public String hello() {
        return "hello there\n";
    }


    @RequestMapping(value = "/webhooks", method = POST)
    public void handleWebhooks(final HttpServletRequest httpServletRequest) {
        LOGGER.info("bt_signature={}", extractFromServletRequest(httpServletRequest, "bt_signature"));
        LOGGER.info("bt_payload={}", extractFromServletRequest(httpServletRequest, "bt_payload"));
    }

    @RequestMapping(value = "/webhooks/{merchant_account_id}", method = POST)
    public void handle(@PathVariable("merchant_account_id") @NotEmpty final String merchantAccountId, final HttpServletRequest httpServletRequest) {

        LOGGER.info("merchant_account_id={}", merchantAccountId);

        printObject(httpServletRequest.getParameterMap());
    }

    @RequestMapping(value = "/object", method = POST, consumes = APPLICATION_JSON_VALUE)
    public String handleAnyJson(final InputStream inputStream) throws IOException {
        final String jsonInRequest = IOUtils.toString(inputStream, Charset.defaultCharset());
        LOGGER.info("jsonInRequest={}", jsonInRequest);
        return jsonInRequest;
    }

    private static void printObject(final Object object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(INDENT_OUTPUT);

        try {
            LOGGER.info("serializedObject={}", objectMapper.writeValueAsString(object));
        } catch (final JsonProcessingException ex) {
            LOGGER.error("Error={}", ex.getMessage());
        }

    }

    private String extractFromServletRequest(final ServletRequest servletRequest, final String requestParam) {
        return servletRequest.getParameter(requestParam);
    }
}