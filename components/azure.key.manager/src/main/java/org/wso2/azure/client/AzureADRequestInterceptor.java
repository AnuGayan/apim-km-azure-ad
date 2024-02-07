/*
 * Copyright © 2022 WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *http://www.apache.org/licenses/LICENSE-2.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.azure.client;

import org.wso2.carbon.apimgt.impl.APIConstants;
import org.wso2.carbon.apimgt.impl.recommendationmgt.AccessTokenGenerator;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AzureADRequestInterceptor implements RequestInterceptor {

    private AccessTokenGenerator accessTokenGenerator;
    private String defaultScope;

    public AzureADRequestInterceptor(AccessTokenGenerator accessTokenGenerator, String defaultScope) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.defaultScope = defaultScope;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String[] scopes = { defaultScope };
        String accessToken = accessTokenGenerator.getAccessToken(scopes);
        requestTemplate
                .header(APIConstants.AUTHORIZATION_HEADER_DEFAULT, APIConstants.AUTHORIZATION_BEARER + accessToken);
    }

}
