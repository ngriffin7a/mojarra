/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright (c) 1997-2016 Oracle and/or its affiliates. All rights reserved.
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
package javax.faces.lifecycle;

import java.util.Map;

import javax.faces.FacesWrapper;
import javax.faces.context.FacesContext;

/**
 * <p class="changed_added_2_2">Wrapper for {@link ClientWindow}</p>
 * 
 * <p class="changed_modified_2_3">Usage: extend this class and push the implementation being wrapped to the
 * constructor and use {@link #getWrapped} to access the instance being wrapped.</p>
 * 
 * @since 2.2
 */
public abstract class ClientWindowWrapper extends ClientWindow implements FacesWrapper<ClientWindow> {

    private ClientWindow wrapped;
    
    /**
     * @deprecated Use the other constructor taking the implementation being wrapped.
     */
    @Deprecated
    public ClientWindowWrapper() {

    }

    /**
     * <p class="changed_added_2_3">If this client window has been decorated, 
     * the implementation doing the decorating should push the implementation being wrapped to this constructor.
     * The {@link #getWrapped()} will then return the implementation being wrapped.</p>
     * 
     * @param wrapped The implementation being wrapped.
     * @since 2.3
     */
    public ClientWindowWrapper(ClientWindow wrapped) {
        this.wrapped = wrapped;
    }
    
    @Override
    public ClientWindow getWrapped() {
        return wrapped;
    }

    @Override
    public String getId() {
        return getWrapped().getId();
    }

    @Override
    public Map<String, String> getQueryURLParameters(FacesContext context) {
        return getWrapped().getQueryURLParameters(context);
    }

    @Override
    public void disableClientWindowRenderMode(FacesContext context) {
        getWrapped().disableClientWindowRenderMode(context);
    }

    @Override
    public void enableClientWindowRenderMode(FacesContext context) {
        getWrapped().enableClientWindowRenderMode(context);
    }

    @Override
    public boolean isClientWindowRenderModeEnabled(FacesContext context) {
        return getWrapped().isClientWindowRenderModeEnabled(context);
    }
    
    @Override
    public void decode(FacesContext context) {
        getWrapped().decode(context);
    }

    
}
