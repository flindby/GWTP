/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.mvp.rebind.util;

import com.google.gwt.dev.javac.testing.impl.MockJavaResource;
import com.google.gwt.dev.resource.Resource;
import com.google.gwt.inject.client.GinModule;
import com.gwtplatform.mvp.client.ApplicationController;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.PreBootstrapper;
import com.gwtplatform.mvp.rebind.model.BarModule;
import com.gwtplatform.mvp.rebind.model.FooModule;

/**
 * Contains GWTP and dependency sources for testing.
 */
public class GwtpResourceBase {

    public static final MockJavaResource DEFAULT_BOOTSTRAPPER =
            new MockJavaResource("com.gwtplatform.mvp.client.DefaultBootstrapper") {
                @Override
                public CharSequence getContent() {
                    StringBuilder code = new StringBuilder();
                    code.append("package com.gwtplatform.mvp.client;\n");
                    code.append("public class DefaultBootstrapper implements Bootstrapper {\n");
                    code.append("  public void onBootstrap() {};\n");
                    code.append("}\n");
                    return code;
                }
            };

    public static final MockJavaResource GINBINDER =
            new MockJavaResource("com.google.gwt.inject.client.binder.GinBinder") {
                @Override
                public CharSequence getContent() {
                    StringBuilder code = new StringBuilder();
                    code.append("package com.google.gwt.inject.client.binder;\n");
                    code.append("public interface GinBinder {\n");
                    code.append("}\n");
                    return code;
                }
            };

    public static Resource[] getResources() {
        return new Resource[]{
                GINBINDER,
                new RealJavaResource(GinModule.class),
                new RealJavaResource(ApplicationController.class),
                new RealJavaResource(Bootstrapper.class),
                new RealJavaResource(PreBootstrapper.class),
                new RealJavaResource(FooModule.class),
                new RealJavaResource(BarModule.class),
                DEFAULT_BOOTSTRAPPER
        };
    }
}
