/*
 * Copyright (c) 2019, Okta, Inc. and/or its affiliates. All rights reserved.
 * The Okta software accompanied by this notice is provided pursuant to the Apache License,
 * Version 2.0 (the "License.")
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations under the
 * License.
 */
package com.okta.oidc;

import android.content.Context;
import android.graphics.Color;

import androidx.test.platform.app.InstrumentationRegistry;

import com.okta.oidc.clients.AuthClient;
import com.okta.oidc.clients.SyncAuthClient;
import com.okta.oidc.clients.web.SyncWebAuthClient;
import com.okta.oidc.clients.web.WebAuthClient;
import com.okta.oidc.net.HttpClientImpl;
import com.okta.oidc.net.OktaHttpClient;
import com.okta.oidc.storage.OktaStorage;
import com.okta.oidc.storage.SharedPreferenceStorage;
import com.okta.oidc.storage.security.EncryptionManager;
import com.okta.oidc.util.EncryptionManagerStub;
import com.okta.oidc.util.MockEndPoint;
import com.okta.oidc.util.TestValues;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 27)
public class OktaTest {

    private OIDCConfig mConfig;
    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private OktaHttpClient mHttpClient;
    private OktaStorage mStorage;
    private Context mContext;
    private int tabColor;
    private String[] supportBrowsers;
    private EncryptionManager mEncryptionManager;

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mStorage = new SharedPreferenceStorage(mContext);
        mHttpClient = new HttpClientImpl();
        mEncryptionManager = new EncryptionManagerStub();
        String url = new MockEndPoint().getUrl();
        mConfig = TestValues.getConfigWithUrl(url);

        tabColor = Color.BLACK;
        supportBrowsers = new String[]{""};
    }


    @Test
    public void testAsyncWebBuilder() {
        Okta.WebAuthBuilder builder = mock(Okta.WebAuthBuilder.class);
        WebAuthClient otherClient = new Okta.WebAuthBuilder()
                .withConfig(mConfig)
                .withStorage(mStorage)
                .withContext(mContext)
                .withOktaHttpClient(mHttpClient)
                .withCallbackExecutor(mExecutor)
                .withTabColor(tabColor)
                .supportedBrowsers(supportBrowsers)
                .withEncryptionManager(mEncryptionManager)
                .create();
        when(builder.create()).thenReturn(otherClient);

        builder.withConfig(mConfig);
        verify(builder).withConfig(mConfig);

        builder.withStorage(mStorage);
        verify(builder).withStorage(mStorage);

        builder.withOktaHttpClient(mHttpClient);
        verify(builder).withOktaHttpClient(mHttpClient);

        builder.withContext(mContext);
        verify(builder).withContext(mContext);

        builder.withCallbackExecutor(mExecutor);
        verify(builder).withCallbackExecutor(mExecutor);

        builder.withTabColor(tabColor);
        verify(builder).withTabColor(tabColor);

        builder.supportedBrowsers(supportBrowsers);
        verify(builder).supportedBrowsers(supportBrowsers);

        builder.withEncryptionManager(mEncryptionManager);
        verify(builder).withEncryptionManager(mEncryptionManager);

        Object client = builder.create();
        verify(builder).create();
        assertEquals(otherClient, client);
    }

    @Test
    public void testAsyncNativeBuilder() {
        Okta.AuthBuilder builder = mock(Okta.AuthBuilder.class);
        AuthClient otherClient = new Okta.AuthBuilder()
                .withConfig(mConfig)
                .withStorage(mStorage)
                .withContext(mContext)
                .withOktaHttpClient(mHttpClient)
                .withCallbackExecutor(mExecutor)
                .withEncryptionManager(mEncryptionManager)
                .create();
        when(builder.create()).thenReturn(otherClient);

        builder.withConfig(mConfig);
        verify(builder).withConfig(mConfig);

        builder.withStorage(mStorage);
        verify(builder).withStorage(mStorage);

        builder.withOktaHttpClient(mHttpClient);
        verify(builder).withOktaHttpClient(mHttpClient);

        builder.withContext(mContext);
        verify(builder).withContext(mContext);

        builder.withCallbackExecutor(mExecutor);
        verify(builder).withCallbackExecutor(mExecutor);

        builder.withEncryptionManager(mEncryptionManager);
        verify(builder).withEncryptionManager(mEncryptionManager);

        Object client = builder.create();
        verify(builder).create();
        assertEquals(otherClient, client);
    }

    @Test
    public void testSyncWebBuilder() {
        Okta.SyncWebAuthBuilder builder = mock(Okta.SyncWebAuthBuilder.class);
        SyncWebAuthClient otherClient = new Okta.SyncWebAuthBuilder()
                .withConfig(mConfig)
                .withStorage(mStorage)
                .withContext(mContext)
                .withOktaHttpClient(mHttpClient)
                .withTabColor(tabColor)
                .supportedBrowsers(supportBrowsers)
                .withEncryptionManager(mEncryptionManager)
                .create();
        when(builder.create()).thenReturn(otherClient);

        builder.withConfig(mConfig);
        verify(builder).withConfig(mConfig);

        builder.withStorage(mStorage);
        verify(builder).withStorage(mStorage);

        builder.withOktaHttpClient(mHttpClient);
        verify(builder).withOktaHttpClient(mHttpClient);

        builder.withContext(mContext);
        verify(builder).withContext(mContext);

        builder.withTabColor(tabColor);
        verify(builder).withTabColor(tabColor);

        builder.supportedBrowsers(supportBrowsers);
        verify(builder).supportedBrowsers(supportBrowsers);

        builder.withEncryptionManager(mEncryptionManager);
        verify(builder).withEncryptionManager(mEncryptionManager);

        Object client = builder.create();
        verify(builder).create();
        assertEquals(otherClient, client);
    }

    @Test
    public void testSyncNativeBuilder() {
        Okta.SyncAuthBuilder builder = mock(Okta.SyncAuthBuilder.class);
        SyncAuthClient otherClient = new Okta.SyncAuthBuilder()
                .withConfig(mConfig)
                .withStorage(mStorage)
                .withContext(mContext)
                .withOktaHttpClient(mHttpClient)
                .withEncryptionManager(mEncryptionManager)
                .create();
        when(builder.create()).thenReturn(otherClient);

        builder.withConfig(mConfig);
        verify(builder).withConfig(mConfig);

        builder.withStorage(mStorage);
        verify(builder).withStorage(mStorage);

        builder.withOktaHttpClient(mHttpClient);
        verify(builder).withOktaHttpClient(mHttpClient);

        builder.withContext(mContext);
        verify(builder).withContext(mContext);

        builder.withEncryptionManager(mEncryptionManager);
        verify(builder).withEncryptionManager(mEncryptionManager);

        Object client = builder.create();
        verify(builder).create();
        assertEquals(otherClient, client);
    }
}
