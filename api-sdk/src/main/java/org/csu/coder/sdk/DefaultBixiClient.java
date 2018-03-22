package org.csu.coder.sdk;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author by bixi.lx
 * @created on 2018 03 22 13:53
 */
public class DefaultBixiClient implements BixiClient{

    @Override
    public <T extends BixiResponse> T execute(BixiRequest<T> bixiRequest) throws Exception {
        return this.execute(bixiRequest, null);
    }

    @Override
    public <T extends BixiResponse> T execute(BixiRequest<T> bixiRequest, String session) throws Exception {
        return this._execute(bixiRequest, session);
    }

    public <T extends BixiResponse> T _execute(BixiRequest<T> bixiRequest, String session) throws Exception {
        T bixiResponse = null;
        return bixiResponse;
    }
}
