/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2014 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.sun.faces.test.servlet30.systest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import static com.sun.faces.test.junit.JsfServerExclude.WEBLOGIC_12_1_3;
import static com.sun.faces.test.junit.JsfServerExclude.WEBLOGIC_12_1_4;
import static com.sun.faces.test.junit.JsfServerExclude.WEBLOGIC_12_2_1;
import com.sun.faces.test.junit.JsfTest;
import com.sun.faces.test.junit.JsfTestRunner;
import static com.sun.faces.test.junit.JsfVersion.JSF_2_2_0;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(JsfTestRunner.class)
public class TLValidatorIT {

    private String webUrl;
    private WebClient webClient;

    @Before
    public void setUp() {
        webUrl = System.getProperty("integration.url");
        webClient = new WebClient();
    }

    @After
    public void tearDown() {
        webClient.closeAllWindows();
    }

    @Test
    public void testHtmlBasicValidatorFail() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestHtmlBasicValidatorFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testHtmlBasicValidatorFail2() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "TestHtmlBasicValidatorFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testHtmlBasicValidatorSucceed() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestHtmlBasicValidatorSucceed.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testCoreValidatorFail() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestCoreValidatorFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testCoreValidatorFail2() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "TestCoreValidatorFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testCoreValidatorSucceed() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestCoreValidatorSucceed.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testCoreValidatorIfFail() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestCoreValidatorIfFail.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testCoreValidatorIfSucceed() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestCoreValidatorIfSucceed.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    /*
     * Because of differences in the JSP engine between Glassfish and Weblogic
     * and this issue not being raised as a bug by any user we exclude this 
     * test on the following servers: WLS 12.1.3 and WLS 12.1.4
     * 
     * 20140903 - edburns, mriem
     */
    @JsfTest(value=JSF_2_2_0, excludes = {WEBLOGIC_12_1_3, WEBLOGIC_12_1_4, WEBLOGIC_12_2_1})
    @Test
    public void testElValidatorActionRefFail() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorActionRefFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    /*
     * Because of differences in the JSP engine between Glassfish and Weblogic
     * and this issue not being raised as a bug by any user we exclude this 
     * test on the following servers: WLS 12.1.3 and WLS 12.1.4
     * 
     * 20140903 - edburns, mriem
     */
    @JsfTest(value=JSF_2_2_0, excludes = {WEBLOGIC_12_1_3, WEBLOGIC_12_1_4, WEBLOGIC_12_2_1})
    @Test
    public void testElValidatorComponentFail() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorComponentFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testElValidatorIdFail() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorIdFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testElValidatorValueRefFail() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorValueRefFail.jsp");
        assertEquals(500, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testElValidatorActionRefSucceed() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorActionRefSucceed.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testElValidatorComponentSucceed() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorComponentSucceed.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testElValidatorIdSucceed() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorIdSucceed.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testElValidatorValueRefSucceed() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/TestElValidatorValueRefSucceed.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }

    @Test
    public void testTlvTest01() throws Exception {
        webClient.setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "faces/jsp/tlvTest01.jsp");
        assertEquals(200, page.getWebResponse().getStatusCode());
        webClient.setThrowExceptionOnFailingStatusCode(true);
    }
}
