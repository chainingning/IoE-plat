package com.chaining.iot.common.exception;

/**
 *
 * @author ningc
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     * @return
     */
    String getResultCode();

    /**
     *
     * @return
     */
    String getResultMsg();
}
