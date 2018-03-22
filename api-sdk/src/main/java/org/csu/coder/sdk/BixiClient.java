package org.csu.coder.sdk;

/**
 * @author by bixi.lx
 * @created on 2018 03 22 13:50
 */
public interface BixiClient {

    <T extends BixiResponse> T execute(BixiRequest<T> bixiRequest) throws Exception;

    <T extends BixiResponse> T execute(BixiRequest<T> bixiRequest, String session) throws Exception;
}
